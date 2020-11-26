package com.example.snowtam.Model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {@ForeignKey(entity = Recherche.class,parentColumns = "id",childColumns = "id_recherche"),
        @ForeignKey(entity = Code_IOCA.class,parentColumns = "id",childColumns = "id_code")})
public class Recherche_code {
    @PrimaryKey(autoGenerate = true)
    private long id;

    private long id_recherche;
    private long id_code;

    public Recherche_code(long id, long id_recherche, long id_code) {
        this.id = id;
        this.id_recherche = id_recherche;
        this.id_code = id_code;
    }

    public Recherche_code() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId_recherche() {
        return id_recherche;
    }

    public void setId_recherche(long id_recherche) {
        this.id_recherche = id_recherche;
    }

    public long getId_code() {
        return id_code;
    }

    public void setId_code(long id_code) {
        this.id_code = id_code;
    }
}
