package com.example.snowtam.Model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities = Recherche.class, version = 1, exportSchema = false)
public  abstract class SaveMyRechercheDatabase extends RoomDatabase {
    public static volatile SaveMyRechercheDatabase INSTANCE;

    public abstract RechercheDao rechercheDao();

    public static SaveMyRechercheDatabase getInstance(Context c){
        if(INSTANCE == null){
            synchronized (SaveMyRechercheDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(c.getApplicationContext(),SaveMyRechercheDatabase.class,"MyDatabase.db").build();
                }
            }
        }
        return INSTANCE;
    }
}
