package com.gdgkazan.punkapi.api;

import com.gdgkazan.punkapi.models.Beer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BeerService {

    @GET("beers")
    Call<List<Beer>> getRandomBeers();

    @GET("beers/{id}")
    Call<List<Beer>> getBeerById(@Path("id") long id);

    @GET("beers")
    Call<List<Beer>> getBeerWithFilter(
            @Query("brewed_before") String date,
            @Query("abv_gt") int abvGt);
}
