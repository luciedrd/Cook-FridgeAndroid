package com.example.bddprojet.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.example.bddprojet.Database.Dao.CategorieAvecIngredientDao;
import com.example.bddprojet.Database.Dao.CategorieDao;
import com.example.bddprojet.Database.Dao.IngredientDao;
import com.example.bddprojet.Database.Dao.MonFrigoAvecIngredientDao;
import com.example.bddprojet.Database.Dao.MonFrigoDao;
import com.example.bddprojet.Database.Dao.RecetteDao;
import com.example.bddprojet.Database.Dao.ingredientAvecRecetteDao;
import com.example.bddprojet.Database.Dao.recetteAvecIngredientDao;
import com.example.bddprojet.Models.Categorie;
import com.example.bddprojet.Models.Ingredient;
import com.example.bddprojet.Models.MonFrigo;
import com.example.bddprojet.Models.Recette;
import com.example.bddprojet.Models.recetteIngredientCrossRef;

@Database(entities = {Categorie.class, Ingredient.class, MonFrigo.class, Recette.class, recetteIngredientCrossRef.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    // -- DAO --
    public abstract CategorieDao categorieDao();
    public abstract IngredientDao ingredientDao();
    public abstract MonFrigoDao monFrigoDao();
    public abstract RecetteDao recetteDao();
    public abstract CategorieAvecIngredientDao categorieAvecIngredientDao();
    public abstract ingredientAvecRecetteDao ingredientAvecRecetteDao();
    public abstract recetteAvecIngredientDao recetteAvecIngredientDao();
    public abstract MonFrigoAvecIngredientDao monFrigoAvecIngredientDao();
}
