package com.example.bddprojet.Database.Dao;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;
import java.util.List;
import com.example.bddprojet.Relations.monFrigoAvecIngredient;

@Dao
public interface MonFrigoAvecIngredientDao {
    @Transaction
    @Query("SELECT * FROM frigo WHERE frigoId= :id")
    public List<monFrigoAvecIngredient> getMonFrigoIDAvecIngredients(int id);
}
