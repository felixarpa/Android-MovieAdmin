package idi.felixjulen.movieadmin.domain.controller;

import android.content.ContentValues;
import android.content.Context;

import java.util.ArrayList;
import java.util.Date;

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

        FileManager fm = FileManager.getInstance(context);

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
        String americanSniperImageName = fm.saveToInternalStorage("image" + new Date().getTime() + ".png", FileManager.resourceToBitmap(R.mipmap.american_sniper));
        americanSniper.put(DBController.COLUMN_IMAGE, americanSniperImageName);
        americanSniper.put(DBController.COLUMN_COUNTRY, "United States");
        americanSniper.put(DBController.COLUMN_YEAR, 2014);
        americanSniper.put(DBController.COLUMN_DIRECTOR, clintEastwoodId);
        americanSniper.put(DBController.COLUMN_MAIN_CHARACTER, bradlyCooperId);
        americanSniper.put(DBController.COLUMN_RATE, 6);

        ContentValues theDarkKnight = new ContentValues();
        theDarkKnight.put(DBController.COLUMN_TITLE, context.getString(R.string.the_dark_knight_name));
        String theDarkKnightImageName = fm.saveToInternalStorage("image" + new Date().getTime() + ".png", FileManager.resourceToBitmap(R.mipmap.the_dark_knight));
        theDarkKnight.put(DBController.COLUMN_IMAGE, theDarkKnightImageName);
        theDarkKnight.put(DBController.COLUMN_COUNTRY, "United States");
        theDarkKnight.put(DBController.COLUMN_YEAR, 2008);
        theDarkKnight.put(DBController.COLUMN_DIRECTOR, christopherNolanId);
        theDarkKnight.put(DBController.COLUMN_MAIN_CHARACTER, christianBaleId);
        theDarkKnight.put(DBController.COLUMN_RATE, 9);

        ContentValues silverLiningsPlaybook = new ContentValues();
        silverLiningsPlaybook.put(DBController.COLUMN_TITLE, context.getString(R.string.silver_lining_playbook_name));
        String silverLiningsPlaybookImageName = fm.saveToInternalStorage("image" + new Date().getTime() + ".png", FileManager.resourceToBitmap(R.mipmap.silver_linings_playbook));
        silverLiningsPlaybook.put(DBController.COLUMN_IMAGE, silverLiningsPlaybookImageName);
        silverLiningsPlaybook.put(DBController.COLUMN_COUNTRY, "United States");
        silverLiningsPlaybook.put(DBController.COLUMN_YEAR, 2012);
        silverLiningsPlaybook.put(DBController.COLUMN_DIRECTOR, davidORussellId);
        silverLiningsPlaybook.put(DBController.COLUMN_MAIN_CHARACTER, jenniferLawrenceId);
        silverLiningsPlaybook.put(DBController.COLUMN_RATE, 7);

        ContentValues blackSwan = new ContentValues();
        blackSwan.put(DBController.COLUMN_TITLE, context.getString(R.string.black_swan_name));
        String blackSwanImageName = fm.saveToInternalStorage("image" + new Date().getTime() + ".png", FileManager.resourceToBitmap(R.mipmap.black_swan));
        blackSwan.put(DBController.COLUMN_IMAGE, blackSwanImageName);
        blackSwan.put(DBController.COLUMN_COUNTRY, "United States");
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
    @Override
    public ArrayList<Film> getWithDirector(Long id) {
        return ctrl.getByDirector(id);
    }

    @Override
    public ArrayList<Film> search(String filter) {
        String upperFilter = filter.toUpperCase();
        ArrayList<Film> all = ctrl.all();
        ArrayList<Film> result = new ArrayList<>();
        for (Film film : all) {
            String name = film.getName().toUpperCase();
            if (name.contains(upperFilter)) result.add(film);
        }
        return result;
    }

    @Override
    public void update(Film film) {
        ctrl.update(film.getId(), getValues(film));
    }

    @Override
    public Long add(Film film) {
        return ctrl.insert(getValues(film));
    }

    private ContentValues getValues(Film film) {
        ContentValues values = new ContentValues();
        values.put(DBController.COLUMN_TITLE, film.getTitle());
        values.put(DBController.COLUMN_IMAGE, film.getImage());
        values.put(DBController.COLUMN_COUNTRY, film.getCountry());
        values.put(DBController.COLUMN_YEAR, film.getYear());
        values.put(DBController.COLUMN_DIRECTOR, film.getDirector());
        values.put(DBController.COLUMN_MAIN_CHARACTER, film.getMainCharacter());
        values.put(DBController.COLUMN_RATE, film.getRate());
        return values;
    }
}
