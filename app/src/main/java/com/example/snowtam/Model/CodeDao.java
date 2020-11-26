package com.example.snowtam.Model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
@Dao
public interface CodeDao {
    @Insert
    long insertCode(Code_IOCA code);

    @Update
    int updateCode(Code_IOCA code);

    @Query("DELETE FROM Code_IOCA WHERE id = :itemId")
    int deleteCode(long itemId);

    @Query("SELECT code FROM Code_IOCA WHERE id = :itemId")
    int SelectName(long itemId);
}
