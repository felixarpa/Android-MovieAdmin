package idi.felixjulen.movieadmin.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import idi.felixjulen.movieadmin.R;
import idi.felixjulen.movieadmin.domain.controller.FilmData;
import idi.felixjulen.movieadmin.presentation.callback.OnRecyclerViewItemClick;

public class MovieListViewController extends BaseViewController implements OnRecyclerViewItemClick {

    private RecyclerView recyclerView;
    private FilmData data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentFrame(R.layout.movie_list_view);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        findViewById(R.id.add_character).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                }
        );

//        data = FilmData.getInstance(this);
//        data.makeDefault();
//
//        ArrayList<Character> characters = data.list();
//        CharacterRecyclerViewAdapter adapter = new CharacterRecyclerViewAdapter(characters, this);
//        if (characters.size() == 0) {
//            recyclerView.setVisibility(View.GONE);
//        } else {
//            recyclerView.setVisibility(View.VISIBLE);
//            recyclerView.setAdapter(adapter);
//        }
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
        Intent intent = new Intent(this, CharacterViewController.class);
        intent.putExtra(getString(R.string.itemEntityId), itemEntityId);
        startActivity(intent);
    }
}
