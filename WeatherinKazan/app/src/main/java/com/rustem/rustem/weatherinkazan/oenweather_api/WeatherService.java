package com.rustem.rustem.weatherinkazan.oenweather_api;

import com.rustem.rustem.weatherinkazan.model.MainData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {

    @GET("forecast/daily")
    Call<MainData> getMainData(
            @Query("id") int id,
            @Query("cnt") int cnt,
            @Query("units") String units
    );

}
