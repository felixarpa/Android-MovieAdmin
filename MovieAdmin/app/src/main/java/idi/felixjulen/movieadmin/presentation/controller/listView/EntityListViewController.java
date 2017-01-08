package idi.felixjulen.movieadmin.presentation.controller.listView;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import idi.felixjulen.movieadmin.R;
import idi.felixjulen.movieadmin.domain.model.Entity;
import idi.felixjulen.movieadmin.presentation.controller.BaseViewController;
import idi.felixjulen.movieadmin.presentation.adapter.EntityRecyclerViewAdapter;
import idi.felixjulen.movieadmin.presentation.callback.OnRecyclerViewItemAction;

public abstract class EntityListViewController<T extends Entity> extends BaseViewController implements OnRecyclerViewItemAction {

    protected RecyclerView recyclerView;
    private TextView emptyTextView;
    protected ArrayList<T> data;

    protected Integer layoutResourceId;
    protected Integer titleResourceId;
    protected Integer rowLayoutResourceId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentFrame(layoutResourceId);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(titleResourceId);
        }

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        emptyTextView = (TextView) findViewById(R.id.empty);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.add);
        fab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        addEntity();
                    }
                }
        );

    }

    @Override
    protected void onResume() {
        setListContent();
        super.onResume();
    }

    protected void setListContent() {
        data = loadData();
        if (data.size() == 0) {
            emptyTextView.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            emptyTextView.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            setAdapterToRecyclerView();
        }
    }

    @Override
    public void onRecyclerViewItemClick(Long itemEntityId) {
        Intent intent = new Intent(this, entityActivity());
        intent.putExtra(getString(R.string.itemEntityId), itemEntityId);
        startActivity(intent);
    }

    protected abstract void addEntity();
    protected abstract ArrayList<T> loadData();
    protected abstract Class<?> entityActivity();

    protected void setAdapterToRecyclerView() {
        recyclerView.setAdapter(new EntityRecyclerViewAdapter<>(rowLayoutResourceId, data, this, this));
    }
}
