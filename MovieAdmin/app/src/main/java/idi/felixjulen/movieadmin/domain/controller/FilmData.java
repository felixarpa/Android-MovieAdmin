package idi.felixjulen.movieadmin.domain.controller;

import android.content.ContentValues;
import android.content.Context;

import java.util.ArrayList;

import idi.felixjulen.movieadmin.R;
import idi.felixjulen.movieadmin.data.CtrlCharacterDB;
import idi.felixjulen.movieadmin.data.CtrlFilmDB;
import idi.felixjulen.movieadmin.data.DBController;
import idi.felixjulen.movieadmin.domain.dataInterface.CtrlFilm;
import idi.felixjulen.movieadmin.domain.model.Character;
import idi.felixjulen.movieadmin.domain.model.Film;

public class FilmData implements DefaultDataController<Film> {

    private static FilmData instance;
    private static CtrlFilm ctrl;
    private static Context context;

    public static FilmData getInstance(Context mContext) {
        context = mContext;
        if (instance == null) {
            instance = new FilmData(context);
        }
        return instance;
    }

    private FilmData(Context context) {
        ctrl = CtrlFilmDB.getInstance(context);
    }

    @Override
    public void makeDefault() {
        ctrl.purge();

        Long bradlyCooperId = CtrlCharacterDB.getInstance(context).getByName(context.getString(R.string.bradly_cooper_name)).getId();
        Long christianBaleId = CtrlCharacterDB.getInstance(context).getByName(context.getString(R.string.christian_bale_name)).getId();
        Long jenniferLawrenceId = CtrlCharacterDB.getInstance(context).getByName(context.getString(R.string.jennifer_lawrence_name)).getId();
        Long nataliePortmanId = CtrlCharacterDB.getInstance(context).getByName(context.getString(R.string.natalie_portman_name)).getId();



        ContentValues americanSniper = new ContentValues();
        americanSniper.put(DBController.COLUMN_NAME, "American Sniper");
        americanSniper.put(DBController.COLUMN_COUNTRY, "USA");
        americanSniper.put(DBController.COLUMN_YEAR, 2014);
        americanSniper.put(DBController.COLUMN_DIRECTOR, 0);
        americanSniper.put(DBController.COLUMN_MAIN_CHARACTER, bradlyCooperId);
        americanSniper.put(DBController.COLUMN_RATE, 6);

        ContentValues theDarkKnight = new ContentValues();
        theDarkKnight.put(DBController.COLUMN_NAME, "The Dark Knight");
        theDarkKnight.put(DBController.COLUMN_COUNTRY, "USA");
        theDarkKnight.put(DBController.COLUMN_YEAR, 2008);
        theDarkKnight.put(DBController.COLUMN_DIRECTOR, 0);
        theDarkKnight.put(DBController.COLUMN_MAIN_CHARACTER, christianBaleId);
        theDarkKnight.put(DBController.COLUMN_RATE, 9);

        ContentValues silverLiningsPlaybook = new ContentValues();
        silverLiningsPlaybook.put(DBController.COLUMN_NAME, "Silver Linings Playbook");
        silverLiningsPlaybook.put(DBController.COLUMN_COUNTRY, "USA");
        silverLiningsPlaybook.put(DBController.COLUMN_YEAR, 2012);
        silverLiningsPlaybook.put(DBController.COLUMN_DIRECTOR, 0);
        silverLiningsPlaybook.put(DBController.COLUMN_MAIN_CHARACTER, jenniferLawrenceId);
        silverLiningsPlaybook.put(DBController.COLUMN_RATE, 7);

        ContentValues blackSwan = new ContentValues();
        blackSwan.put(DBController.COLUMN_NAME, "Black Swan");
        blackSwan.put(DBController.COLUMN_COUNTRY, "USA");
        blackSwan.put(DBController.COLUMN_YEAR, 2010);
        blackSwan.put(DBController.COLUMN_DIRECTOR, 0);
        blackSwan.put(DBController.COLUMN_MAIN_CHARACTER, nataliePortmanId);
        blackSwan.put(DBController.COLUMN_RATE, 4);
    }

    @Override
    public ArrayList<Film> list() {
        return null;
    }

    @Override
    public Character get(Long id) {
        return null;
    }
}
