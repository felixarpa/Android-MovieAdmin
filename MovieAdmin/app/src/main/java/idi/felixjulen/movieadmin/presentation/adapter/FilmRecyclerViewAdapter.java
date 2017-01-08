package idi.felixjulen.movieadmin.presentation.adapter;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import idi.felixjulen.movieadmin.R;
import idi.felixjulen.movieadmin.domain.controller.FileManager;
import idi.felixjulen.movieadmin.domain.model.Film;
import idi.felixjulen.movieadmin.presentation.callback.OnRecyclerViewItemAction;

public class FilmRecyclerViewAdapter extends EntityRecyclerViewAdapter<FilmRecyclerViewAdapter.FilmViewHolder, Film> {

    public FilmRecyclerViewAdapter(ArrayList<Film> data, OnRecyclerViewItemAction callback) {
        super(R.layout.movie_row_layout, data, callback);
    }

    @Override
    protected FilmViewHolder newT(View view) {
        return new FilmViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FilmViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        FileManager fm = FileManager.getInstance(context);
        Bitmap image = fm.loadImageFromStorage(data.get(position).getImage(), R.mipmap.film);
        if (image != null) {
            holder.imageView.setImageBitmap(image);
        }
        holder.rateView.setText(String.valueOf(data.get(position).getRate()));
        holder.countryView.setText(data.get(position).getCountry());
        holder.yearView.setText(String.valueOf(data.get(position).getYear()));
    }

    class FilmViewHolder extends EntityViewHolder {

        TextView rateView;
        TextView countryView;
        TextView yearView;

        FilmViewHolder(View itemView) {
            super(itemView);
            this.nameView = (TextView) this.itemView.findViewById(R.id.title);
            this.rateView = (TextView) this.itemView.findViewById(R.id.rate);
            this.countryView = (TextView) this.itemView.findViewById(R.id.country);
            this.yearView = (TextView) this.itemView.findViewById(R.id.year);

        }
    }
}

