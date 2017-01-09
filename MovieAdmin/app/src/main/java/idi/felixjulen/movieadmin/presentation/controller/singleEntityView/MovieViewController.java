package idi.felixjulen.movieadmin.presentation.controller.singleEntityView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import idi.felixjulen.movieadmin.R;
import idi.felixjulen.movieadmin.domain.controller.CharacterData;
import idi.felixjulen.movieadmin.domain.controller.DirectorData;
import idi.felixjulen.movieadmin.domain.controller.FileManager;
import idi.felixjulen.movieadmin.domain.controller.FilmData;
import idi.felixjulen.movieadmin.domain.model.Character;
import idi.felixjulen.movieadmin.domain.model.Director;
import idi.felixjulen.movieadmin.domain.model.Film;
import idi.felixjulen.movieadmin.presentation.controller.editEntityView.MovieEditViewController;

public class MovieViewController extends EntityViewController<Film> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        layoutResourceId = R.layout.movie_view;
        removeTitle = R.string.delete_movie_title;
        removeMessage = R.string.delete_movie_message;
        removeYes = R.string.of_course;
        removeNo = R.string.not_really;
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();

        ImageView imageView = (ImageView) findViewById(R.id.image);
        Bitmap image = FileManager.getInstance(this).loadImageFromStorage(data.getImage(), R.mipmap.film);
        imageView.setImageBitmap(image);

        TextView rateView = (TextView) findViewById(R.id.rate);
        rateView.setText(String.valueOf(data.getRate()));

        TextView yearView = (TextView) findViewById(R.id.year);
        yearView.setText(String.valueOf(data.getYear()));

        TextView countryView = (TextView) findViewById(R.id.country);
        countryView.setText(data.getCountry());

        Director director = DirectorData.getInstance(this).get(data.getDirector());

        FrameLayout directorFrame = (FrameLayout) findViewById(R.id.director);
        View directorView = getLayoutInflater().inflate(R.layout.entity_row_layout, directorFrame);

        TextView nameView = (TextView) directorView.findViewById(R.id.name);
        nameView.setText(director.getName());

        if (director.getImage() != null) {
            imageView = (ImageView) directorView.findViewById(R.id.image);
            Bitmap directorImage = FileManager.getInstance(this).loadImageFromStorage(
                    director.getImage(),
                    R.mipmap.profile
            );
            imageView.setImageBitmap(directorImage);
        }

        Character character = CharacterData.getInstance(this).get(data.getProtagonist());

        FrameLayout characterFrame = (FrameLayout) findViewById(R.id.main_character);
        View characterView = getLayoutInflater().inflate(R.layout.entity_row_layout, characterFrame);

        nameView = (TextView) characterView.findViewById(R.id.name);
        nameView.setText(character.getName());

        if (character.getImage() != null) {
            imageView = (ImageView) characterView.findViewById(R.id.image);
            Bitmap characterImage = FileManager.getInstance(this).loadImageFromStorage(
                    character.getImage(),
                    R.mipmap.profile
            );
            imageView.setImageBitmap(characterImage);
        }

    }

    @Override
    protected void editEntity() {
        Intent intent = new Intent(this, MovieEditViewController.class);
        intent.putExtra(getString(R.string.itemEntityId), id);
        startActivity(intent);
    }

    @Override
    protected void removeEntity() {
        FilmData.getInstance(this).delete(data.getId());
    }

    @Override
    protected Film getData() {
        return FilmData.getInstance(this).get(id);
    }
}
