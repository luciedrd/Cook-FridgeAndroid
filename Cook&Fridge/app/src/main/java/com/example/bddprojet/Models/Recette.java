package com.example.bddprojet.Models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity
public class Recette {
    /*
    Création d'une recette
     */
    @PrimaryKey(autoGenerate = true) //Incrémentation automatique de la clé primaire
    private int recetteId;
    private String nomRecette;
    //private List<Ingredient> ingredientsList;
    private String description;

    //Instanciation des constructeurs
    public Recette() {
    }

    public Recette(String nom,List<Ingredient> liste, String descrip) {
        this.nomRecette = nom;
        //this.ingredientsList = liste;
        this.description = descrip;
    }

    // Instanciation des getters
    public int getRecetteId() {
        return recetteId;
    }

    public String getNomRecette() {
        return nomRecette;
    }

    public String getDescription(){
        return description;
    }

    /*public List<Ingredient> getIngredientsList() {
        return ingredientsList;
    }*/

    //Instanciation des setters
    public void setRecetteId(int id) {
        this.recetteId = id;
    }

    public void setNomRecette(String nom) {
        this.nomRecette = nom;
    }

    public void setDescription(String nom){
        this.description = nom;
    }

    /*public void setIngredientsList(List<Ingredient> ingredients){
        this.ingredientsList = ingredients;
    }*/

    /*public String toString() {
        return "ID : " + getRecetteId() + "\nRecette : " + getNomRecette()+"\nIngredients : "+ getIngredientsList()
                +" \nDescription : "+ getDescription();
    }*/
    public String toString() {
        return "ID : " + getRecetteId() + "\nRecette : " + getNomRecette()
                +"\nDescription : "+ getDescription()+"\n";
    }
}

