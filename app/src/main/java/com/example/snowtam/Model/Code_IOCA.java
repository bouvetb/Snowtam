package com.example.snowtam.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Code_IOCA {
    @PrimaryKey(autoGenerate = true)
    private long id;

    private String code;

    public Code_IOCA() {
    }

    public Code_IOCA(long id, String code) {
        this.id = id;
        this.code = code;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
