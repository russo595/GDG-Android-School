package com.example.rustem.recyclerapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class CitiesViewHolder extends RecyclerView.ViewHolder {

    TextView cityNameView;
    TextView countryNameView;
    ImageButton likeButton;
    ImageButton dislikeButton;
    TextView likeCounter;
    TextView dislikeCounter;
    TextView countryCode;
    ImageView flag;

    CitiesViewHolder(View itemView) {
        super(itemView);

        cityNameView = (TextView) itemView.findViewById(R.id.city_name);
        countryNameView = (TextView) itemView.findViewById(R.id.country_name);
        likeButton = (ImageButton) itemView.findViewById(R.id.like_button);
        dislikeButton = (ImageButton) itemView.findViewById(R.id.dislike_button);
        likeCounter = (TextView) itemView.findViewById(R.id.counter_like);
        dislikeCounter = (TextView) itemView.findViewById(R.id.counter_dislike);
        countryCode = (TextView) itemView.findViewById(R.id.country_code);
        flag = (ImageView) itemView.findViewById(R.id.flag);
    }
}
