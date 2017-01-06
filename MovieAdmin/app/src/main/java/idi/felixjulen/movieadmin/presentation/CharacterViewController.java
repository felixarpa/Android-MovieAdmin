package idi.felixjulen.movieadmin.presentation;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
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

public class CharacterViewController extends AppCompatActivity implements OnRecyclerViewItemAction {

    private Character character;
    private ArrayList<Film> films;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.character_view);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.character);
        setSupportActionBar(toolbar);

        Drawable arrowLeft = getDrawable(R.drawable.arrow_left);
        if(arrowLeft != null) {
            arrowLeft.mutate();
            arrowLeft.setColorFilter(getResources().getColor(R.color.md_white_1000), PorterDuff.Mode.SRC_ATOP);
            toolbar.setNavigationIcon(arrowLeft);
        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.character_menu, menu);
        for(int i = 0; i < menu.size(); i++){
            Drawable drawable = menu.getItem(i).getIcon();
            if(drawable != null) {
                drawable.mutate();
                drawable.setColorFilter(getResources().getColor(R.color.md_white_1000), PorterDuff.Mode.SRC_ATOP);
            }
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.remove_item:
                break;

            case R.id.edit_iem:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRecyclerViewItemClick(Integer position, Long itemEntityId) {
    }
}
