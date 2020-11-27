package com.example.bddprojet.Database.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import com.example.bddprojet.Models.Categorie;
import java.util.List;

@Dao
public interface CategorieDao {

    // Méthodes de commodité
    @Insert(onConflict = OnConflictStrategy.REPLACE) //Permet d'annuler l'insertion si la catégorieExiste déjà
    void insertCategories(Categorie ... categories);

    @Insert(onConflict = OnConflictStrategy.REPLACE) //Permet d'annuler l'insertion si la catégorie
        //Existe déjà
    void insertCategorie(Categorie cate);

    @Update
    void updateCategories(Categorie ... categories);

    @Delete
    void deleteCategories(Categorie ... categories);
    //Supprimer un ingredient en connaissant son id
    @Query("DELETE FROM Categorie WHERE cateId = :cateId")
    void deleteCategoriebyId(int cateId);


    //Demande d'informations

    //Utilité ?
    /*@Query("SELECT * FROM Categorie WHERE nomCat = :nomCategorie")
    LiveData<Categorie> getCategorieName(String nomCategorie);*/

    @Query("SELECT * FROM Categorie WHERE cateId = :idCate")
    //LiveData<List<Categorie>> getCategorieById(int idCate);
    List<Categorie> getCategorieById(int idCate);

    @Query("SELECT * FROM Categorie")
    List<Categorie> getAll();

    @Query("SELECT * FROM Categorie WHERE nomCat LIKE '%'+:search+'%'")
    //LiveData<List<Categorie>> getCategoriebyName(String search);
    List<Categorie> getCategoriebyName(String search);

   /* @Query("SELECT * FROM Categorie WHERE cateId = :idCate")
    LiveData<Categorie> getCategorieById(int idCate);

    @Query("SELECT * FROM Categorie")
    LiveData<List<Categorie>> getAll();

    @Query("SELECT * FROM Categorie WHERE nomCat LIKE '%'+:search+'%'")
    LiveData<Categorie> findCategoriebyName(String search);*/

}
