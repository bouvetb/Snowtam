package com.example.snowtam.Model;

import android.content.Context;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Injection {
    public static RechercheDataRepository provideRechercheDataRepo(Context c) {
        SaveMyRechercheDatabase database = SaveMyRechercheDatabase.getInstance(c);
        return new RechercheDataRepository(database.rechercheDao());
    }
    public static Executor provideExecutor(){
        return Executors.newSingleThreadExecutor();
    }
    public static VmFactory provideVmFactory(Context c){
        RechercheDataRepository rechercheDataRepository = provideRechercheDataRepo(c);
        Executor executor = provideExecutor();
        return new VmFactory(rechercheDataRepository,executor);
    }
}
