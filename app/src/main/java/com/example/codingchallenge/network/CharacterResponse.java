package com.example.codingchallenge.network;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import com.example.codingchallenge.model.Character;


public class CharacterResponse {
    @SerializedName("results")
    private List<Character> results;

    public List<Character> getResults() {
        return results;
    }
}
