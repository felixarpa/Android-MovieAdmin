package idi.felixjulen.movieadmin.presentation.controller;

import android.os.Bundle;

import idi.felixjulen.movieadmin.R;

public class HelpViewController extends BaseViewController {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentFrame(R.layout.help_view);

    }

    @Override
    protected Integer getMenuPosition() {
        return 3;
    }

    @Override
    protected Integer getMenuId() {
        return R.id.help_item;
    }
}
