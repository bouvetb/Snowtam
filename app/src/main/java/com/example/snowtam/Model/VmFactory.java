package com.example.snowtam.Model;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.util.concurrent.Executor;

public class VmFactory implements ViewModelProvider.Factory {
    private final RechercheDataRepository rechercheDataRepository;
    private Executor executor;

    public VmFactory(RechercheDataRepository rechercheDataRepository, Executor executor) {
        this.rechercheDataRepository = rechercheDataRepository;
        this.executor = executor;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(RechercheVM.class)){
            return (T) new RechercheVM(rechercheDataRepository,executor);
        }
        throw new IllegalArgumentException("Unknown VM");
    }
}
