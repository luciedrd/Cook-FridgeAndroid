package com.example.bddprojet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.room.Room;
import android.os.Bundle;
import android.util.Log;

import com.example.bddprojet.Database.AppDatabase;
import com.example.bddprojet.Models.Categorie;
import com.example.bddprojet.Models.Ingredient;
import com.example.bddprojet.Models.MonFrigo;
import com.example.bddprojet.Models.Recette;
import com.example.bddprojet.Relations.categorieAvecIngredient;
import com.example.bddprojet.Relations.ingredientAvecRecette;
import com.example.bddprojet.Relations.monFrigoAvecIngredient;
import com.example.bddprojet.Relations.recetteAvecIngredient;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static FragmentManager fragmentManager;
    public static final String TAG = "Hello";
    public static AppDatabase db;
    public static MonFrigo monFrigo;
    public static int frigoID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();

        if(findViewById(R.id.fragment_container)!=null){
            if(savedInstanceState!=null){
                return;
            }
        fragmentManager.beginTransaction().add(R.id.fragment_container, new Home()).commit();
        }

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "BDD.db").allowMainThreadQueries().createFromAsset("Database/firstDatabase.db").build();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "on start"+getLocalClassName());
        /*
            On obtient les catégories qui sont dans notre base de données
         */
        List<Categorie> categories = db.categorieDao().getAll();
        Log.i(TAG, "On a toutes les catégories suivantes : "+ categories);

        /*
            On obtient les ingrédients par catégorie.
         */
        /*
        List<categorieAvecIngredient> CateIngredient = db.categorieAvecIngredientDao().getCategorieAvecIngredients();
        int i = CateIngredient.size();
        for (int k=0; k<=i; k++){
            //CateIngredient.get(k).ingredients;
            List<Ingredient> ingredients = CateIngredient.get(k).ingredients;
            Log.i(TAG, "On a tous les ingrédients suivants dans la catégorie n°"+(k+1)+": "+ ingredients);
        }
        */

        /*
            On obtient les recettes qui contiennent l'ingrédient qu'on choisit grâce à son ID
        */
        /*
        List <ingredientAvecRecette> ingreRecette = db.ingredientAvecRecetteDao().getRecetteByIngredientID(1);
        int i = ingreRecette.size();
        for (int k=0; k<=i; k++){
            //CateIngredient.get(k).ingredients;
             List<Recette> recettes = ingreRecette.get(k).recetteList;
            Log.i(TAG, "Il y a des coquillettes dans la(es) recette(s) suivante(s) : "+ recettes);
        }
        */

        /*
            On obtient les recettes qui contiennent l'ingrédient
        */
        /*
        List <ingredientAvecRecette> ingreRecette = db.ingredientAvecRecetteDao().getRecetteIngredients();
        List <Ingredient> ingres = db.ingredientDao().getAll();
        int i = ingreRecette.size();
        for (int k=0; k<=i; k++){
            //CateIngredient.get(k).ingredients;
            List<Recette> recettes = ingreRecette.get(k).recetteList;
            Log.i(TAG, "Il y a de(s) "+ingres.get(k).getNomProduit()+" dans la(es) recette(s) suivante(s) : "+ recettes);
        }
        */

        /*
            On obtient les ingrédient contenus dans la recette
        */
        /*
        List<recetteAvecIngredient> recetteIngres = db.recetteAvecIngredientDao().getIngredientsAvecRecette();
        List <Recette> recettes = db.recetteDao().getAll();
        int i = recetteIngres.size();
        for (int k=0; k<=i;k++){
            List<Ingredient> ingredientList = recetteIngres.get(k).ingredients;
            Log.i(TAG, "La recette : "+recettes.get(k).getNomRecette()+", contient les ingrédients suivants : "+ ingredientList);
        }
        */

        /*
            On obtient les ingrédients contenus dans la recette choisit par l'ID
        */
/*
        List<recetteAvecIngredient> recetteIngres = db.recetteAvecIngredientDao().getIngredientsAvecRecetteID(3);
        Recette recette = db.recetteDao().getRecetteById(3);
        int i = recetteIngres.size();
        for (int k=0; k<=i;k++){
            List<Ingredient> ingredientList = recetteIngres.get(k).ingredients;
            Log.i(TAG, "La recette : "+recette.getNomRecette()+", contient les ingrédients suivants : "+ ingredientList);
        }
*/


        // PREMIERE ETAPE : On récupère le "frigo" qui nous intéresse et son ID
       MonFrigo  monFrigo = db.monFrigoDao().getMonFrigo();
       frigoID = monFrigo.getFrigoId();

        // DEUXIEME ETAPE : ON SELECTIONNE DES INGREDIENTS OU ON VA MODIFIER L'IdFRIGO EN FONCTION DE
        //NOTRE FRIGO PUIS ON UPDATE CES INGREDIENTS !

        /*List<Ingredient> ingredientList = db.ingredientDao().getAll();
        Ingredient ing1 = ingredientList.get(2); // On prélève les ingrédients qu'on ajoute à nore frigo
        Ingredient ing2 = ingredientList.get(4);
        ing1.setMonFrigoId(frigoID); // On set l'idFrigo aux ingrédients
        ing2.setMonFrigoId(frigoID);
        db.ingredientDao().updateIngredients(ing1, ing2); // On met à jours nos ingrédients dans la base de données
*/
        /*
            On obtient  ALORS les ingrédients de mon Frigo
        */
/*
        List<monFrigoAvecIngredient> frigoIngredient = db.monFrigoAvecIngredientDao().getMonFrigoIDAvecIngredients(frigoID);
        List<Ingredient> ingredients = frigoIngredient.get(0).ingredients;
        Log.i(TAG, "Dans mon Frigo, il y a les ingrédients suivants: "+ ingredients);


        // On vérifie que si on set à nouveau l'iD frigo à 0 il ne fait plus partie du frigo :
        ing2.setMonFrigoId(0);
        db.ingredientDao().updateIngredients(ing2);
      List<monFrigoAvecIngredient> frigoIngredientNEW = db.monFrigoAvecIngredientDao().getMonFrigoIDAvecIngredients(frigoID);
        List<Ingredient> ingredts = frigoIngredientNEW.get(0).ingredients;
        Log.i(TAG, "Dans mon Frigo après MISE A JOUR, il y a les ingrédients suivants: "+ ingredts);
 */
    }

}
