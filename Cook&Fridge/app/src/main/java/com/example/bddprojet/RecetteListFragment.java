package com.example.bddprojet;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.bddprojet.Models.Recette;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.bddprojet.Models.Recette;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecetteListFragment extends Fragment  implements View.OnClickListener {


    private static final String TAG = "Hello";

    private Button Recette1Button,Recette2Button,Recette3Button,RetourButton;
    private List<Recette> recette = MainActivity.db.recetteDao().getAll();
    private List<Integer> recette_id = new ArrayList<>();

    public RecetteListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


    View view= inflater.inflate(R.layout.fragment_recette_list, container, false);
        Recette1Button = view.findViewById(R.id.button_Recette1);
        Recette2Button = view.findViewById(R.id.button_Recette2);
        Recette3Button = view.findViewById(R.id.button_Recette3);
        RetourButton = view.findViewById(R.id.button_retour2);

        Recette1Button.setOnClickListener(this);
        Recette2Button.setOnClickListener(this);
        Recette3Button.setOnClickListener(this);
        RetourButton.setOnClickListener(this);

        Recette1Button.setText(recette.get(0).getNomRecette());
        Recette2Button.setText(recette.get(1).getNomRecette());
        Recette3Button.setText(recette.get(2).getNomRecette());

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

        Bundle bundle=new Bundle();
        FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
        Recette_fragment recette_fragment=new Recette_fragment();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();

        switch (view.getId()){
            case R.id.button_Recette1:
                bundle.putInt("ID",recette.get(0).getRecetteId());
                recette_fragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.fragment_container,recette_fragment);
                fragmentTransaction.commit();
                break;

            case R.id.button_Recette2:
                bundle.putInt("ID",recette.get(1).getRecetteId());
                recette_fragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.fragment_container,recette_fragment);
                fragmentTransaction.commit();
                break;

            case R.id.button_Recette3:
                bundle.putInt("ID",recette.get(2).getRecetteId());
                recette_fragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.fragment_container,recette_fragment);
                fragmentTransaction.commit();
                break;

            case R.id.button_retour2:
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container,new RecetteOuFrigoFragment()).addToBackStack(null).commit();
                break;
        }
    }
}
