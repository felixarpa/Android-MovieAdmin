package idi.felixjulen.movieadmin.domain.controller;

import android.content.ContentValues;
import android.content.Context;

import java.util.ArrayList;

import idi.felixjulen.movieadmin.R;
import idi.felixjulen.movieadmin.data.CtrlCharacterDB;
import idi.felixjulen.movieadmin.data.CtrlDirectorDB;
import idi.felixjulen.movieadmin.data.CtrlFilmDB;
import idi.felixjulen.movieadmin.data.DBController;
import idi.felixjulen.movieadmin.domain.dataInterface.CtrlFilm;
import idi.felixjulen.movieadmin.domain.model.Film;

public class FilmData implements FilmDataController {

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

        Long clintEastwoodId = CtrlDirectorDB.getInstance(context).getByName(context.getString(R.string.clint_eastwood_name)).getId();
        Long christopherNolanId = CtrlDirectorDB.getInstance(context).getByName(context.getString(R.string.christopher_nolan_name)).getId();
        Long davidORussellId = CtrlDirectorDB.getInstance(context).getByName(context.getString(R.string.david_o_russell_name)).getId();
        Long darrenAronofskyId = CtrlDirectorDB.getInstance(context).getByName(context.getString(R.string.darren_aronofsky_name)).getId();

        ContentValues americanSniper = new ContentValues();
        americanSniper.put(DBController.COLUMN_TITLE, context.getString(R.string.american_sniper_name));
        americanSniper.put(DBController.COLUMN_IMAGE, context.getString(R.string.american_sniper_image));
        americanSniper.put(DBController.COLUMN_COUNTRY, "USA");
        americanSniper.put(DBController.COLUMN_YEAR, 2014);
        americanSniper.put(DBController.COLUMN_DIRECTOR, clintEastwoodId);
        americanSniper.put(DBController.COLUMN_MAIN_CHARACTER, bradlyCooperId);
        americanSniper.put(DBController.COLUMN_RATE, 6);

        ContentValues theDarkKnight = new ContentValues();
        theDarkKnight.put(DBController.COLUMN_TITLE, context.getString(R.string.the_dark_knight_name));
        theDarkKnight.put(DBController.COLUMN_IMAGE, context.getString(R.string.the_dark_knight_image));
        theDarkKnight.put(DBController.COLUMN_COUNTRY, "USA");
        theDarkKnight.put(DBController.COLUMN_YEAR, 2008);
        theDarkKnight.put(DBController.COLUMN_DIRECTOR, christopherNolanId);
        theDarkKnight.put(DBController.COLUMN_MAIN_CHARACTER, christianBaleId);
        theDarkKnight.put(DBController.COLUMN_RATE, 9);

        ContentValues silverLiningsPlaybook = new ContentValues();
        silverLiningsPlaybook.put(DBController.COLUMN_TITLE, context.getString(R.string.silver_lining_playbook_name));
        silverLiningsPlaybook.put(DBController.COLUMN_IMAGE, context.getString(R.string.silver_lining_playbook_image));
        silverLiningsPlaybook.put(DBController.COLUMN_COUNTRY, "USA");
        silverLiningsPlaybook.put(DBController.COLUMN_YEAR, 2012);
        silverLiningsPlaybook.put(DBController.COLUMN_DIRECTOR, davidORussellId);
        silverLiningsPlaybook.put(DBController.COLUMN_MAIN_CHARACTER, jenniferLawrenceId);
        silverLiningsPlaybook.put(DBController.COLUMN_RATE, 7);

        ContentValues blackSwan = new ContentValues();
        blackSwan.put(DBController.COLUMN_TITLE, context.getString(R.string.black_swan_name));
        blackSwan.put(DBController.COLUMN_IMAGE, context.getString(R.string.black_swan_image));
        blackSwan.put(DBController.COLUMN_COUNTRY, "USA");
        blackSwan.put(DBController.COLUMN_YEAR, 2010);
        blackSwan.put(DBController.COLUMN_DIRECTOR, darrenAronofskyId);
        blackSwan.put(DBController.COLUMN_MAIN_CHARACTER, nataliePortmanId);
        blackSwan.put(DBController.COLUMN_RATE, 4);

        ctrl.insert(americanSniper);
        ctrl.insert(theDarkKnight);
        ctrl.insert(silverLiningsPlaybook);
        ctrl.insert(blackSwan);
    }

    @Override
    public ArrayList<Film> list() {
        return ctrl.all();
    }

    @Override
    public Film get(Long id) {
        return ctrl.get(id);
    }

    @Override
    public void delete(Long id) {
        ctrl.delete(id);
    }

    @Override
    public ArrayList<Film> getWithCharacter(Long id) {
        return ctrl.getByCharacter(id);
    }
}
