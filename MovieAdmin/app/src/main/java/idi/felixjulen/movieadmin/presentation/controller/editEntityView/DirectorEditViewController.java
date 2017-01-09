package idi.felixjulen.movieadmin.presentation.controller.editEntityView;

import android.content.Intent;
import android.os.Bundle;

import idi.felixjulen.movieadmin.R;
import idi.felixjulen.movieadmin.domain.controller.DirectorData;
import idi.felixjulen.movieadmin.domain.model.Director;
import idi.felixjulen.movieadmin.presentation.controller.singleEntityView.DirectorViewController;

public class DirectorEditViewController extends EntityEditViewController<Director> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        layoutResourceId = R.layout.entity_edit_view;
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void update() {
        DirectorData.getInstance(this).update(data);
        finish();
    }

    @Override
    protected void save() {
        Long newId = DirectorData.getInstance(this).add(data);
        Intent intent = new Intent(this, DirectorViewController.class);
        intent.putExtra(getString(R.string.itemEntityId), newId);
        startActivity(intent);
        finish();
    }

    @Override
    protected Director getData(Long id) {
        return DirectorData.getInstance(this).get(id);
    }

    @Override
    protected Director newData() {
        return new Director();
    }

}
