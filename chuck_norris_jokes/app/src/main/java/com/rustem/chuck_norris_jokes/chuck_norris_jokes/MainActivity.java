package com.rustem.chuck_norris_jokes.chuck_norris_jokes;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rustem.chuck_norris_jokes.R;
import com.rustem.chuck_norris_jokes.chuck_norris_jokes.Adapter.JokesAdapter;
import com.rustem.chuck_norris_jokes.chuck_norris_jokes.model.Joke;
import com.rustem.chuck_norris_jokes.chuck_norris_jokes.service.ApiFactory;
import com.rustem.chuck_norris_jokes.chuck_norris_jokes.service.ApiService;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private ApiService apiService;

    private JokesAdapter jokesAdapter;

    private SwipeRefreshLayout swipeContainer;

    @BindView(R.id.postitem_post)
    TextView post;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiService = ApiFactory.getRetrofitInstance().create(ApiService.class);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        jokesAdapter = new JokesAdapter();
        swipe();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(jokesAdapter);
        showJokes("Rustem", "Sabitov");
    }

    private void showJokes(String firstNmae, String lastName) {

        Call<Joke> call = apiService.getJokes(firstNmae, lastName);

        call.enqueue(new Callback<Joke>() {
            @Override
            public void onResponse(@NonNull Call<Joke> call, @NonNull Response<Joke> response) {
                if (response.isSuccessful()) {
                    jokesAdapter.updateData(response.body().getValue());
                } else {
                    Toast.makeText(MainActivity.this, R.string.network_error, Toast.LENGTH_SHORT).show();
                }
                swipeContainer.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<Joke> call, Throwable t) {
                Toast.makeText(MainActivity.this, R.string.network_error, Toast.LENGTH_SHORT).show();
                Log.d("Error", t.getMessage());
            }
        });
    }

    private void swipe(){
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
                        showJokes("Rustem", "Sabitov");
                    }
                }, 2000);
            }
        });
    }
}
