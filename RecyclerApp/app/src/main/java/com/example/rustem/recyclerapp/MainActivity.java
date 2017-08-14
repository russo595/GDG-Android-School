package com.example.rustem.recyclerapp;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;

import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

import static android.support.v7.widget.RecyclerView.LayoutManager;
import static com.example.rustem.recyclerapp.ListCities.getListCities;

public class MainActivity extends AppCompatActivity {

    private RecyclerView citiesListView;
    private LayoutManager layoutManager;
    private CitiesAdapter citiesAdapter;
    private SwipeRefreshLayout swipeContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        citiesListView = (RecyclerView) findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(this);
        citiesListView.setLayoutManager(layoutManager);

        citiesAdapter = new CitiesAdapter(getListCities(), this);
        citiesListView.setAdapter(citiesAdapter);

        ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        citiesListView.addItemDecoration(itemDecoration);
        citiesListView.setItemAnimator(new SlideInUpAnimator());

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
                        citiesAdapter = new CitiesAdapter(getListCities(), getApplicationContext());
                        citiesListView.setAdapter(citiesAdapter);
                        swipeContainer.setRefreshing(false);
                    }
                }, 3000);
            }
        });
    }
}
