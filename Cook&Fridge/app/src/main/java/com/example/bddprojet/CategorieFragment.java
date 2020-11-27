package com.example.bddprojet;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bddprojet.Models.Categorie;
import com.example.bddprojet.Models.Ingredient;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CategorieFragment extends Fragment implements View.OnClickListener {

    private Button FLButton, PatesButton, DiversButton, EpicesButton, EpicerieButton, FromageButton, RetourButton;
    private static final String TAG = "Hello";
    private List<Categorie> categories=MainActivity.db.categorieDao().getAll();
    private String etat;

    public CategorieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ajouter_ingredient, container, false);


        FLButton = view.findViewById(R.id.button_Fl);
        PatesButton = view.findViewById(R.id.button_pates);
        DiversButton = view.findViewById(R.id.button_Divers);
        EpicesButton = view.findViewById(R.id.button_epices);
        EpicerieButton = view.findViewById(R.id.button_epicerie);
        FromageButton =view.findViewById(R.id.button_fromage);
        RetourButton = view.findViewById(R.id.button_retour4);

        FLButton.setText("+");
        PatesButton.setText("+");
        DiversButton.setText("+");
        EpicerieButton.setText("+");
        EpicesButton.setText("+");
        FromageButton.setText("+");

        FLButton.setOnClickListener(this);
        PatesButton.setOnClickListener(this);
        DiversButton.setOnClickListener(this);
        EpicesButton.setOnClickListener(this);
        EpicerieButton.setOnClickListener(this);
        FromageButton.setOnClickListener(this);
        RetourButton.setOnClickListener(this);


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

//Bundle qui envoie l'id de la catégorie au fragment Ajout_Ingredient_fragment
        Bundle bundle=new Bundle();
        FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
        Ajout_Ingredient_fragment ajout_ingredient_fragment=new Ajout_Ingredient_fragment();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();

        switch (view.getId()){
            case R.id.button_Fl:
                bundle.putInt("ID",categories.get(0).getCateId());
                ajout_ingredient_fragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.fragment_container,ajout_ingredient_fragment);
                fragmentTransaction.commit();
                break;
            case R.id.button_pates:
                bundle.putInt("ID",categories.get(1).getCateId());
                ajout_ingredient_fragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.fragment_container,ajout_ingredient_fragment);
                fragmentTransaction.commit();
                break;
            case R.id.button_Divers:
                bundle.putInt("ID",categories.get(2).getCateId());
                ajout_ingredient_fragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.fragment_container,ajout_ingredient_fragment);
                fragmentTransaction.commit();
                break;
            case R.id.button_epices:
                bundle.putInt("ID",categories.get(3).getCateId());
                ajout_ingredient_fragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.fragment_container,ajout_ingredient_fragment);
                fragmentTransaction.commit();
                break;
            case R.id.button_epicerie:
                bundle.putInt("ID",categories.get(4).getCateId());
                ajout_ingredient_fragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.fragment_container,ajout_ingredient_fragment);
                fragmentTransaction.commit();
                break;
            case R.id.button_fromage:
                bundle.putInt("ID",categories.get(5).getCateId());
                ajout_ingredient_fragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.fragment_container,ajout_ingredient_fragment);
                fragmentTransaction.commit();
                break;

            case R.id.button_retour4:
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container,new FrigoFragment()).addToBackStack(null).commit();
                break;

        }
    }
}