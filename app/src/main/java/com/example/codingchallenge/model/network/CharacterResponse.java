package com.example.codingchallenge.model.network;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import com.example.codingchallenge.model.data.Character;


public class CharacterResponse {
    @SerializedName("results")
    private List<Character> results;

    public List<Character> getResults() {
        return results;
    }
}
