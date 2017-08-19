package com.gdgkazan.punkapi.api;

import com.gdgkazan.punkapi.models.Beer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BeerApi {

    @GET("beers")
    Call<List<Beer>> getAllBeers();

    @GET("beers")
    Call<List<Beer>> getBeerWithFilter(
            @Query("abv_gt") int abvGt,
            @Query("abv_lt") int abvLt
    );
}
