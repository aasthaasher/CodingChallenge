package com.example.codingchallenge.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.codingchallenge.R;
import com.example.codingchallenge.model.data.Character;
import com.example.codingchallenge.model.data.CharacterEntity;
import com.example.codingchallenge.viewModel.CharacterViewModel;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashSet;
import java.util.Set;

public class DetailActivity extends AppCompatActivity {

    private Character character;
    TextView nameTextView;
    TextView heightTextView;
    TextView massTextView;
    TextView hairColorTextView;
    TextView skinColorTextView;
    TextView eyeColorTextView;
    TextView birthYearTextView;
    TextView genderTextView;
    Button favoriteButton;
    Button backButton;
    Button databaseButton;

    ImageView favoriteIcon;

    private SharedPreferences sharedPreferences;
    private boolean isFavorite = false; // Initial state is not a favorite
    private boolean isInDatabase = false; // Initial state is not saved
    CharacterViewModel characterViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initializeComponents();
        // Set the text of the favorite button based on the initial state
        updateFavoriteButton();

        // Toggle the favorite status when the favorite button is clicked
        favoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleFavoriteStatus();
                updateFavoriteButton();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // Add or remove character from the database depending on whether character is in database
        databaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Check if the character is already in the database
                if(isInDatabase){
                    // if already saved then remove
                    removeCharacterFromDatabaseInBackground();
                }
                else {
                    // if not saved then add to database
                    addCharacterToDatabaseInBackground();
                }
            }
        });
    }

    private void initializeComponents() {
        // Retrieve the character object from the intent
        character = getIntent().getParcelableExtra("character");
        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("favorites", Context.MODE_PRIVATE);

        // Check if the character is already a favorite
        isFavorite = checkIfFavorite(character.getName());

        // Initialize UI components
        nameTextView = findViewById(R.id.nameTextView);
        heightTextView = findViewById(R.id.heightTextView);
        massTextView = findViewById(R.id.massTextView);
        hairColorTextView = findViewById(R.id.hairColorTextView);
        skinColorTextView = findViewById(R.id.skinColorTextView);
        eyeColorTextView = findViewById(R.id.eyeColorTextView);
        birthYearTextView = findViewById(R.id.birthYearTextView);
        genderTextView = findViewById(R.id.genderTextView);
        favoriteButton = findViewById(R.id.favoriteButton);
        backButton = findViewById(R.id.backButton);
        favoriteIcon = findViewById(R.id.favoriteIcon);
        databaseButton = findViewById(R.id.addToDatabase);

        // Check if the Character is Already in the Database
        checkIfCharacterInDatabaseInBackground();


        // Populate the UI with character details
        nameTextView.setText(character.getName());
        heightTextView.setText(String.format(getString(R.string.height_label), character.getHeight()));
        massTextView.setText(String.format(getString(R.string.mass_label), character.getMass()));
        hairColorTextView.setText(String.format(getString(R.string.hair_color_label), character.getHairColor()));
        skinColorTextView.setText(String.format(getString(R.string.skin_color_label), character.getSkinColor()));
        eyeColorTextView.setText(String.format(getString(R.string.eye_color_label), character.getEyeColor()));
        birthYearTextView.setText(String.format(getString(R.string.birth_year_label), character.getBirthYear()));
        genderTextView.setText(String.format(getString(R.string.gender_label), character.getGender()));

    }

    // Helper method to check if a character is already a favorite
    private boolean checkIfFavorite(String characterName) {
        // Retrieve favorites from SharedPreference
        Set<String> favoriteSet = sharedPreferences.getStringSet("favorites_list", null);
        if (favoriteSet!=null){
            return favoriteSet.contains(characterName);
        }
        return false;
    }

    // Helper method to check if a character is already saved
    public void checkIfCharacterInDatabaseInBackground() {
        characterViewModel = new ViewModelProvider(this).get(CharacterViewModel.class);
        if (characterViewModel.checkIfCharacterInDatabase(character.getName())) {
            isInDatabase = true;
            updateDatabaseButton();
        }
    }

    // Helper method to toggle the favorite status
    private void toggleFavoriteStatus() {
        // Retrieve favorites from SharedPreferences
        Set<String> favoriteSet = sharedPreferences.getStringSet("favorites_list", null);
        Set<String> newFavoriteSet = new HashSet<>();
        if (favoriteSet!=null){
            newFavoriteSet.addAll(favoriteSet);}
        if (isFavorite) {
            newFavoriteSet.remove(character.getName());
        }
        else {
            newFavoriteSet.add(character.getName());
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet("favorites_list", newFavoriteSet);
        editor.apply();

        // Toggle the favorite state
        isFavorite = !isFavorite;
    }

    // Update the text of the favorite button based on the favorite status
    private void updateFavoriteButton() {
        Button favoriteButton = findViewById(R.id.favoriteButton);
        if (isFavorite) {
            favoriteButton.setText(R.string.remove_from_favorites);
            favoriteIcon.setVisibility(View.VISIBLE);
        } else {
            favoriteButton.setText(R.string.add_to_favorites);
            favoriteIcon.setVisibility(View.INVISIBLE);

        }

    }

    // Update the text of the database button based on the favorite status
    private void updateDatabaseButton() {
        if (isInDatabase) {
            databaseButton.setText(R.string.remove_from_db);
        } else {
            databaseButton.setText(R.string.add_to_db);
        }
    }

    // Helper method to save character
    public void addCharacterToDatabaseInBackground() {
        CharacterEntity characterEntity = new CharacterEntity();
        characterEntity.setName(character.getName());
        characterEntity.setHeight(character.getHeight());
        characterEntity.setMass(character.getMass());
        characterEntity.setHairColor(character.getHairColor());
        characterEntity.setSkinColor(character.getSkinColor());
        characterEntity.setEyeColor(character.getEyeColor());
        characterEntity.setBirthYear(character.getBirthYear());
        characterEntity.setGender(character.getGender());

        characterViewModel = new ViewModelProvider(this).get(CharacterViewModel.class);
        characterViewModel.insert(characterEntity);
        isInDatabase = true;

        // change the text of the database button
        updateDatabaseButton();
    }

    // Helper method to remove character from database
    private void removeCharacterFromDatabaseInBackground() {
        characterViewModel = new ViewModelProvider(this).get(CharacterViewModel.class);
        characterViewModel.delete(character.getName());
        isInDatabase = false;

        // change the text of the database button
        updateDatabaseButton();
    }
        @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

}