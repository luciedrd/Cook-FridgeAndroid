package com.example.bddprojet.Database.Dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;
import java.util.List;

import com.example.bddprojet.Models.Ingredient;
import com.example.bddprojet.Relations.categorieAvecIngredient;

@Dao
public interface CategorieAvecIngredientDao {
    @Transaction
    @Query("SELECT * FROM Ingredient WHERE categorieId=:id")
    public List<Ingredient> getCategorieAvecIngredients(int id);
}