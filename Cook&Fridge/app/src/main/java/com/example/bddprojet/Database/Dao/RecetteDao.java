package com.example.bddprojet.Database.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;
import com.example.bddprojet.Models.Recette;

@Dao
public interface RecetteDao {

    // Méthodes de commodité

    @Insert(onConflict = OnConflictStrategy.ABORT) //Permet d'annuler l'insertion si la catégorie
        //Existe déjà
    void insertAll(Recette... recettes);

    @Update
    int updateRecette(Recette r);

    @Delete
    void delete(Recette recette);
    //Supprimer un ingredient en connaissant son id
    @Query("DELETE FROM Recette WHERE recetteId = :recetteId")
    void deleteRecettebyId(int recetteId);

    //Demande d'informations

    @Query("SELECT * FROM Recette WHERE recetteId = :idRecette")
    Recette getRecetteById(int idRecette);

    @Query("SELECT * FROM Recette")
    List<Recette> getAll();

    @Query("SELECT * FROM Recette WHERE nomRecette LIKE '%'+:search+'%'")
    List<Recette> findRecettebyName(String search);
}
