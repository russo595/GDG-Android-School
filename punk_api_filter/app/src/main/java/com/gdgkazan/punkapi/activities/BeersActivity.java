package com.gdgkazan.punkapi.activities;

import android.app.ProgressDialog;
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
import com.gdgkazan.punkapi.api.ApiFactory;
import com.gdgkazan.punkapi.api.BeerService;
import com.gdgkazan.punkapi.models.Beer;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BeersActivity extends AppCompatActivity {

    private BeerService beerService;

    private BeersAdapter beersAdapter;

    private SwipeRefreshLayout swipeContainer;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_beer);

        // Создаем сервис через который будет выполняться запрос
        beerService = ApiFactory.getRetrofitInstance().create(BeerService.class);
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
                        fetchRandomBeer();
                    }
                }, 2000);
            }
        });
        fetchRandomBeer();
    }

    private void fetchRandomBeer() {
        // Создаем экземпляр запроса со всем необходимыми настройками
        Call<List<Beer>> call = beerService.getRandomBeers();
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

    private void fetchFilterBeer(String date, int abvGt) {
        // Создаем экземпляр запроса со всем необходимыми настройками
        Call<List<Beer>> call = beerService.getBeerWithFilter(date, abvGt);
        // Отображаем progress bar
        swipeContainer.setRefreshing(true);
        // Выполняем запрос асинхронно
        call.enqueue(new Callback<List<Beer>>() {
            // В случае если запрос выполнился успешно, то мы переходим в метод onResponse(...)
            @Override
            public void onResponse(@NonNull Call<List<Beer>> call, @NonNull Response<List<Beer>> response) {
                if (response.isSuccessful()) {
                    // Если в ответ нам пришел код 2xx, то отображаем содержимое запроса
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
}
