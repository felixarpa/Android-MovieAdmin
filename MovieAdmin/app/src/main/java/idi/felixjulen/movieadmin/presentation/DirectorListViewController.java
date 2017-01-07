package idi.felixjulen.movieadmin.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import idi.felixjulen.movieadmin.R;
import idi.felixjulen.movieadmin.domain.controller.DirectorData;
import idi.felixjulen.movieadmin.domain.model.Director;
import idi.felixjulen.movieadmin.presentation.adapter.DirectorRecyclerViewAdapter;
import idi.felixjulen.movieadmin.presentation.callback.OnRecyclerViewItemAction;

public class DirectorListViewController extends BaseViewController implements OnRecyclerViewItemAction {

    private RecyclerView recyclerView;
    private TextView emptyTextView;
    private DirectorData data;
    private ArrayList<Director> directors;
    private DirectorRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentFrame(R.layout.movie_list_view);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        emptyTextView = (TextView) findViewById(R.id.empty);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        findViewById(R.id.add_movie).setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//
//                    }
//                }
//        );

    }

    @Override
    protected void onResume() {
        data = DirectorData.getInstance(this);
        setListContent();
        super.onResume();
    }

    private void setListContent() {
        directors = data.list();
        adapter = new DirectorRecyclerViewAdapter(directors, this);
        if (directors.size() == 0) {
            emptyTextView.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            emptyTextView.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            recyclerView.setAdapter(adapter);
            registerForContextMenu(recyclerView);
        }
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
    public void onRecyclerViewItemClick(Long itemEntityId) {
        Intent intent = new Intent(this, MovieViewController.class);
        intent.putExtra(getString(R.string.itemEntityId), itemEntityId);
        intent.putExtra(getString(R.string.enable_navigation), true);
        startActivity(intent);
    }
}
