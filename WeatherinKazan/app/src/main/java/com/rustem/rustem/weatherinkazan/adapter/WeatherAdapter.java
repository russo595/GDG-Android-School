package com.rustem.rustem.weatherinkazan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rustem.rustem.weatherinkazan.R;
import com.rustem.rustem.weatherinkazan.adapter.holder.WeatherViewHolder;
import com.rustem.rustem.weatherinkazan.model.DataDay;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherViewHolder> {

    private List<DataDay> dataDays;
    private HashMap<Long, Double> externalData;
    private Context mContext;

    public WeatherAdapter(Context mContext) {
        this.dataDays = new ArrayList<>();
        this.mContext = mContext;
        externalData = new HashMap<>();
    }

    @Override
    public WeatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View inflate = layoutInflater.inflate(R.layout.weather_recycler_view, parent, false);
        return new WeatherViewHolder(inflate, externalData, mContext);
    }

    @Override
    public void onBindViewHolder(WeatherViewHolder holder, int position) {
        holder.bind(dataDays.get(position));
    }

    @Override
    public int getItemCount() {
        return dataDays.size();
    }

    public void updateData(List<DataDay> list) {
        externalData.clear();
        this.dataDays.clear();
        this.dataDays.addAll(list);
        notifyDataSetChanged();
    }
}
