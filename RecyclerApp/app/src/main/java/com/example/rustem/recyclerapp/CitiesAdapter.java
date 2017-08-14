package com.example.rustem.recyclerapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CitiesAdapter extends RecyclerView.Adapter<CitiesViewHolder> {

    private List<CapitalModel> cities;

    private Context context;

    public CitiesAdapter(List<CapitalModel> cities, Context context) {
        this.cities = cities;
        this.context = context;
    }

    @Override
    public CitiesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View inflate = layoutInflater.inflate(R.layout.row_citiy, parent, false);
        return new CitiesViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(final CitiesViewHolder holder, final int position) {
        String cityName = cities.get(position).getCityName();
        TextView cityNameView = holder.cityNameView;
        cityNameView.setText(cityName);

        String countryName = cities.get(position).getCountryName();
        TextView countryNameView = holder.countryNameView;
        countryNameView.setText(countryName);

        String code = cities.get(position).getCountryCode();
        TextView countryCode = holder.countryCode;
        countryCode.setText(code);

        String codeFlag = "_" + code.toLowerCase();
        String packageName = context.getApplicationContext().getPackageName();
        int identifier = context.getApplicationContext().getResources().getIdentifier(codeFlag, "drawable", packageName);
        ImageView flag = holder.flag;
        flag.setImageResource(identifier);

        holder.likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = cities.get(position).getCountLike();
                count++;
                cities.get(position).setCountLike(count);
                TextView likeCounter = holder.likeCounter;
                likeCounter.setText(String.format("+%s", String.valueOf(cities.get(position).getCountLike())));
            }
        });

        holder.dislikeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = cities.get(position).getCountDislike();
                count--;
                cities.get(position).setCountDislike(count);
                TextView dislikeCounter = holder.dislikeCounter;
                dislikeCounter.setText(String.valueOf(cities.get(position).getCountDislike()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }
}
