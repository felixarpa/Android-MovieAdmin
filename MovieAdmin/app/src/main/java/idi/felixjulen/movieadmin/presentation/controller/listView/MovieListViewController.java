package idi.felixjulen.movieadmin.presentation.controller.listView;

import android.os.Bundle;

import java.util.ArrayList;

import idi.felixjulen.movieadmin.R;
import idi.felixjulen.movieadmin.domain.controller.FilmData;
import idi.felixjulen.movieadmin.domain.model.Film;
import idi.felixjulen.movieadmin.presentation.adapter.FilmRecyclerViewAdapter;
import idi.felixjulen.movieadmin.presentation.controller.singleEntityView.MovieViewController;

public class MovieListViewController extends EntityListViewController<Film> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        layoutResourceId = R.layout.entity_list_view;
        rowLayoutResourceId = R.layout.movie_row_layout;
        titleResourceId = R.string.app_name;
        super.onCreate(savedInstanceState);
    }

    @Override
    protected Integer getMenuPosition() {
        return 0;
    }

    @Override
    protected Integer getMenuId() {
        return R.id.movie_list_item;
    }

    @Override
    protected void addEntity() {

    }

    @Override
    protected ArrayList<Film> loadData() {
        return FilmData.getInstance(this).list();
    }

    @Override
    protected Class<?> entityActivity() {
        return MovieViewController.class;
    }

    protected void setAdapterToRecyclerView() {
        recyclerView.setAdapter(new FilmRecyclerViewAdapter(data, this));
    }
}
