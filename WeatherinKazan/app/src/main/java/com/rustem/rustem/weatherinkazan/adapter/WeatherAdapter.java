package com.rustem.rustem.weatherinkazan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rustem.rustem.weatherinkazan.R;
import com.rustem.rustem.weatherinkazan.model.DataDay;
import com.squareup.picasso.Picasso;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder> {

    private List<DataDay> dataDays;

    private Context mContext;

    public WeatherAdapter(Context mContext) {
        this.dataDays = new ArrayList<>();
        this.mContext = mContext;
    }

    @Override
    public WeatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View inflate = layoutInflater.inflate(R.layout.weather_recycler_view, parent, false);
        return new WeatherViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(WeatherViewHolder holder, int position) {
        DataDay dataDay = dataDays.get(position);
        holder.date.setText(longToDate(dataDay.getDt() * 1000L));
        holder.morning.setText(formateToString(dataDay.getTemp().getMorning()));
        holder.day.setText(formateToString(dataDay.getTemp().getDay()));
        holder.evening.setText(formateToString(dataDay.getTemp().getEvening()));
        holder.night.setText(formateToString(dataDay.getTemp().getNight()));


        String icon = dataDay.getWeather().get(0).getIcon();
        String iconUrl = "http://openweathermap.org/img/w/" + icon + ".png";

        Picasso.with(mContext)
                .load(iconUrl)
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return dataDays.size();
    }

    public void updateData(List<DataDay> list) {
        this.dataDays.clear();
        this.dataDays.addAll(list);
        notifyDataSetChanged();
    }

    private String longToDate(long time) {
        Date date = new Date(time);
        Format format = new SimpleDateFormat("dd.MM.yyyy");
        return format.format(date);
    }

    private String formateToString(double d) {
        if (d >= 0) {
            return String.format("+%.0f \u2103", d);
        }
        return String.format("%.0f \u2103", d);
    }

    class WeatherViewHolder extends RecyclerView.ViewHolder {

        TextView date;
        TextView morning;
        TextView day;
        TextView evening;
        TextView night;
        ImageView image;

        public WeatherViewHolder(View itemView) {
            super(itemView);

            date = (TextView) itemView.findViewById(R.id.date);
            morning = (TextView) itemView.findViewById(R.id.morning_degrees);
            day = (TextView) itemView.findViewById(R.id.day_degrees);
            evening = (TextView) itemView.findViewById(R.id.evening_degrees);
            night = (TextView) itemView.findViewById(R.id.night_degrees);
            image = (ImageView) itemView.findViewById(R.id.image_weather);
        }
    }
}
