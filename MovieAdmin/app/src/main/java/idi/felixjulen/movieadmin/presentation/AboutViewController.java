package idi.felixjulen.movieadmin.presentation;

import android.os.Bundle;

import idi.felixjulen.movieadmin.R;

public class AboutViewController extends BaseViewController {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.about_view, frameLayout, true);
        toolbar.setTitle(R.string.about);
    }

    @Override
    protected Integer getMenuPosition() {
        return 4;
    }

    @Override
    protected Integer getMenuId() {
        return R.id.about_item;
    }
}
