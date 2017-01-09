package idi.felixjulen.movieadmin.domain.controller;

import android.content.ContentValues;
import android.content.Context;

import java.util.ArrayList;
import java.util.Date;

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

        FileManager fm = FileManager.getInstance(context);

        ContentValues bradlyCooper = new ContentValues();
        bradlyCooper.put(DBController.COLUMN_NAME, context.getString(R.string.bradly_cooper_name));
        String bradlyCooperImageName = fm.saveToInternalStorage("image" + new Date().getTime() + ".png", FileManager.resourceToBitmap(R.mipmap.bradlycooper));
        bradlyCooper.put(DBController.COLUMN_IMAGE, bradlyCooperImageName);

        ContentValues christianBale = new ContentValues();
        christianBale.put(DBController.COLUMN_NAME, context.getString(R.string.christian_bale_name));
        String christianBaleImageName = fm.saveToInternalStorage("image" + new Date().getTime() + ".png", FileManager.resourceToBitmap(R.mipmap.christianbale));
        christianBale.put(DBController.COLUMN_IMAGE, christianBaleImageName);

        ContentValues jenniferLawrence = new ContentValues();
        jenniferLawrence.put(DBController.COLUMN_NAME, context.getString(R.string.jennifer_lawrence_name));
        String jenniferLawrenceImageName = fm.saveToInternalStorage("image" + new Date().getTime() + ".png", FileManager.resourceToBitmap(R.mipmap.jenniferlawrence));
        jenniferLawrence.put(DBController.COLUMN_IMAGE, jenniferLawrenceImageName);

        ContentValues nataliePortman = new ContentValues();
        nataliePortman.put(DBController.COLUMN_NAME, context.getString(R.string.natalie_portman_name));
        String nataliePortmanImageName = fm.saveToInternalStorage("image" + new Date().getTime() + ".png", FileManager.resourceToBitmap(R.mipmap.natalieportman));
        nataliePortman.put(DBController.COLUMN_IMAGE, nataliePortmanImageName);

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
            if (f.getMainCharacter().equals(id)) FilmData.getInstance(context).delete(f.getId());
        }
        ctrl.delete(id);
    }

    @Override
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

    @Override
    public void update(Character character) {
        ctrl.update(character.getId(), getValues(character));
    }

    @Override
    public Long add(Character character) {
        return ctrl.insert(getValues(character));
    }

    public Character getByName(String name) {
        return ctrl.getByName(name);
    }

    private ContentValues getValues(Character character) {
        ContentValues values = new ContentValues();
        values.put(DBController.COLUMN_NAME, character.getName());
        values.put(DBController.COLUMN_IMAGE, character.getImage());
        return values;
    }

}
