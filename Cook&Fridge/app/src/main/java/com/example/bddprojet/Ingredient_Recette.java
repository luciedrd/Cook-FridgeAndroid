package com.example.bddprojet;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.bddprojet.Models.Ingredient;
import com.example.bddprojet.Models.MonFrigo;
import com.example.bddprojet.Models.Recette;
import com.example.bddprojet.Relations.ingredientAvecRecette;
import com.example.bddprojet.Relations.monFrigoAvecIngredient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Ingredient_Recette#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Ingredient_Recette extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String TAG = "Hello";
    private ListView liste_recette;
    private List<String> recettes=new ArrayList<>();
    private List<Integer> recettes_id=new ArrayList<>();
    private HashMap recette_map= new HashMap();
    private int num_recette;
    private Button bt_retour;


    public Ingredient_Recette() {
        // Required empty public constructor
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_ingredient__recette, container, false);
        liste_recette=view.findViewById(R.id.liste_recette);
        bt_retour=view.findViewById(R.id.button_retour5);

        MonFrigo monFrigo = MainActivity.db.monFrigoDao().getMonFrigo();
        int frigoID = monFrigo.getFrigoId();

        List<monFrigoAvecIngredient> frigoIngredient = MainActivity.db.monFrigoAvecIngredientDao().getMonFrigoIDAvecIngredients(frigoID);
        List<Ingredient> ingredients = frigoIngredient.get(0).ingredients;
        List <ingredientAvecRecette> ingreRecette=new ArrayList<>();

//Récupère toute les recettes associé au ingrédient présent dans le frigo et on les met dans une liste
        for (Ingredient ing : ingredients) {
            int i = 0;
            ingreRecette = MainActivity.db.ingredientAvecRecetteDao().getRecetteByIngredientID(ing.getIngredientId());
            Log.i(TAG, "Dans mon Frigo, il y a les ingrédients suivants: " + ingreRecette);
            for (ingredientAvecRecette rec : ingreRecette) {
                if (i <= rec.recetteList.size() - 1) {
                    String r = rec.recetteList.get(i).getNomRecette();
                    int rec_id = rec.recetteList.get(i).getRecetteId();
                    Log.i(TAG, "nom Recette: " + r + " id :" + rec_id);
                    recette_map.putIfAbsent(rec_id,r);
                    i++;
                }
                    else {
                        i=0;
                    }
            }
        }
        Log.i(TAG, "nom Recette: " +  recette_map);
        recettes.addAll(recette_map.values());
        Log.i(TAG, "nom Recette: " +  recettes);

        ArrayAdapter<String> arrayAdapter_cat=new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_list_item_1,recettes);
        liste_recette.setAdapter(arrayAdapter_cat);

//On récupère l'id de la recette sélectionné que l'on envoie dans un bundle pour le transmettre au fragment recette
        liste_recette.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemvalue=(String) liste_recette.getItemAtPosition(position);
                num_recette= recette_map.keySet().toArray()[position].hashCode();
                Log.i(TAG, "nom Recette: " + itemvalue+" id :"+recette_map.keySet().toArray()[position]);
                Bundle bundle=new Bundle();
                bundle.putInt("ID",num_recette);

                FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();

                Recette_fragment recette_fragment=new Recette_fragment();
                recette_fragment.setArguments(bundle);

                fragmentTransaction.replace(R.id.fragment_container,recette_fragment);
                fragmentTransaction.commit();

            }

    });
        bt_retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container,new FrigoFragment()).addToBackStack(null).commit();
            }
        });
        return view;
    }
    @Override
    public void onStart()
    {
        super.onStart();
        Log.i(TAG, "on start " + getClass().getSimpleName());
    }
    @Override
    public void onResume()
    {
        super.onResume();
        Log.i(TAG, "on resume " + getClass().getSimpleName());
    }
    @Override
    public void onPause()
    {
        super.onPause();
        Log.i(TAG, "on pause " + getClass().getSimpleName());
    }
    @Override
    public void onStop()
    {
        super.onStop();
        Log.i(TAG, "on stop " + getClass().getSimpleName());
    }
    @Override
    public void onDestroy()
    {
        super.onDestroy();
        Log.i(TAG, "on destroy " + getClass().getSimpleName());
    }
    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        Log.i(TAG, "on attach " + getClass().getSimpleName());
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        Log.i(TAG, "on activity created " + getClass().getSimpleName());
    }
    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        Log.i(TAG, "on destroy view " + getClass().getSimpleName());
    }
    @Override
    public void onDetach()
    {
        super.onDetach();
        Log.i(TAG, "on detach " + getClass().getSimpleName());
    }


}
