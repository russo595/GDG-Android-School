package com.gdgkazan.punkapi.api;

import com.gdgkazan.punkapi.models.Beer;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface BeerApi {

    @GET("beers")
    Call<List<Beer>> getAllBeers();

    @GET("beers")
    Call<List<Beer>> getBeerWithFilter(@QueryMap Map<String, Integer> options);
}
