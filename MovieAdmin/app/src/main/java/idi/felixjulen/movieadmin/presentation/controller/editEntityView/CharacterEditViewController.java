package idi.felixjulen.movieadmin.presentation.controller.editEntityView;

import android.content.Intent;
import android.os.Bundle;

import idi.felixjulen.movieadmin.R;
import idi.felixjulen.movieadmin.domain.controller.CharacterData;
import idi.felixjulen.movieadmin.domain.model.Character;
import idi.felixjulen.movieadmin.presentation.controller.singleEntityView.CharacterViewController;

public class CharacterEditViewController extends EntityEditViewController<Character> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        layoutResourceId = R.layout.entity_edit_view;
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void update() {
        CharacterData.getInstance(this).update(data);
        finish();
    }

    @Override
    protected void save() {
        Long newId = CharacterData.getInstance(this).add(data);
        Intent intent = new Intent(this, CharacterViewController.class);
        intent.putExtra(getString(R.string.itemEntityId), newId);
        startActivity(intent);
        finish();
    }

    @Override
    protected Character getData(Long id) {
        return CharacterData.getInstance(this).get(id);
    }

    @Override
    protected Character newData() {
        return new Character();
    }

}
