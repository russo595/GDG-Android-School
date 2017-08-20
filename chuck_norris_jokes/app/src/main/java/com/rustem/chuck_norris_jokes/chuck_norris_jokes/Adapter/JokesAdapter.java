package com.rustem.chuck_norris_jokes.chuck_norris_jokes.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rustem.chuck_norris_jokes.R;
import com.rustem.chuck_norris_jokes.chuck_norris_jokes.model.Value;

import java.util.ArrayList;
import java.util.List;

public class JokesAdapter extends RecyclerView.Adapter<JokesAdapter.JokesViewHolder> {

    private List<Value> values;

    public JokesAdapter() {
        this.values = new ArrayList<>();
    }

    @Override
    public JokesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View inflate = layoutInflater.inflate(R.layout.chuck_norris, parent, false);
        return new JokesViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(JokesViewHolder holder, int position) {
        Value value = values.get(position);
        holder.post.setText(value.getJoke());
        holder.idPost.setText(String.format("# %s", String.valueOf(value.getId())));
    }

    public void updateData(List<Value> values) {
        this.values.clear();
        this.values.addAll(values);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    class JokesViewHolder extends RecyclerView.ViewHolder {

        TextView post;
        TextView idPost;

        public JokesViewHolder(View itemView) {
            super(itemView);

            post = (TextView) itemView.findViewById(R.id.postitem_post);
            idPost = (TextView) itemView.findViewById(R.id.id_post);
        }
    }
}
