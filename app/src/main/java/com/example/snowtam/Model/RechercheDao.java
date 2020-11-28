package com.example.snowtam.Model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface RechercheDao {
@Insert
long createRecherche(Recherche recherche);

@Query("DELETE FROM Recherche WHERE id = :itemId")
int deleteRecherche(long itemId);

@Update
int UpdateRecherche(Recherche r);

@Query("SELECT * FROM Recherche")
LiveData<List<Recherche>> getAllRecherches();


}
