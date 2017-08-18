package com.gdgkazan.punkapi;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gdgkazan.punkapi.models.Beer;
import com.squareup.picasso.Picasso;

import java.util.List;


public class BeersAdapter extends RecyclerView.Adapter<BeersViewHolder> {

    private List<Beer> beers;

    private Context mContext;

    public BeersAdapter(List<Beer> beers, Context mContext) {
        this.beers = beers;
        this.mContext = mContext;
    }

    @Override
    public BeersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View inflate = layoutInflater.inflate(R.layout.beer_holder, parent, false);
        return new BeersViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(BeersViewHolder holder, int position) {
        Beer beer = beers.get(position);
        Picasso.with(mContext)
                .load(beer.getImageUrl())
                .into(holder.ivBeer);

        holder.beerName.setText(beer.getName());
        holder.beerTagLine.setText(beer.getTagLine());
        holder.beerAbv.setText(String.valueOf(beer.getAbv()));
        holder.beerIbu.setText(String.valueOf(beer.getIbu()));
        holder.beerSrm.setText(String.valueOf(beer.getSrm()));
        holder.beerDescription.setText(beer.getDescription());
    }

    public void updateData(List<Beer> beers) {
        this.beers.clear();
        this.beers.addAll(beers);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return beers.size();
    }
}
