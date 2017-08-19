package com.gdgkazan.punkapi.activities;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.util.Log;
import android.widget.Toast;

import com.gdgkazan.punkapi.BeersAdapter;
import com.gdgkazan.punkapi.R;
import com.gdgkazan.punkapi.UtilsPunk.UtilPunk;
import com.gdgkazan.punkapi.api.ApiFactory;
import com.gdgkazan.punkapi.api.BeerApi;
import com.gdgkazan.punkapi.models.Beer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BeersActivity extends AppCompatActivity {

    private BeerApi beerApi;

    private BeersAdapter beersAdapter;

    private SwipeRefreshLayout swipeContainer;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_beer);

        // Создаем сервис через который будет выполняться запрос
        beerApi = ApiFactory.getRetrofitInstance().create(BeerApi.class);

        RecyclerView citiesListView = (RecyclerView) findViewById(R.id.recycler_view);
        LayoutManager layoutManager = new LinearLayoutManager(this);
        citiesListView.setLayoutManager(layoutManager);
        beersAdapter = new BeersAdapter(new ArrayList<Beer>(), getApplicationContext());
        citiesListView.setAdapter(beersAdapter);
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        swipeContainer.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showAllBeers();
                    }
                }, 2000);
            }
        });

        showBeersUsingFilter(new UtilPunk().getDataMap(getIntent()));
    }

    private void showAllBeers() {
        // Создаем экземпляр запроса со всем необходимыми настройками
        Call<List<Beer>> call = beerApi.getAllBeers();
        // Отображаем progress bar
        swipeContainer.setRefreshing(true);
        // Выполняем запрос асинхронно
        call.enqueue(new Callback<List<Beer>>() {
            // В случае если запрос выполнился успешно, то мы переходим в метод onResponse(...)
            @Override
            public void onResponse(@NonNull Call<List<Beer>> call, @NonNull Response<List<Beer>> response) {
                if (response.isSuccessful()) {
                    beersAdapter.updateData(response.body());
                } else {
                    // Если пришел код ошибки, то обрабатываем её
                    Toast.makeText(BeersActivity.this, R.string.network_error, Toast.LENGTH_SHORT).show();
                }
                // Скрываем progress bar
                swipeContainer.setRefreshing(false);
            }

            // Если запрос не удалось выполнить, например, на телефоне отсутствует подключение к интернету
            @Override
            public void onFailure(@NonNull Call<List<Beer>> call, @NonNull Throwable t) {
                // Скрываем progress bar
                swipeContainer.setRefreshing(false);
                Toast.makeText(BeersActivity.this, R.string.network_error, Toast.LENGTH_SHORT).show();
                Log.d("Error", t.getMessage());
            }
        });
    }

    private void showBeersUsingFilter(Map<String, Integer> data) {

        Call<List<Beer>> call = beerApi.getBeerWithFilter(data);

        call.enqueue(new Callback<List<Beer>>() {
            @Override
            public void onResponse(Call<List<Beer>> call, Response<List<Beer>> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(BeersActivity.this, "Найдено: " + response.body().size(), Toast.LENGTH_SHORT).show();
                    beersAdapter.updateData(response.body());
                } else {
                    Toast.makeText(BeersActivity.this, R.string.network_error, Toast.LENGTH_SHORT).show();
                }
                swipeContainer.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<List<Beer>> call, Throwable t) {
                swipeContainer.setRefreshing(false);
                Toast.makeText(BeersActivity.this, R.string.network_error, Toast.LENGTH_SHORT).show();
                Log.d("Error", t.getMessage());
            }
        });
    }
}
