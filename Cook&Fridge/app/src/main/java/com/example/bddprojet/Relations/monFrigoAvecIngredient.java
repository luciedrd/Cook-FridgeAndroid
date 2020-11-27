package com.example.bddprojet.Relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.bddprojet.Models.Ingredient;
import com.example.bddprojet.Models.MonFrigo;

import java.util.List;
/*
        Cette classe permet de lier notre frigo avec les ingrédients qu'on y ajoutera
 */
public class monFrigoAvecIngredient {

    @Embedded public MonFrigo frigo;
    @Relation(
            parentColumn = "frigoId",
            entityColumn = "monFrigoId"
    )
    public List<Ingredient> ingredients;
}
