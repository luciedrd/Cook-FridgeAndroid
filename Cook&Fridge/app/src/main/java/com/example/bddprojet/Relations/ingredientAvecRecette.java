package com.example.bddprojet.Relations;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;
import java.util.List;

import com.example.bddprojet.Models.Ingredient;
import com.example.bddprojet.Models.Recette;
import com.example.bddprojet.Models.recetteIngredientCrossRef;
/*
        Cette classe permet de lier un ingrédient à plusieurs recettes
 */
public class ingredientAvecRecette {
    @Embedded public Ingredient ingredient;
    @Relation(
            parentColumn = "ingredientId",
            entityColumn = "recetteId",
            associateBy = @Junction(recetteIngredientCrossRef.class)
    )
    public List<Recette> recetteList;
}

