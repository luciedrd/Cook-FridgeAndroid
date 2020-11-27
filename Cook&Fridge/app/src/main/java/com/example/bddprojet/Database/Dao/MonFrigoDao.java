package com.example.bddprojet.Database.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.bddprojet.Models.MonFrigo;

import java.util.List;

@Dao
public interface MonFrigoDao {
    @Query("SELECT * FROM frigo")
    MonFrigo getMonFrigo();
}
