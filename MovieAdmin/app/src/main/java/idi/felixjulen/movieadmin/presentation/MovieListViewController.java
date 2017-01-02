package idi.felixjulen.movieadmin.presentation;

import android.os.Bundle;

import idi.felixjulen.movieadmin.R;

public class MovieListViewController extends BaseViewController {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentFrame(R.layout.movie_list_view);

    }

    @Override
    protected Integer getMenuPosition() {
        return 0;
    }

    @Override
    protected Integer getMenuId() {
        return R.id.movie_list_item;
    }
}
