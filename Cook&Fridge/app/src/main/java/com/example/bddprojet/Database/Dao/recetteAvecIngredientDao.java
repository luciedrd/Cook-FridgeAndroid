package com.example.bddprojet.Database.Dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;
import java.util.List;
import com.example.bddprojet.Relations.recetteAvecIngredient;

@Dao
public interface recetteAvecIngredientDao {
    @Transaction
    @Query("SELECT * FROM recette")
    public List<recetteAvecIngredient> getIngredientsAvecRecette();

    @Transaction
    @Query("SELECT * FROM recette WHERE recetteId= :id")
    public List<recetteAvecIngredient> getIngredientsAvecRecetteID(int id);
}


