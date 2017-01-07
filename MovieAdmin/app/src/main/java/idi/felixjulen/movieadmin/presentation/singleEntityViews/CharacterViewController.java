package idi.felixjulen.movieadmin.presentation.singleEntityViews;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;

import idi.felixjulen.movieadmin.R;
import idi.felixjulen.movieadmin.domain.controller.CharacterData;
import idi.felixjulen.movieadmin.domain.controller.FilmData;
import idi.felixjulen.movieadmin.domain.model.Character;
import idi.felixjulen.movieadmin.domain.model.Film;
import idi.felixjulen.movieadmin.presentation.adapter.FilmRecyclerViewAdapter;
import idi.felixjulen.movieadmin.presentation.callback.OnRecyclerViewItemAction;

public class CharacterViewController extends EntityViewController<Character> implements OnRecyclerViewItemAction {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        layoutResourceId = R.layout.entity_view;
        removeTitle = R.string.delete_character_title;
        removeMessage = R.string.delete_character_message;
        removeYes = R.string.hell_yeah;
        removeNo = R.string.oh_no;
        super.onCreate(savedInstanceState);

        ArrayList<Film> films = FilmData.getInstance(this).getWithCharacter(id);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        if (films.size() > 0) {
            FilmRecyclerViewAdapter adapter = new FilmRecyclerViewAdapter(films, this);
            recyclerView.setAdapter(adapter);
        } else {
            TextView moviesHeader = (TextView) findViewById(R.id.movies_header);
            FrameLayout splitLayout = (FrameLayout) findViewById(R.id.split);
            moviesHeader.setVisibility(View.GONE);
            splitLayout.setVisibility(View.GONE);
        }

    }

    @Override
    protected void editEntity() {

    }

    @Override
    protected void removeEntity() {
        CharacterData.getInstance(this).delete(data.getId());
    }

    @Override
    protected Character getData() {
        return CharacterData.getInstance(this).get(id);
    }

    @Override
    public void onRecyclerViewItemClick(Long itemEntityId) {
        Intent intent = new Intent(this, MovieViewController.class);
        intent.putExtra(getString(R.string.itemEntityId), clickedEntityId);
        startActivity(intent);
    }
}
