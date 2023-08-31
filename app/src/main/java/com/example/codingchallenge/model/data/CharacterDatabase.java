package com.example.codingchallenge.model.data;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {CharacterEntity.class}, version = 1)
public abstract class CharacterDatabase extends RoomDatabase {
    public abstract CharacterDao characterDao();

    public static volatile CharacterDatabase INSTANCE;
    public static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static CharacterDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (CharacterDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    CharacterDatabase.class, "character-db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}