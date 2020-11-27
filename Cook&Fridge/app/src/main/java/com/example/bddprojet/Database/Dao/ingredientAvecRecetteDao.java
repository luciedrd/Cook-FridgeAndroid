package com.example.bddprojet.Database.Dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;
import com.example.bddprojet.Relations.ingredientAvecRecette;
import java.util.List;

@Dao
public interface ingredientAvecRecetteDao {
    @Transaction
    @Query("SELECT * FROM recetteIngredientCrossRef WHERE ingredientId = :id")
    public List<ingredientAvecRecette> getRecetteByIngredientID(int  id);

    @Transaction
    @Query("SELECT * FROM ingredient")
    public List<ingredientAvecRecette> getRecetteIngredients();
}
