package idi.felixjulen.movieadmin.domain.controller;

import android.content.ContentValues;
import android.content.Context;

import java.util.ArrayList;

import idi.felixjulen.movieadmin.R;
import idi.felixjulen.movieadmin.data.CtrlDirectorDB;
import idi.felixjulen.movieadmin.data.DBController;
import idi.felixjulen.movieadmin.domain.dataInterface.CtrlDirector;
import idi.felixjulen.movieadmin.domain.model.Director;
import idi.felixjulen.movieadmin.domain.model.Film;

public class DirectorData implements DefaultDataController<Director> {

    private static DirectorData instance;
    private static CtrlDirector ctrl;
    private static Context context;

    public static DirectorData getInstance(Context mContext) {
        context = mContext;
        if (instance == null) {
            instance = new DirectorData(context);
        }
        return instance;
    }

    private DirectorData(Context context) {
        ctrl = CtrlDirectorDB.getInstance(context);
    }

    @Override
    public void makeDefault() {
        ctrl.purge();

        ContentValues christopherNolan = new ContentValues();
        christopherNolan.put(DBController.COLUMN_NAME, context.getString(R.string.christopher_nolan_name));
        christopherNolan.put(DBController.COLUMN_IMAGE, context.getString(R.string.christopher_nolan_image));

        ContentValues clintEastwood = new ContentValues();
        clintEastwood.put(DBController.COLUMN_NAME, context.getString(R.string.clint_eastwood_name));
        clintEastwood.put(DBController.COLUMN_IMAGE, context.getString(R.string.clint_eastwood_image));

        ContentValues darrenAronofsky = new ContentValues();
        darrenAronofsky.put(DBController.COLUMN_NAME, context.getString(R.string.darren_aronofsky_name));
        darrenAronofsky.put(DBController.COLUMN_IMAGE, context.getString(R.string.darren_aronofsky_image));

        ContentValues davidORussell = new ContentValues();
        davidORussell.put(DBController.COLUMN_NAME, context.getString(R.string.david_o_russell_name));
        davidORussell.put(DBController.COLUMN_IMAGE, context.getString(R.string.david_o_russell_image));

        ctrl.insert(clintEastwood);
        ctrl.insert(christopherNolan);
        ctrl.insert(darrenAronofsky);
        ctrl.insert(davidORussell);
    }

    @Override
    public ArrayList<Director> list() {
        return ctrl.all();
    }

    @Override
    public Director get(Long id) {
        return ctrl.get(id);
    }

    @Override
    public void delete(Long id) {
        ArrayList<Film> films = FilmData.getInstance(context).list();
        Boolean canDelete = true;
        for (int i = 0; i < films.size() && canDelete; i++) {
            if (films.get(i).getDirector().equals(id)) canDelete = false;
        }
        if (canDelete) ctrl.delete(id);
    }
}
