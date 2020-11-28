package com.example.snowtam.Model;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;
import java.util.concurrent.Executor;

public class RechercheVM extends ViewModel {
    private final RechercheDataRepository rechercheDataRepository;
    private Executor executor;

    @Nullable
    private LiveData<List<Recherche>> recherches;

    public RechercheVM(RechercheDataRepository rechercheDataRepository,Executor executor){
        this.rechercheDataRepository = rechercheDataRepository;
        this.executor = executor;
    }

    public LiveData<List<Recherche>> getRecherches(){
        return rechercheDataRepository.getallrecherche();
    }

    public void createRecherche(Recherche r){
        executor.execute(() -> {
            rechercheDataRepository.createRecherche(r);
        });
    }

}
