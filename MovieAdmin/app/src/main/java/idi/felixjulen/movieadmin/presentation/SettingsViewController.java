package idi.felixjulen.movieadmin.presentation;

import android.os.Bundle;

import idi.felixjulen.movieadmin.R;

public class SettingsViewController extends BaseViewController {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentFrame(R.layout.settings_view);
    }

    @Override
    protected Integer getMenuPosition() {
        return 3;
    }

    @Override
    protected Integer getMenuId() {
        return R.id.settings_item;
    }
}
