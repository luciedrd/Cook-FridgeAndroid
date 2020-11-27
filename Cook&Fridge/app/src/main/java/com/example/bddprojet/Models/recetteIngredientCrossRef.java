package com.example.bddprojet.Models;

import androidx.room.Entity;

@Entity(primaryKeys = {"recetteId", "ingredientId"})
public class recetteIngredientCrossRef {
    public int recetteId;
    public int ingredientId;
}

