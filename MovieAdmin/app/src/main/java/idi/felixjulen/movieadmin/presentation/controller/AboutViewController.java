package idi.felixjulen.movieadmin.presentation.controller;

import android.os.Bundle;

import idi.felixjulen.movieadmin.R;

public class AboutViewController extends BaseViewController {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentFrame(R.layout.about_view);

    }

    @Override
    protected Integer getMenuPosition() {
        return 5;
    }

    @Override
    protected Integer getMenuId() {
        return R.id.about_item;
    }
}
