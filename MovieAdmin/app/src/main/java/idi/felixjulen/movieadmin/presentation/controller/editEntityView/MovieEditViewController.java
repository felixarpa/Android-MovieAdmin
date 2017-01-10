package idi.felixjulen.movieadmin.presentation.controller.editEntityView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import idi.felixjulen.movieadmin.R;
import idi.felixjulen.movieadmin.domain.controller.CharacterData;
import idi.felixjulen.movieadmin.domain.controller.DirectorData;
import idi.felixjulen.movieadmin.domain.controller.FilmData;
import idi.felixjulen.movieadmin.domain.model.Character;
import idi.felixjulen.movieadmin.domain.model.Director;
import idi.felixjulen.movieadmin.domain.model.Film;
import idi.felixjulen.movieadmin.presentation.controller.singleEntityView.MovieViewController;

public class MovieEditViewController extends EntityEditViewController<Film> {

    private RatingBar rateBar;
    private Spinner countrySpinner;
    private Spinner yearSpinner;
    private AutoCompleteTextView directorText;
    private AutoCompleteTextView mainCharacterText;
    private ArrayAdapter<String> countriesArrayAdapter;
    private ArrayAdapter<String> yearArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        layoutResourceId = R.layout.movie_edit_view;
        alternativeResourceId = R.mipmap.film;
        super.onCreate(savedInstanceState);

        rateBar = (RatingBar) findViewById(R.id.rating_bar);
        countrySpinner = (Spinner) findViewById(R.id.country);
        yearSpinner = (Spinner) findViewById(R.id.year);
        directorText = (AutoCompleteTextView) findViewById(R.id.director_autocomplete);
        mainCharacterText = (AutoCompleteTextView) findViewById(R.id.main_character_autocomplete);

        String[] countries = getResources().getStringArray(R.array.countries_array);
        countriesArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, countries);
        countriesArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countrySpinner.setAdapter(countriesArrayAdapter);

        Integer currentYear = Calendar.getInstance().get(Calendar.YEAR);
        ArrayList<String> years = new ArrayList<>();
        for (int i = currentYear; i >= 1896; i--) {
            years.add(String.valueOf(i));
        }
        yearArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, years);
        yearArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearSpinner.setAdapter(yearArrayAdapter);

        ArrayAdapter<String> directorArrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                getDirectorStringArray(DirectorData.getInstance(this).list())
        );
        directorArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        directorText.setAdapter(directorArrayAdapter);

        ArrayAdapter<String> mainCharacterArrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                getCharacterStringArray(CharacterData.getInstance(this).list())
        );
        mainCharacterArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mainCharacterText.setAdapter(mainCharacterArrayAdapter);

        if (id != -1L) {
            Float rate = ((float) data.getRate()) / 2;
            rateBar.setRating(rate);
            countrySpinner.setSelection(countriesArrayAdapter.getPosition(data.getCountry()));
            yearSpinner.setSelection(yearArrayAdapter.getPosition(String.valueOf(data.getYear())));
            Director director = DirectorData.getInstance(this).get(data.getDirector());
            directorText.setText(director.getName());
            Character character = CharacterData.getInstance(this).get(data.getMainCharacter());
            mainCharacterText.setText(character.getName());
        }

    }

    private ArrayList<String> getDirectorStringArray(ArrayList<Director> list) {
        ArrayList<String> result = new ArrayList<>();
        for(Director d : list) {
            result.add(d.getName());
        }
        return result;
    }

    private ArrayList<String> getCharacterStringArray(ArrayList<Character> list) {
        ArrayList<String> result = new ArrayList<>();
        for(Character c : list) {
            result.add(c.getName());
        }
        return result;
    }

    @Override
    protected void update() {
        FilmData.getInstance(this).update(data);
        finish();
    }

    @Override
    protected void save() {
        Long newId = FilmData.getInstance(this).add(data);
        Intent intent = new Intent(this, MovieViewController.class);
        intent.putExtra(getString(R.string.itemEntityId), newId);
        startActivity(intent);
        finish();
    }

    @Override
    protected Film getData(Long id) {
        return FilmData.getInstance(this).get(id);
    }

    @Override
    protected Film newData() {
        return new Film();
    }

    @Override
    protected void doSave() {
        if (nameEditText.getText().length() == 0) {
            nameTextInputLayout.setError(getString(R.string.empty_error));
        } else if (directorText.getText().length() == 0 || mainCharacterText.getText().length() == 0) {
            Toast.makeText(this, R.string.empty_error, Toast.LENGTH_SHORT).show();
        } else {

            String directorName = directorText.getText().toString();
            Director director = DirectorData.getInstance(this).getByName(directorName);

            String characterName = mainCharacterText.getText().toString();
            Character character = CharacterData.getInstance(this).getByName(characterName);

            String message = " not exists. If you create this movie you will also create ";
            if (director.getId() == -1L && character.getId() == -1L) {
                message = "Director and Character with that name do" + message + "them";
            } else if (director.getId() == -1L) {
                message = "Director with that name does" + message + "him/her";
            } else if (character.getId() == -1L) {
                message = "Character with that name does" + message + "him/her";
            }

            if (director.getId() == -1L || character.getId() == -1L) {
                new AlertDialog.Builder(this)
                        .setTitle("Some fields do not exists")
                        .setMessage(message)
                        .setPositiveButton(
                                "Continue",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        doSuperSave();
                                    }
                                }
                        )
                        .setNeutralButton(
                                "Cancel",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                }
                        )
                        .show();
            } else {
                doSuperSave();
            }
        }
    }

    private void doSuperSave() {
        //data.setRate(ratePicker.getValue());
        Integer rate = (int) (rateBar.getRating() * 2);
        data.setRate(rate);
        data.setCountry(countriesArrayAdapter.getItem(countrySpinner.getSelectedItemPosition()));
        data.setYear(Integer.valueOf(yearArrayAdapter.getItem(yearSpinner.getSelectedItemPosition())));

        String directorName = directorText.getText().toString();
        Director director = DirectorData.getInstance(this).getByName(directorName);
        if (director.getId() == -1L) {
            director.setName(directorName);
            director.setImage("");
            director.setId(DirectorData.getInstance(this).add(director));
        }
        data.setDirector(director.getId());

        String characterName = mainCharacterText.getText().toString();
        Character character = CharacterData.getInstance(this).getByName(characterName);
        if (character.getId() == -1L) {
            character.setName(characterName);
            character.setImage("");
            character.setId(CharacterData.getInstance(this).add(character));
        }
        data.setMainCharacter(character.getId());

        super.doSave();
    }

}
