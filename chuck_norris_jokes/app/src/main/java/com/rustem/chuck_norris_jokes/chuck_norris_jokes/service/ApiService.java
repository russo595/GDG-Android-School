package com.rustem.chuck_norris_jokes.chuck_norris_jokes.service;

import com.rustem.chuck_norris_jokes.chuck_norris_jokes.model.Joke;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("jokes/random/30")
    Call<Joke> getJokes(@Query("firstName") String firstName, @Query("lastName") String lastName);

}
