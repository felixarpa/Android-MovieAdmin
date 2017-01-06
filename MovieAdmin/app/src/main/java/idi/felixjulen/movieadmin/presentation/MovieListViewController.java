package idi.felixjulen.movieadmin.presentation;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import idi.felixjulen.movieadmin.R;
import idi.felixjulen.movieadmin.domain.controller.FilmData;
import idi.felixjulen.movieadmin.domain.model.Film;
import idi.felixjulen.movieadmin.presentation.adapter.FilmRecyclerViewAdapter;
import idi.felixjulen.movieadmin.presentation.callback.OnRecyclerViewItemAction;

public class MovieListViewController extends BaseViewController implements OnRecyclerViewItemAction {

    private RecyclerView recyclerView;
    private TextView emptyTextView;
    private FilmData data;
    private ArrayList<Film> films;
    private FilmRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentFrame(R.layout.movie_list_view);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        emptyTextView = (TextView) findViewById(R.id.empty);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        findViewById(R.id.add_movie).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                }
        );

        data = FilmData.getInstance(this);

        setListContent();
    }

    private void setListContent() {
        films = data.list();
        adapter = new FilmRecyclerViewAdapter(films, this);
        if (films.size() == 0) {
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
        return 0;
    }

    @Override
    protected Integer getMenuId() {
        return R.id.movie_list_item;
    }

    @Override
    public void onRecyclerViewItemClick(Integer position, Long itemEntityId) {
//        Intent intent = new Intent(this, CharacterViewController.class);
//        intent.putExtra(getString(R.string.itemEntityId), itemEntityId);
//        startActivity(intent);
    }
}
