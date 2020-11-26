package com.example.snowtam.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Date;
@Entity
public class Recherche {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private Date date;

    public Recherche() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Recherche(long id, Date date) {
        this.id = id;
        this.date = date;
    }
}
