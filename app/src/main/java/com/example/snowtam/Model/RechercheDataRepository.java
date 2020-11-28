package com.example.snowtam.Model;

import androidx.lifecycle.LiveData;

import java.util.List;

public class RechercheDataRepository {
    private final RechercheDao rechercheDao;

    public RechercheDataRepository(RechercheDao rechercheDao) {
        this.rechercheDao = rechercheDao;
    }

    public LiveData<List<Recherche>> getallrecherche(){
        return this.rechercheDao.getAllRecherches();
    }

    public  long createRecherche(Recherche r){
        return this.rechercheDao.createRecherche(r);
    }
}
