package idi.felixjulen.movieadmin.presentation.controller.editEntityView;

import android.content.Intent;
import android.os.Bundle;

import idi.felixjulen.movieadmin.R;
import idi.felixjulen.movieadmin.domain.controller.FilmData;
import idi.felixjulen.movieadmin.domain.model.Film;
import idi.felixjulen.movieadmin.presentation.controller.singleEntityView.MovieViewController;

public class MovieEditViewController extends EntityEditViewController<Film> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        layoutResourceId = R.layout.movie_edit_view;
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void update() {
        FilmData.getInstance(this).update(data);
        finish();
    }

    @Override
    protected void save() {
        Long newId = FilmData.getInstance(this).add(data);
        Intent intent = new Intent(this, MovieViewController.class);
        intent.putExtra(getString(R.string.itemEntityId), newId);
        startActivity(intent);
        finish();
    }

    @Override
    protected Film getData(Long id) {
        return FilmData.getInstance(this).get(id);
    }

    @Override
    protected Film newData() {
        return new Film();
    }

}
