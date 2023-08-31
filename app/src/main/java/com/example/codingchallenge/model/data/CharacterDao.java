package com.example.codingchallenge.model.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.codingchallenge.model.data.CharacterEntity;

import java.util.List;

@Dao
public interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(CharacterEntity character);

    @Query("DELETE FROM characters WHERE name = :characterName")
    void deleteContactById(String characterName);

    @Query("SELECT * FROM characters")
    List<CharacterEntity> getAllCharacters();

    @Query("SELECT * FROM characters WHERE name = :characterName")
    CharacterEntity getCharacterByName(String characterName);

}
