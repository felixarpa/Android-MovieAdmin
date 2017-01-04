package idi.felixjulen.movieadmin.presentation.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import idi.felixjulen.movieadmin.R;
import idi.felixjulen.movieadmin.domain.model.Film;
import idi.felixjulen.movieadmin.presentation.callback.OnRecyclerViewItemClick;

public class FilmRecyclerViewAdapter extends RecyclerView.Adapter<FilmRecyclerViewAdapter.AdapterViewHolder> {

    private ArrayList<Film> films;
    private OnRecyclerViewItemClick callback;

    public FilmRecyclerViewAdapter(ArrayList<Film> films, OnRecyclerViewItemClick callback) {
        this.films = films;
        this.callback = callback;
    }

    @Override
    public AdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.film_row_layout, parent, false);
        return new AdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterViewHolder holder, int position) {
        final Integer itemPosition = position;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onRecyclerViewItemClick(itemPosition, films.get(itemPosition).getId());
            }
        });
        holder.nameView.setText(films.get(position).getTitle());
        holder.rateView.setText(films.get(position).getRate());
    }

    @Override
    public int getItemCount() {
        return films.size();
    }

    class AdapterViewHolder extends RecyclerView.ViewHolder {

        private View itemView;
        private TextView nameView;
        private TextView rateView;

        public AdapterViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            this.nameView = (TextView) this.itemView.findViewById(R.id.title);
            this.rateView = (TextView) this.itemView.findViewById(R.id.rate);
        }
    }
}

