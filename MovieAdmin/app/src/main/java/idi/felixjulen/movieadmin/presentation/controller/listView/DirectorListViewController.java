package idi.felixjulen.movieadmin.presentation.controller.listView;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;

import java.util.ArrayList;

import idi.felixjulen.movieadmin.R;
import idi.felixjulen.movieadmin.domain.controller.DirectorData;
import idi.felixjulen.movieadmin.domain.model.Director;
import idi.felixjulen.movieadmin.presentation.callback.OnRecyclerViewItemAction;
import idi.felixjulen.movieadmin.presentation.controller.singleEntityView.DirectorViewController;

public class DirectorListViewController extends EntityListViewController<Director> implements OnRecyclerViewItemAction {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        layoutResourceId = R.layout.entity_list_view;
        rowLayoutResourceId = R.layout.entity_row_layout;
        titleResourceId = R.string.directors;
        super.onCreate(savedInstanceState);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.add);
        fab.setImageResource(R.drawable.account_plus);
    }

    @Override
    protected Integer getMenuPosition() {
        return 2;
    }

    @Override
    protected Integer getMenuId() {
        return R.id.director_list_item;
    }

    @Override
    protected void addEntity() {

    }

    @Override
    protected ArrayList<Director> loadData() {
        return DirectorData.getInstance(this).list();
    }

    @Override
    protected Class<?> entityActivity() {
        return DirectorViewController.class;
    }
}
