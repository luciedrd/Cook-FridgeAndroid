package com.example.bddprojet;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bddprojet.Database.Dao.CategorieAvecIngredientDao;
import com.example.bddprojet.Models.Categorie;
import com.example.bddprojet.Models.Ingredient;
import com.example.bddprojet.Relations.categorieAvecIngredient;
import com.example.bddprojet.Relations.monFrigoAvecIngredient;

import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Ingredient_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Ajout_Ingredient_fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String TAG = "Hello";
    private int id_categorie;
    private ListView list_Ingredient;
    private TextView nom_categorie;
    private List<Categorie> categories=MainActivity.db.categorieDao().getAll();
    private HashMap ingredient_map= new HashMap();
    private Button bt_retour;

    public Ajout_Ingredient_fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_ingredient_fragment, container, false);
        list_Ingredient = view.findViewById(R.id.list_ingredient);
        nom_categorie = view.findViewById(R.id.Categorie_name);
        bt_retour=view.findViewById(R.id.bt_retour);

//On récupère le nom de la catégorie dans un bundle
        Bundle bundle = getArguments();
        id_categorie = bundle.getInt("ID");
        nom_categorie.setText(categories.get(id_categorie - 1).getNomCat() + ":");

//On recherche tous les ingredients correspondant à l'id de la catégorie sélectionnée
        List<Ingredient> ingredientList = MainActivity.db.categorieAvecIngredientDao().getCategorieAvecIngredients(id_categorie);
        for (Ingredient ing : ingredientList) {
                ingredient_map.put(ing.getIngredientId(), ing.getNomProduit());
        }

        ingredientList.clear();
        ingredientList.addAll(ingredient_map.values());

        ArrayAdapter<Ingredient> arrayAdapter_ing = new ArrayAdapter<Ingredient>(this.getActivity(), android.R.layout.simple_list_item_1, ingredientList);
        list_Ingredient.setAdapter(arrayAdapter_ing);

//Listener de la liste qui ajoute un ingrédient au frigo lors du clic
        final List<Ingredient> ingList = MainActivity.db.categorieAvecIngredientDao().getCategorieAvecIngredients(id_categorie);
        list_Ingredient.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Ingredient ing=ingList.get(position);
                ing.setMonFrigoId(MainActivity.frigoID);
                MainActivity.db.ingredientDao().updateIngredients(ing);
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container,new FrigoFragment()).addToBackStack(null).commit();
            }
        });

        bt_retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new CategorieFragment()).addToBackStack(null).commit();
            }
        });

            return view;
        }



    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "on start " + getClass().getSimpleName());
    }

    @Override
    public void onResume() {
        super.onResume();
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
}