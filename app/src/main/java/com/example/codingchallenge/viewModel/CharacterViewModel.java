package com.example.codingchallenge.viewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import com.example.codingchallenge.model.repository.CharacterRepository;
import com.example.codingchallenge.model.data.CharacterEntity;

import java.util.List;

public class CharacterViewModel extends AndroidViewModel {
    private CharacterRepository characterRepository;

    public CharacterViewModel (Application application) {
        super(application);
        characterRepository = new CharacterRepository(application);
    }
    public List<CharacterEntity> getAllCharacters() {
        return characterRepository.getAllCharacters();
    }

    public boolean checkIfCharacterInDatabase(String name)
    {
        return characterRepository.checkIfCharacterInDatabase(name);
    }
    public void delete(String name) { characterRepository.delete(name); }

    public void insert(CharacterEntity characterEntity) { characterRepository.insert(characterEntity); }
}