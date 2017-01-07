package idi.felixjulen.movieadmin.presentation;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.widget.ImageView;
import android.widget.TextView;

import idi.felixjulen.movieadmin.R;
import idi.felixjulen.movieadmin.domain.controller.CharacterData;
import idi.felixjulen.movieadmin.domain.controller.DirectorData;
import idi.felixjulen.movieadmin.domain.controller.FilmData;
import idi.felixjulen.movieadmin.domain.model.Character;
import idi.felixjulen.movieadmin.domain.model.Director;
import idi.felixjulen.movieadmin.domain.model.Film;

public class MovieViewController extends EntityViewController {

    private Film film;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        layoutResourceId = R.layout.movie_view;
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setUpEntity() {

        film = FilmData.getInstance(this).get(id);

        TextView titleView = (TextView) findViewById(R.id.title);
        titleView.setText(film.getTitle());

        if (film.getImage() != null) {
            ImageView imageView = (ImageView) findViewById(R.id.image_film);
            imageView.setImageBitmap(film.getImage());
        }

        TextView rateView = (TextView) findViewById(R.id.rate);
        rateView.setText(String.valueOf(film.getRate()));

        TextView yearView = (TextView) findViewById(R.id.year);
        yearView.setText(String.valueOf(film.getYear()));

        TextView countryView = (TextView) findViewById(R.id.country);
        countryView.setText(film.getCountry());

        Director director = DirectorData.getInstance(this).get(film.getDirector());

        TextView nameDirView = (TextView) findViewById(R.id.name_dir);
        nameDirView.setText(director.getName());

        if (director.getImage() != null) {
            ImageView imageView = (ImageView) findViewById(R.id.image_dir);
            imageView.setImageBitmap(director.getImage());
        }

        Character character = CharacterData.getInstance(this).get(film.getProtagonist());

        TextView nameView = (TextView) findViewById(R.id.name);
        nameView.setText(character.getName());

        if (character.getImage() != null) {
            ImageView imageView = (ImageView) findViewById(R.id.image);
            imageView.setImageBitmap(character.getImage());
        }

    }

    @Override
    protected void editEntity() {

    }

    @Override
    protected void removeEntity() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.delete_movie_title)
                .setMessage(R.string.delete_movie_message)
                .setPositiveButton(
                        "Of course",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                FilmData.getInstance(getApplicationContext()).delete(film.getId());
                                finish();
                            }
                        })
                .setNeutralButton(
                        "Not really",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                .show();
    }
}
