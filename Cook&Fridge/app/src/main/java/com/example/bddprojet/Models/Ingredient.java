package com.example.bddprojet.Models;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity
public class Ingredient {
    /*
       Création d'un ingredient
    */
    @PrimaryKey(autoGenerate = true) //Incrémentation automatique de la clé primaire
    private int ingredientId;
    private int categorieId;
    private int monFrigoId;
    private String nomProduit;

    //Instanciation des constructeurs
    public Ingredient() {}

    public Ingredient(String nom, int cateID){
        this.nomProduit = nom;
        this.categorieId = cateID;
    }

    public Ingredient(String nom, int cateID, int monFrigoId){
        this.nomProduit = nom;
        this.categorieId = cateID;
        this.monFrigoId = monFrigoId;
    }

    // Instanciation des getters
    public int getIngredientId() {
        return ingredientId;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public int getCategorieId() { return categorieId; }

    public int getMonFrigoId() { return monFrigoId; }

    //public int getRecetteId() { return rectId; }

    //Instanciation des setters
    public void setIngredientId(int id) {
        this.ingredientId = id;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public void setCategorieId(int id) { this.categorieId = id; }

    public void setMonFrigoId(int monFrigoId) { this.monFrigoId = monFrigoId; }

    //public void setRecetteId(int recetteId) { this.rectId = recetteId; }

    public String toString() {
        return "ID : "+getIngredientId()+"\nCatégorie n° : "+getCategorieId()+"\nProduit : "+getNomProduit()+"\n";
    }
}
