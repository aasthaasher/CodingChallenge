package com.example.codingchallenge;

import androidx.appcompat.app.AppCompatActivity;
import com.example.codingchallenge.model.Character;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initializeComponents();

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // @TODO: favorite button onclick
        favoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void initializeComponents() {
        // Retrieve the character object from the intent
        character = getIntent().getParcelableExtra("character");

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
        heightTextView.setText("Height: " + character.getHeight());
        massTextView.setText("Mass: " + character.getMass());
        hairColorTextView.setText("Hair Color: " + character.getHairColor());
        skinColorTextView.setText("Skin Color: " + character.getSkinColor());
        eyeColorTextView.setText("Eye Color: " + character.getEyeColor());
        birthYearTextView.setText("Birth Year: " + character.getBirthYear());
        genderTextView.setText("Gender: " + character.getGender());

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

}