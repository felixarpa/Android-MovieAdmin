package idi.felixjulen.movieadmin.presentation.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import idi.felixjulen.movieadmin.R;
import idi.felixjulen.movieadmin.domain.model.Film;
import idi.felixjulen.movieadmin.presentation.callback.OnRecyclerViewItemAction;

public class FilmRecyclerViewAdapter extends RecyclerView.Adapter<FilmRecyclerViewAdapter.AdapterViewHolder> {

    private ArrayList<Film> films;
    private OnRecyclerViewItemAction callback;

    public FilmRecyclerViewAdapter(ArrayList<Film> films, OnRecyclerViewItemAction callback) {
        this.films = films;
        this.callback = callback;
    }

    @Override
    public AdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.movie_row_layout, parent, false);

        return new AdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterViewHolder holder, final int position) {
        final Integer itemPosition = position;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onRecyclerViewItemClick(films.get(itemPosition).getId());
            }
        });
        holder.titleView.setText(films.get(position).getTitle());
        if (films.get(position).getImage() != null){
            holder.imageView.setImageBitmap(films.get(position).getImage());
        }
        holder.rateView.setText(String.valueOf(films.get(position).getRate()));
        holder.countryView.setText(films.get(position).getCountry());
        holder.yearView.setText(String.valueOf(films.get(position).getYear()));
    }

    @Override
    public void onViewRecycled(AdapterViewHolder holder) {
        holder.itemView.setOnClickListener(null);
        super.onViewRecycled(holder);
    }

    @Override
    public int getItemCount() {
        return films.size();
    }

    class AdapterViewHolder extends RecyclerView.ViewHolder {

        private View itemView;
        private TextView titleView;
        private TextView rateView;
        private TextView countryView;
        private TextView yearView;
        private ImageView imageView;

        public AdapterViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            this.titleView = (TextView) this.itemView.findViewById(R.id.title);
            this.rateView = (TextView) this.itemView.findViewById(R.id.rate);
            this.countryView = (TextView) this.itemView.findViewById(R.id.country);
            this.yearView = (TextView) this.itemView.findViewById(R.id.year);
            this.imageView = (ImageView) this.itemView.findViewById(R.id.image);
        }
    }
}

