package idi.felixjulen.movieadmin.presentation;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import idi.felixjulen.movieadmin.R;
import idi.felixjulen.movieadmin.domain.controller.CharacterData;
import idi.felixjulen.movieadmin.domain.controller.FilmData;
import idi.felixjulen.movieadmin.domain.model.Character;
import idi.felixjulen.movieadmin.domain.model.Film;
import idi.felixjulen.movieadmin.presentation.adapter.FilmRecyclerViewAdapter;
import idi.felixjulen.movieadmin.presentation.callback.OnRecyclerViewItemAction;

public class CharacterViewController extends EntityViewController implements OnRecyclerViewItemAction {

    private Character character;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        layoutResourceId = R.layout.character_view;
        super.onCreate(savedInstanceState);
    }

    protected void setUpEntity() {

        character = CharacterData.getInstance(this).get(id);
        ArrayList<Film> films = FilmData.getInstance(this).getWithCharacter(id);

        TextView nameTextView = (TextView) findViewById(R.id.name);
        nameTextView.setText(character.getName());

        ImageView imageView = (ImageView) findViewById(R.id.image);
        imageView.setImageBitmap(character.getImage());

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
        new AlertDialog.Builder(this)
                .setTitle(R.string.delete_character_title)
                .setMessage(R.string.delete_character_message)
                .setPositiveButton(
                        "Hell yeah!",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                CharacterData.getInstance(getApplicationContext()).delete(character.getId());
                                finish();
                            }
                        })
                .setNeutralButton(
                        "Oh, no",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                .show();
    }

    @Override
    public void onRecyclerViewItemClick(Long itemEntityId) {
        if (enableNavigation) {
            Intent intent = new Intent(this, MovieViewController.class);
            intent.putExtra(getString(R.string.itemEntityId), clickedEntityId);
            intent.putExtra(getString(R.string.enable_navigation), false);
            startActivity(intent);
        }
    }
}
