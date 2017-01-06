package idi.felixjulen.movieadmin.presentation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import idi.felixjulen.movieadmin.R;
import idi.felixjulen.movieadmin.domain.controller.CharacterData;
import idi.felixjulen.movieadmin.domain.controller.FilmData;
import idi.felixjulen.movieadmin.domain.model.Character;
import idi.felixjulen.movieadmin.domain.model.Film;

public class CharacterViewController extends AppCompatActivity {

    private Character character;
    private ArrayList<Film> films;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.character_view);
        //toggle.onConfigurationChanged();

        Long id = getIntent().getExtras().getLong(getString(R.string.itemEntityId), -1);
        if (id == -1) finish();

        character = CharacterData.getInstance(this).get(id);
        films = FilmData.getInstance(this).getWithCharacter(id);

        TextView nameTextView = (TextView) findViewById(R.id.name);
        nameTextView.setText(character.getName());

        ImageView imageView = (ImageView) findViewById(R.id.image);
        imageView.setImageBitmap(character.getImage());

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



    }
}
