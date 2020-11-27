package com.example.bddprojet.Models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "frigo")
public class MonFrigo {
    /*
    Création des données dans mon frigo
     */
    @PrimaryKey(autoGenerate = true) //Incrémentation automatique de la clé primaire
    private int frigoId;
    //private List<Ingredient> ingredientsList;

    //Instanciation des constructeurs
    public MonFrigo(){
        //this.ingredientsList = new ArrayList<Ingredient>();
    }

    /*MonFrigo(List<Ingredient> liste){
        this.ingredientsList = liste;
    }*/

    //Instanciation des getters
    public int getFrigoId() {
        return frigoId;
    }

    /*public List<Ingredient> getIngredientsList() {
        return ingredientsList;
    }*/

    //Instanciation des setters
    public void setFrigoId(int id) {
        this.frigoId = id;
    }

    /*public void setIngredientsList(List<Ingredient> ingredientsList) {
        this.ingredientsList = ingredientsList;
    }*/
}
