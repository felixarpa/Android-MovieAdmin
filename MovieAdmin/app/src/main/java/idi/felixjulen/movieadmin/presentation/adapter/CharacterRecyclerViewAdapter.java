package idi.felixjulen.movieadmin.presentation.adapter;

import android.view.View;

import java.util.ArrayList;

import idi.felixjulen.movieadmin.R;
import idi.felixjulen.movieadmin.domain.model.Character;
import idi.felixjulen.movieadmin.presentation.callback.OnRecyclerViewItemAction;


public class CharacterRecyclerViewAdapter extends EntityRecyclerViewAdapter<EntityViewHolder, Character> {

    public CharacterRecyclerViewAdapter(ArrayList<Character> directors, OnRecyclerViewItemAction callback) {
        super(R.layout.character_row_layout, directors, callback);
    }

    @Override
    protected EntityViewHolder newT(View view) {
        return new EntityViewHolder(view);
    }
}