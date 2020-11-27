package com.example.bddprojet.Database.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;
import com.example.bddprojet.Models.Ingredient;

@Dao
public interface IngredientDao {

    // Méthodes de commodité

    @Insert(onConflict = OnConflictStrategy.REPLACE) //Permet d'annuler l'insertion si la catégorie
        //Existe déjà
    void insertIngredients(Ingredient ... prdt);

    @Update
    void updateIngredients(Ingredient ... prdt);

    @Delete
    void deleteIngredients(Ingredient ... prdt);
    //Supprimer un ingredient en connaissant son id
    @Query("DELETE FROM Ingredient WHERE ingredientId = :prdtId")
    void deleteIngredientbyId(int prdtId);

    //Demande d'informations

    @Query("SELECT * FROM Ingredient WHERE categorieId= :categorieId")
    //LiveData<List<Ingredient>> getIngredient(int categorieId);
    List<Ingredient> getIngredient(int categorieId);

    @Query("SELECT * FROM Ingredient WHERE ingredientId= :ingredientId")
        //LiveData<List<Ingredient>> getIngredient(int categorieId);
    Ingredient findIngredientbyId(int ingredientId);

    @Query("SELECT * FROM Ingredient")
    List<Ingredient> getAll();

    @Query("SELECT * FROM Ingredient WHERE nomProduit=:search ")
    Ingredient findIngredientbyName(String search);
}
