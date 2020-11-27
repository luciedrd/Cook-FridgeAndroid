package com.example.bddprojet.Relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.bddprojet.Models.Categorie;
import com.example.bddprojet.Models.Ingredient;

import java.util.List;

/*
      Cette classe permet de lier une Catégorie à une liste d'ingrédient
 */
public class categorieAvecIngredient {

        @Embedded public Categorie categorie;
        @Relation(
                parentColumn = "cateId",
                entityColumn = "categorieId"
        )
        public List<Ingredient> ingredients;
}
