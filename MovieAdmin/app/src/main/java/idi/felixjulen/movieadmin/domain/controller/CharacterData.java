package idi.felixjulen.movieadmin.domain.controller;

import android.content.ContentValues;
import android.content.Context;

import java.util.ArrayList;

import idi.felixjulen.movieadmin.R;
import idi.felixjulen.movieadmin.data.CtrlCharacterDB;
import idi.felixjulen.movieadmin.data.DBController;
import idi.felixjulen.movieadmin.domain.dataInterface.CtrlCharacter;
import idi.felixjulen.movieadmin.domain.model.Character;
import idi.felixjulen.movieadmin.domain.model.Film;

public class CharacterData implements DefaultDataController<Character> {

    private static CharacterData instance;
    private static CtrlCharacter ctrl;
    private static Context context;

    public static CharacterData getInstance(Context mContext) {
        context = mContext;
        if (instance == null) {
            instance = new CharacterData(context);
        }
        return instance;
    }

    private CharacterData(Context context) {
        ctrl = CtrlCharacterDB.getInstance(context);
    }

    @Override
    public void makeDefault() {
        ctrl.purge();

        ContentValues bradlyCooper = new ContentValues();
        bradlyCooper.put(DBController.COLUMN_NAME, context.getString(R.string.bradly_cooper_name));
        bradlyCooper.put(DBController.COLUMN_IMAGE, context.getString(R.string.bradly_cooper_image));

        ContentValues christianBale = new ContentValues();
        christianBale.put(DBController.COLUMN_NAME, context.getString(R.string.christian_bale_name));
        christianBale.put(DBController.COLUMN_IMAGE, context.getString(R.string.christian_bale_image));

        ContentValues jenniferLawrence = new ContentValues();
        jenniferLawrence.put(DBController.COLUMN_NAME, context.getString(R.string.jennifer_lawrence_name));
        jenniferLawrence.put(DBController.COLUMN_IMAGE, context.getString(R.string.jennifer_lawrence_image));

        ContentValues nataliePortman = new ContentValues();
        nataliePortman.put(DBController.COLUMN_NAME, context.getString(R.string.natalie_portman_name));
        nataliePortman.put(DBController.COLUMN_IMAGE, context.getString(R.string.natalie_portman_image));

        ctrl.insert(christianBale);
        ctrl.insert(bradlyCooper);
        ctrl.insert(jenniferLawrence);
        ctrl.insert(nataliePortman);
    }

    @Override
    public ArrayList<Character> list() {
        return ctrl.all();
    }

    @Override
    public Character get(Long id) {
        return ctrl.get(id);
    }

    @Override
    public void delete(Long id) {
        ArrayList<Film> films = FilmData.getInstance(context).list();
        for (Film f : films) {
            if (f.getProtagonist().equals(id)) FilmData.getInstance(context).delete(f.getId());
        }
        ctrl.delete(id);
    }

    public ArrayList<Character> search(String filter) {
        String upperFilter = filter.toUpperCase();
        ArrayList<Character> all = ctrl.all();
        ArrayList<Character> result = new ArrayList<>();
        for (Character character : all) {
            String name = character.getName().toUpperCase();
            if (name.contains(upperFilter)) result.add(character);
        }
        return result;
    }

}
