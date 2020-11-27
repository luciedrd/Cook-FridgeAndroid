package com.example.bddprojet;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.bddprojet.Models.Recette;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Recette_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Recette_fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    private TextView TxtRecette;
    private Button bt_retour;
    private static final String TAG = "Hello";
    int id_recette;

    public Recette_fragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recette, container, false);

//Récupération de l'id de la recette sélectionné
        Bundle bundle= getArguments();
        id_recette=bundle.getInt("ID");
        TxtRecette = view.findViewById(R.id.Recette_text);
        bt_retour=view.findViewById(R.id.button_retour5);
        Recette recette = MainActivity.db.recetteDao().getRecetteById(id_recette);
        String nom=recette.getNomRecette();
        String descritpion=recette.getDescription();

        String info = "";
        info=info+"\n\n"+nom+"\n\n"+descritpion;
        TxtRecette.setText(info);
        TxtRecette.setMovementMethod(new ScrollingMovementMethod());


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
