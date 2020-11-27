package com.example.bddprojet;


import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.room.Room;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;


import com.example.bddprojet.Database.AppDatabase;
import com.example.bddprojet.Database.Dao.CategorieDao;
import com.example.bddprojet.Models.Categorie;
import com.example.bddprojet.Models.Ingredient;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class IngredientDaoTest {

    //FOR DATA
    private AppDatabase db;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void initDb() throws Exception{
        this.db = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().getContext(),AppDatabase.class)
                .allowMainThreadQueries().build();
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }

    //DATA SET FOR TEST
    private static int CATE_ID = 1 ;
    private static Categorie CATE_DEMO = new Categorie("Fruits/Légumes");
    private static Ingredient NEW_FRUIT = new Ingredient("Banane", CATE_ID);
    private static Ingredient NEW_LEGUME = new Ingredient("Poireau", CATE_ID);


    //Test sur la Catégorie
    @Test
    public void insertAndGetCategorie() throws Exception{
        //Before : ajout d'une catégorie
        this.db.categorieDao().insertCategorie(CATE_DEMO);
        //TEST
        Categorie cate = LiveDataTestUtil.getValue((LiveData<List<Categorie>>) this.db.categorieDao().getCategorieById(CATE_ID)).get(0);
        assertTrue(cate.getNomCat().equals(CATE_DEMO.getNomCat()) && cate.getCateId()==CATE_ID);
    }

    //Test sur le produit
    @Test
    public void getIngredientWhenNoIngredientInserted() throws Exception{
        //TEST
        List<Ingredient> produits = LiveDataTestUtil.getValue((LiveData<List<Ingredient>>) this.db.ingredientDao().getIngredient(CATE_ID));
        assertTrue(produits.isEmpty());
    }

    @Test
    public void insertAndGetIngredients() throws Exception{
        //Before : ajout de la catégorie et des produits
        this.db.categorieDao().insertCategorie(CATE_DEMO);
        this.db.ingredientDao().insertIngredients(NEW_FRUIT,NEW_LEGUME);

        //TEST
        List<Ingredient> produits = LiveDataTestUtil.getValue((LiveData<List<Ingredient>>) this.db.ingredientDao().getIngredient(CATE_ID));
        assertTrue(produits.size()==2);
    }

    @Test
    public void insertAndUpdateIngredient() throws InterruptedException{
        //Before : Ajout de la catégorie et d'un produit. Next: sauvegarde produit ajouté et re-sauvegarde
        this.db.categorieDao().insertCategorie(CATE_DEMO);
        this.db.ingredientDao().insertIngredients(NEW_FRUIT);
        Ingredient prdtAjoute = LiveDataTestUtil.getValue((LiveData<List<Ingredient>>) this.db.ingredientDao().getIngredient(CATE_ID)).get(0);
        this.db.ingredientDao().updateIngredients(prdtAjoute);

        //TEST
        List<Ingredient> produits = LiveDataTestUtil.getValue((LiveData<List<Ingredient>>) this.db.ingredientDao().getIngredient(CATE_ID));
        assertTrue(produits.size() == 1);
    }

    @Test
    public void insertAndDeleteIngredient() throws InterruptedException{
        //Before : Ajout de la catégorie et d'un produit. Next : obtient le produit ajouté puis le supprime.
        this.db.categorieDao().insertCategories(CATE_DEMO);
        this.db.ingredientDao().insertIngredients(NEW_FRUIT);
        Ingredient prdtAjoute = LiveDataTestUtil.getValue((LiveData<List<Ingredient>>) this.db.ingredientDao().getIngredient(CATE_ID)).get(0);
        this.db.ingredientDao().deleteIngredientbyId(prdtAjoute.getIngredientId());

        //TEST
        List<Ingredient> produits = LiveDataTestUtil.getValue((LiveData<List<Ingredient>>) this.db.ingredientDao().getIngredient(CATE_ID));
        assertTrue(produits.isEmpty());
    }

}

