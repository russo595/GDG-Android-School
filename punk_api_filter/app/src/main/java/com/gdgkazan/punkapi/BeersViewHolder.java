package com.gdgkazan.punkapi;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class BeersViewHolder extends RecyclerView.ViewHolder {

    ImageView ivBeer;
    TextView beerName;
    TextView beerTagLine;
    TextView beerAbv;
    TextView beerIbu;
    TextView beerSrm;
    TextView beerDescription;


    public BeersViewHolder(View itemView) {
        super(itemView);

        ivBeer = (ImageView) itemView.findViewById(R.id.ivBeer);
        beerName = (TextView) itemView.findViewById(R.id.tvBeerName);
        beerTagLine = (TextView) itemView.findViewById(R.id.tvBeerTagLine);
        beerAbv = (TextView) itemView.findViewById(R.id.tvBeerAbv);
        beerIbu = (TextView) itemView.findViewById(R.id.tvBeerIbu);
        beerSrm = (TextView) itemView.findViewById(R.id.tvBeerSrm);
        beerDescription = (TextView) itemView.findViewById(R.id.tvBeerDescription);

    }
}
