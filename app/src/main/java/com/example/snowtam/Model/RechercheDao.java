package com.example.snowtam.Model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface RechercheDao {
@Insert
long createRecherche(Recherche recherche);

@Query("DELETE FROM Recherche WHERE id = :itemId")
long deleteRecherche(long itemId);

@Update
long UpdateRecherche(Recherche r);

}
