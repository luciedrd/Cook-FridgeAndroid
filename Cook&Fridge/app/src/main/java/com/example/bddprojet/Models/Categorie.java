package com.example.bddprojet.Models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Categorie {
     /*
    Création d'une catégorie d'ingredients
     */
    @PrimaryKey(autoGenerate = true) //Incrémentation automatique de la clé primaire
    private int cateId;
    private String nomCat;

    //Instanciation des constructeurs
    public Categorie() {}

    public Categorie(String nom){
        this.nomCat = nom;
    }

    // Instanciation des getters
    public int getCateId() {
        return cateId;
    }

    public String getNomCat() {
        return nomCat;
    }

    //Instanciation des setters
    public void setCateId(int id) {
        this.cateId = id;
    }

    public void setNomCat(String nomCat) {
        this.nomCat = nomCat;
    }

    public String toString() {
        return "ID : "+getCateId()+" Catégorie : "+getNomCat()+"\n";
    }
}
