package com.example.snowtam.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Date;
import java.util.ArrayList;

@Entity
public class Recherche {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String date;

    public String getCodes() {
        return codes;
    }

    public void setCodes(String codes) {
        this.codes = codes;
    }

    private String codes;

    public Recherche() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Recherche(long id, String date,String codes) {
        this.id = id;
        this.date = date;
        this.codes = codes;
    }
    public Recherche(String date,String codes){
        this.date = date;
        this.codes = codes;
    }
}
