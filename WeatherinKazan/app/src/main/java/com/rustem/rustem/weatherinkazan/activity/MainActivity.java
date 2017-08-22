package com.rustem.rustem.weatherinkazan.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.rustem.rustem.weatherinkazan.R;
import com.rustem.rustem.weatherinkazan.adapter.WeatherAdapter;
import com.rustem.rustem.weatherinkazan.model.MainData;
import com.rustem.rustem.weatherinkazan.openweather_api.ApiFactory;
import com.rustem.rustem.weatherinkazan.openweather_api.WeatherService;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.rustem.rustem.weatherinkazan.BuildConfig.ID_CITY;

public class MainActivity extends AppCompatActivity {

    private static final int CNT = 7;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.swipeContainer)
    SwipeRefreshLayout swipeRefreshLayout;

    private WeatherService weatherService;

    private WeatherAdapter weatherAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        weatherService = ApiFactory.getRetrofitInstance().create(WeatherService.class);

        weatherAdapter = new WeatherAdapter(getApplicationContext());
        swipe();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(weatherAdapter);
        showWeather();
    }

    private void showWeather() {

        Call<MainData> call = weatherService.getMainData(ID_CITY, CNT, "metric");
        call.enqueue(new Callback<MainData>() {
            @Override
            public void onResponse(@NonNull Call<MainData> call, @NonNull Response<MainData> response) {
                if (response.isSuccessful()) {
                    weatherAdapter.updateData(response.body().getDataDays());
                } else {
                    Toast.makeText(MainActivity.this, R.string.network_error, Toast.LENGTH_SHORT).show();
                }
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<MainData> call, Throwable t) {

            }
        });
    }

    private void swipe() {
        swipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showWeather();
                    }
                }, 2000);
            }
        });
    }
}
