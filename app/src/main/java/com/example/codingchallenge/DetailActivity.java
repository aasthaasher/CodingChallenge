package com.example.codingchallenge;

import androidx.appcompat.app.AppCompatActivity;
import com.example.codingchallenge.model.Character;

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

    ImageView favoriteIcon;

    private SharedPreferences sharedPreferences;
    private boolean isFavorite = false; // Initial state is not a favorite

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


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

}