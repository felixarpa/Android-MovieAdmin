package idi.felixjulen.movieadmin.presentation.controller.listView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import idi.felixjulen.movieadmin.R;
import idi.felixjulen.movieadmin.domain.controller.FilmData;
import idi.felixjulen.movieadmin.domain.model.Film;
import idi.felixjulen.movieadmin.presentation.adapter.FilmRecyclerViewAdapter;
import idi.felixjulen.movieadmin.presentation.controller.editEntityView.MovieEditViewController;
import idi.felixjulen.movieadmin.presentation.controller.singleEntityView.MovieViewController;

public class MovieListViewController extends EntityListViewController<Film> implements AdapterView.OnItemSelectedListener {

    private Spinner orderBy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        layoutResourceId = R.layout.movie_list_view;
        rowLayoutResourceId = R.layout.movie_row_layout;
        titleResourceId = R.string.app_name;
        super.onCreate(savedInstanceState);
        orderBy = (Spinner) findViewById(R.id.order_by);
        String[] sort = new String[] { "Title", "Year", "Country" };
        ArrayAdapter<String> orderAdapter = new ArrayAdapter<>( this, android.R.layout.simple_spinner_item, sort);
        orderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        orderBy.setAdapter(orderAdapter);
        orderBy.setOnItemSelectedListener(this);
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
    protected void addEntity() {
        Intent intent = new Intent(this, MovieEditViewController.class);
        intent.putExtra(getString(R.string.itemEntityId), -1L);
        startActivity(intent);
    }

    @Override
    protected ArrayList<Film> loadData() {
        orderBy.setSelection(0);
        ArrayList<Film> list = FilmData.getInstance(this).list();
        Collections.sort(list, new Comparator<Film>() {
                @Override
                public int compare(Film f1, Film f2) {
                    return f1.getTitle().compareTo(f2.getTitle());
                }
        });
        return list;
    }

    @Override
    protected Class<?> entityActivity() {
        return MovieViewController.class;
    }

    protected void setAdapterToRecyclerView() {
        FilmRecyclerViewAdapter adapter = new FilmRecyclerViewAdapter(data, this);
        adapter.setContext(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        final Integer pos = position;
        ArrayList<Film> list = FilmData.getInstance(this).list();
        Collections.sort(list, new Comparator<Film>() {
            @Override
            public int compare(Film f1, Film f2) {
                switch (pos) {
                    case 1:
                        return f1.getYear().compareTo(f2.getYear());

                    case 2:
                        return f1.getCountry().compareTo(f2.getCountry());

                     default:
                        return f1.getTitle().compareTo(f2.getTitle());
                }
            }
        });
        FilmRecyclerViewAdapter adapter = new FilmRecyclerViewAdapter(list, this);
        adapter.setContext(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
