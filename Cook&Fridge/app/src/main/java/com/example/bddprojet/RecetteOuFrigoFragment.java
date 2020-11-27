package com.example.bddprojet;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecetteOuFrigoFragment extends Fragment implements View.OnClickListener {


    private static final String TAG = "Hello";

    private Button LesRecettesButton,MonFrigoButton;

    public RecetteOuFrigoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_recette_ou_frigo, container, false);
        LesRecettesButton = view.findViewById(R.id.button_RecetteList);
        MonFrigoButton = view.findViewById(R.id.button_Frigo);
        LesRecettesButton.setOnClickListener(this);
        MonFrigoButton.setOnClickListener(this);

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
    public void onClick(View view){

        switch (view.getId()){
            case R.id.button_RecetteList:
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container,new RecetteListFragment()).addToBackStack(null).commit();
                break;

            case R.id.button_Frigo:
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container,new FrigoFragment()).addToBackStack(null).commit();
                break;
       }
    }
}
