package com.example.bddprojet;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bddprojet.Models.Ingredient;
import com.example.bddprojet.Relations.monFrigoAvecIngredient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Delete_Ingredient_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Delete_Ingredient_fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String TAG = "Hello";
    private Button vide;
    private Button retour;
    private HashMap ingredient_frigo_map = new HashMap();
    private CheckBox checkBox;
    private CheckBox check_all;
    private LinearLayout linear;
    private List<CheckBox> checkBoxes;
    private TextView textView;

    public Delete_Ingredient_fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_delete__ingredient_fragment, container, false);
        final List<monFrigoAvecIngredient> frigoIngredient = MainActivity.db.monFrigoAvecIngredientDao().getMonFrigoIDAvecIngredients(MainActivity.frigoID);
        //frigo_ingredient=view.findViewById(R.id.list_frigo_ingredient);
        retour = view.findViewById(R.id.button_retour5);
        linear = view.findViewById(R.id.linear_lay);
        check_all = view.findViewById(R.id.checkBox_all);
        linear.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        textView=view.findViewById(R.id.text_select);

        checkBoxes = new ArrayList<>();

//Affiche tous les éléments du frigo dans une liste de checkBox
        for (Ingredient ingredient : frigoIngredient.get(0).ingredients) {
            ingredient_frigo_map.put(ingredient.getIngredientId(), ingredient.getNomProduit());
            checkBox = new CheckBox(this.getActivity());
            checkBox.setId(ingredient.getIngredientId());
            checkBox.setText(ingredient.getNomProduit());
            checkBox.setOnClickListener(getOnclick(checkBox));
            linear.addView(checkBox);
            checkBoxes.add(checkBox);
        }

        frigoIngredient.clear();
        frigoIngredient.addAll(ingredient_frigo_map.values());

//CheckBox qui permet de valider/invalider toutes les autres checkBox en même temps
        check_all.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = check_all.isChecked();
                if (checked) {
                    for (int i = 0; i < checkBoxes.size(); i++) {
                        CheckBox currentChecBox = checkBoxes.get(i);
                        currentChecBox.setChecked(true);
                    }
                    check_all.setText("Tout Désélectionner");
                    textView.setText(countChecked()+" ingrédients \nsélectionnés");
                }
                else {
                    for (int i = 0; i < checkBoxes.size(); i++) {
                        CheckBox currentChecBox = checkBoxes.get(i);
                        currentChecBox.setChecked(false);
                    }
                    textView.setText("");
                    check_all.setText("Tout Sélectionner");
                }
            }
        });

//Bt qui ramène à la page précédente
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new FrigoFragment()).addToBackStack(null).commit();
            }
        });


//Dialog box lors de l'appuie sur le bouton "Vider le frigo" qui demande de valider l'action
//Supprime du frigo le ou les élément(s) sélectionné(s)
        vide= view.findViewById(R.id.button_viderFrigo);
        vide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (countChecked() != 0) {
                    AlertDialog.Builder alertdialog = new AlertDialog.Builder(Delete_Ingredient_fragment.this.getActivity());
                    Log.i(TAG, "Suppression oui/non ?");
                    alertdialog.setMessage(phrase_pluriel()).setCancelable(false)
                            .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    for (int i = 0; i < checkBoxes.size(); i++) {
                                        CheckBox currentChecBox = checkBoxes.get(i);
                                        if (currentChecBox.isChecked()) {
                                            Ingredient ingredient_select = MainActivity.db.ingredientDao().findIngredientbyId(currentChecBox.getId());
                                            ingredient_select.setMonFrigoId(0);
                                            MainActivity.db.ingredientDao().updateIngredients(ingredient_select);
                                        }
                                    }
                                    MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new FrigoFragment()).addToBackStack(null).commit();
                                }
                            }).setNegativeButton("Non", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog alert = alertdialog.create();
                    alert.setTitle("Suppression élément");
                    alert.show();
                }
            }
        });

        return view;
    }

//Variation du TextView en fonction du nombres d'éléments sélectionnés
    private View.OnClickListener getOnclick(final CheckBox checkBox) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (countChecked()>1){ textView.setText(countChecked()+" ingrédients \nsélectionnés");}
                else if (countChecked()==1){textView.setText(countChecked()+" ingrédient \nsélectionné");}
                else textView.setText("");
                Log.i(TAG, "bonjour: " + checkBox.getId() + "item: " + checkBox.getText().toString());
            }
        };
    }


    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "on start " + getClass().getSimpleName());
    }

    @Override
    public void onResume() {
        super.onResume();
        this.onCreate(null);
        Log.i(TAG, "on resume " + getClass().getSimpleName());
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "on pause " + getClass().getSimpleName());
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG, "on stop " + getClass().getSimpleName());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "on destroy " + getClass().getSimpleName());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i(TAG, "on attach " + getClass().getSimpleName());
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(TAG, "on activity created " + getClass().getSimpleName());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(TAG, "on destroy view " + getClass().getSimpleName());
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(TAG, "on detach " + getClass().getSimpleName());
    }

//Variation de la phrase de la pop-up lors du clic sur le bouton vider
    public String phrase_pluriel(){
        String phrase;
        if (countChecked()!=1 & countChecked()!=checkBoxes.size()){phrase="Voulez-vous vraiment supprimer les "+ countChecked() +" ingrédients sélectionnés ?"; }
        else if (countChecked()==checkBoxes.size()){phrase="Voulez-vous vraiment supprimer tous les ingrédients ?";}
        else phrase="Voulez-vous vraiment supprimer l'ingrédient sélectionné ?";
        return phrase;
    }
//Compteur de checkbox validé
    public int countChecked() {
        int compteur = 0;
        for (CheckBox cb:checkBoxes) {
            CheckBox currentChecBox = cb;
            if (currentChecBox.isChecked()) {
                compteur++;
            }
        }
        if (compteur==checkBoxes.size()){
            check_all.setChecked(true);
            check_all.setText("Tout Désélectionner");;
        }
        else if (compteur!=checkBoxes.size()) {
            check_all.setChecked(false);
            check_all.setText("Tout Sélectionner");
        }
        else check_all.setChecked(false);
        return compteur;
    }
}

