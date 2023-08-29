package com.example.codingchallenge.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SwapidevService {
    @GET("people/")
    Call<CharacterResponse> searchCharacters(@Query("search") String query);
}
