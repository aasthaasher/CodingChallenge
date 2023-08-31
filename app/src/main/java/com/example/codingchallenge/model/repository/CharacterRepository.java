package com.example.codingchallenge.model.repository;

import android.app.Application;
import android.os.AsyncTask;

import com.example.codingchallenge.model.data.CharacterDao;
import com.example.codingchallenge.model.data.CharacterDatabase;
import com.example.codingchallenge.model.data.CharacterEntity;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class CharacterRepository {

    public CharacterDao characterDao;
    public CharacterDatabase characterDatabase;

    public CharacterRepository(Application application) {
        characterDatabase = CharacterDatabase.getDatabase(application);
        characterDao = characterDatabase.characterDao();
    }

    public List<CharacterEntity> getAllCharacters() {
        try {
            return new GetCharsAsyncTask().execute().get();

        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
    public void insert(CharacterEntity characterEntity) {
        CharacterDatabase.databaseWriteExecutor.execute(() -> {
            characterDao.insert(characterEntity);
        });
    }
    public void delete(String name) {
        CharacterDatabase.databaseWriteExecutor.execute(() -> {
            characterDao.deleteContactById(name);
        });
    }

    public boolean checkIfCharacterInDatabase(String name){
        try {
            return new checkIfExistsAsyncTask().execute(name).get();

        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public class GetCharsAsyncTask extends AsyncTask<Void, Void, List<CharacterEntity>>
    {
        @Override
        protected List<CharacterEntity> doInBackground(Void... url) {
            return characterDao.getAllCharacters();
        }
    }

    public class checkIfExistsAsyncTask extends AsyncTask<String, Void, Boolean>
    {
        @Override
        protected Boolean doInBackground(String... name) {
            String charName = name[0];
            return characterDao.getCharacterByName(charName)!=null;
        }
    }
}
