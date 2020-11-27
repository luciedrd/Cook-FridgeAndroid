package com.example.bddprojet;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.bddprojet.Models.Ingredient;
import com.example.bddprojet.Relations.monFrigoAvecIngredient;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FrigoFragment extends Fragment implements View.OnClickListener {

    private TextView TxtInfo1;

    private Button ButtonEnlever,ButtonAjouter,RetourButton,Button_Frigo;;
    private static final String TAG = "Hello";

    public FrigoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_frigo, container, false);

        // Affichage des légumes
        List<monFrigoAvecIngredient> frigoIngredientNEW2 = MainActivity.db.monFrigoAvecIngredientDao().getMonFrigoIDAvecIngredients(MainActivity.frigoID);
        List<Ingredient> ingredts2 = frigoIngredientNEW2.get(0).ingredients;
        TxtInfo1 = view.findViewById(R.id.txtView_Frigo1);
        String info1 = " ";
        for(Ingredient ing : ingredts2){
           String nom =ing.getNomProduit();
            info1=info1+nom+" \n\n ";
        }

        TxtInfo1.setText(info1);
        TxtInfo1.setMovementMethod(new ScrollingMovementMethod());
        RetourButton = view.findViewById(R.id.button_retour3);
        ButtonAjouter = view.findViewById(R.id.button_AjouterIngredient);
        ButtonEnlever = view.findViewById(R.id.button_eneleverIngredient);
        Button_Frigo=view.findViewById(R.id.bt_frigo_recette);

        ButtonEnlever.setOnClickListener(this);
        RetourButton.setOnClickListener(this);
        ButtonAjouter.setOnClickListener(this);
        Button_Frigo.setOnClickListener(this);

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

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.button_eneleverIngredient:
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container,new Delete_Ingredient_fragment()).addToBackStack(null).commit();
                break;

            case R.id.button_AjouterIngredient:
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container,new CategorieFragment()).addToBackStack(null).commit();
                break;

            case R.id.button_retour3:
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container,new RecetteOuFrigoFragment()).addToBackStack(null).commit();
                break;

            case R.id.bt_frigo_recette:
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container,new Ingredient_Recette()).addToBackStack(null).commit();
                break;
        }

    }
}

