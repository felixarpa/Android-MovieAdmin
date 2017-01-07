package idi.felixjulen.movieadmin.presentation.adapter;

import android.view.View;

import java.util.ArrayList;

import idi.felixjulen.movieadmin.R;
import idi.felixjulen.movieadmin.domain.model.Director;
import idi.felixjulen.movieadmin.presentation.callback.OnRecyclerViewItemAction;


public class DirectorRecyclerViewAdapter extends EntityRecyclerViewAdapter<EntityViewHolder, Director> {

    public DirectorRecyclerViewAdapter(ArrayList<Director> directors, OnRecyclerViewItemAction callback) {
        super(R.layout.director_row_layout, directors, callback);
    }

    @Override
    protected EntityViewHolder newT(View view) {
        return new EntityViewHolder(view);
    }
}
