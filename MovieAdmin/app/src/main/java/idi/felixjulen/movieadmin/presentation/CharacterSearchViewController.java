package idi.felixjulen.movieadmin.presentation;

import android.os.Bundle;

import idi.felixjulen.movieadmin.R;

public class CharacterSearchViewController extends BaseViewController {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.character_search_view, frameLayout, true);
    }

    @Override
    protected Integer getMenuPosition() {
        return 1;
    }

    @Override
    protected Integer getMenuId() {
        return R.id.character_search_item;
    }
}
