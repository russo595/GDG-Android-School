package com.rustem.rustem.weatherinkazan.adapter.holder;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rustem.rustem.weatherinkazan.R;
import com.rustem.rustem.weatherinkazan.activity.GraphicActivity;
import com.rustem.rustem.weatherinkazan.model.DataDay;
import com.squareup.picasso.Picasso;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class WeatherViewHolder extends RecyclerView.ViewHolder {

    private HashMap<Long, Double> externalData;
    private Context mContext;
    private TextView date;
    private TextView morning;
    private TextView day;
    private TextView evening;
    private TextView night;
    private ImageView image;
    private CardView card;

    public WeatherViewHolder(View itemView, HashMap<Long, Double> externalData, Context mContext) {
        super(itemView);
        this.externalData = externalData;
        this.mContext = mContext;

        date = itemView.findViewById(R.id.date);
        morning = itemView.findViewById(R.id.morning_degrees);
        day = itemView.findViewById(R.id.day_degrees);
        evening = itemView.findViewById(R.id.evening_degrees);
        night = itemView.findViewById(R.id.night_degrees);
        image = itemView.findViewById(R.id.image_weather);
        card = itemView.findViewById(R.id.card);
    }

    public void bind(DataDay dataDay) {
        final long time = dataDay.getDt() * 1000L;
        date.setText(longToDate(time));
        morning.setText(formateToString(dataDay.getTemp().getMorning()));
        double dayTemp = dataDay.getTemp().getDay();
        day.setText(formateToString(dayTemp));
        evening.setText(formateToString(dataDay.getTemp().getEvening()));
        night.setText(formateToString(dataDay.getTemp().getNight()));

        externalData.put(time, dayTemp);

        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), GraphicActivity.class);
                intent.putExtra("map", externalData);
                v.getContext().startActivity(intent);
            }
        });

        String icon = dataDay.getWeather().get(0).getIcon();
        String iconUrl = "http://openweathermap.org/img/w/" + icon + ".png";

        Picasso.with(mContext)
                .load(iconUrl)
                .into(image);
    }

    private String longToDate(long time) {
        Date date = new Date(time);
        Format format = new SimpleDateFormat("dd.MM.yyyy");
        return format.format(date);
    }

    private String formateToString(double d) {
        if (d >= 0) {
            return String.format("+%.0f\u2103", d);
        }
        return String.format("%.0f\u2103", d);
    }
}
