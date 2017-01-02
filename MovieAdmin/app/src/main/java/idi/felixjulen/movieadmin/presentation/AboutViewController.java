package idi.felixjulen.movieadmin.presentation;

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
        return 4;
    }

    @Override
    protected Integer getMenuId() {
        return R.id.about_item;
    }
}
