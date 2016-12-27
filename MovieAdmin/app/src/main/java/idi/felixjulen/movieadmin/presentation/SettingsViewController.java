package idi.felixjulen.movieadmin.presentation;

import android.os.Bundle;

import idi.felixjulen.movieadmin.R;

public class SettingsViewController extends BaseViewController {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.settings_view, frameLayout, true);
    }

    @Override
    protected Integer getMenuPosition() {
        return 2;
    }

    @Override
    protected Integer getMenuId() {
        return R.id.settings_item;
    }
}
