package com.example.codingchallenge.views;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.codingchallenge.R;
import com.example.codingchallenge.model.data.CharacterEntity;
import com.example.codingchallenge.viewModel.CharacterViewModel;
import com.example.codingchallenge.views.adapters.CharacterAdapter;
import com.example.codingchallenge.model.network.SwapidevService;
import com.example.codingchallenge.model.data.Character;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import com.example.codingchallenge.model.network.CharacterResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements CharacterAdapter.OnItemClickListener{
    private SearchView searchView;
    private RecyclerView recyclerView;
    private CharacterAdapter characterAdapter;
    private List<Character> characterList;
    private Retrofit retrofit;
    private SwapidevService swapidevService;
    // Reference to the ongoing API call
    private Call<CharacterResponse> currentApiCall;
    SharedPreferences sharedpreferences;
    public List<CharacterEntity> allCharsInDb;
    public List<String> charsInDB;
    CharacterViewModel characterViewModel;
    Button showDatabaseButton;
    String charsInDBMessage;

    public static final String fav_list = "favorites_list";
    Set<String> favoritesList;

    @Override
    public void onRestart() {
        super.onRestart();

        // Retrieve the favorites list when the activity is restarted
        favoritesList = sharedpreferences.getStringSet(fav_list, null);

        // Get saved characters
        getDatabaseCharacters();

        // Reset the adapter to ensure the favorites are loaded correctly
        characterAdapter = new CharacterAdapter(characterList, favoritesList, charsInDB, this);
        recyclerView.setAdapter(characterAdapter);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getDatabaseCharacters();

        initializeComponents();

        // Set up search functionality
        setUpSearchView();

        showDatabaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage(charsInDBMessage);
                if(Objects.equals(charsInDBMessage, "")){
                    builder.setMessage("No saved characters");
                }
                else {
                    builder.setMessage(charsInDBMessage);
                }
                builder.setTitle("Characters in Database")
                        .setPositiveButton("Done", (DialogInterface.OnClickListener) (dialog, which) -> {
                    dialog.cancel();
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });


    }

    private void initializeComponents() {
        // Retrieve favorites from SharedPreferences
        sharedpreferences = this.getSharedPreferences("favorites",
                Context.MODE_PRIVATE);
        favoritesList = sharedpreferences.getStringSet("favorites_list", null);

        // Initialize UI components
        searchView = findViewById(R.id.searchView);
        recyclerView = findViewById(R.id.recyclerView);
        showDatabaseButton = findViewById(R.id.showDatabaseButton);


        // Initialize RecyclerView and adapter
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        characterList = new ArrayList<>();

        // Initialize Retrofit for API calls
        retrofit = new Retrofit.Builder()
                .baseUrl("https://swapi.dev/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        swapidevService = retrofit.create(SwapidevService.class);

        // Set Adapter
        characterAdapter = new CharacterAdapter(characterList, favoritesList, charsInDB, this);
        recyclerView.setAdapter(characterAdapter);

    }

    private void getDatabaseCharacters() {
        characterViewModel = new ViewModelProvider(this).get(CharacterViewModel.class);
        allCharsInDb = characterViewModel.getAllCharacters();
        charsInDBMessage = "";
        charsInDB = new ArrayList<>();

        for (CharacterEntity characterEntity : allCharsInDb) {
            charsInDB.add(characterEntity.getName());
            charsInDBMessage += characterEntity.getName() + "\n";
        }
    }

    private void setUpSearchView() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchForOngoingApiCalls(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchForOngoingApiCalls(newText);
                return false;
            }
        });
    }

    private void searchForOngoingApiCalls(String query) {
        if (!query.isEmpty()) {
            // Cancel the previous API call if it's still running
            if (currentApiCall != null && !currentApiCall.isCanceled()) {
                currentApiCall.cancel();
            }
            createAPIcall(query);
        } else {
            characterList.clear();
            characterAdapter.notifyDataSetChanged();
        }

    }

    private void createAPIcall(String query) {

        // Create a new API call for the current search
        currentApiCall = swapidevService.searchCharacters(query);
        currentApiCall.enqueue(new Callback<CharacterResponse>() {

            @Override
            public void onResponse(Call<CharacterResponse> call, Response<CharacterResponse> response) {
                if (response.isSuccessful()) {
                    characterList.clear();
                    characterList.addAll(response.body().getResults());
                    characterAdapter.notifyDataSetChanged();
                    CharacterResponse characterResponse = response.body();
                    List<Character> characters = characterResponse.getResults();

                } else {
                    int statusCode = response.code();
                    Log.e("API Error", "HTTP Status Code: " + statusCode);
                }
            }

            @Override
            public void onFailure(Call<CharacterResponse> call, Throwable t) {
                // Handling network error
                call.cancel();

            }
        });
    }

    @Override
    public void onItemClick(int position) {
        // Handling item click to open Detail Activity with character details
        Character character = characterList.get(position);
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("character", character);
        startActivity(intent);
    }


}