package com.example.bddprojet.Relations;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.example.bddprojet.Models.Ingredient;
import com.example.bddprojet.Models.Recette;
import com.example.bddprojet.Models.recetteIngredientCrossRef;

import java.util.List;
/*
        Cette classe permet de lier une recette à plusieurs ingrédients
 */
public class recetteAvecIngredient {
    @Embedded public Recette recette;
    @Relation(
            parentColumn = "recetteId",
            entityColumn = "ingredientId",
            associateBy = @Junction(recetteIngredientCrossRef.class)
    )
    public List<Ingredient> ingredients;
}