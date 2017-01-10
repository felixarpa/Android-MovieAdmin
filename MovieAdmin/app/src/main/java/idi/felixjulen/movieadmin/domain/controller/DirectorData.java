package idi.felixjulen.movieadmin.domain.controller;

import android.content.ContentValues;
import android.content.Context;

import java.util.ArrayList;
import java.util.Date;

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

        FileManager fm = FileManager.getInstance(context);

        ContentValues christopherNolan = new ContentValues();
        christopherNolan.put(DBController.COLUMN_NAME, context.getString(R.string.christopher_nolan_name));
        String christopherNolanImageName = fm.saveToInternalStorage("image" + new Date().getTime() + ".png", FileManager.resourceToBitmap(R.mipmap.christopher_nolan));
        christopherNolan.put(DBController.COLUMN_IMAGE, christopherNolanImageName);

        ContentValues clintEastwood = new ContentValues();
        clintEastwood.put(DBController.COLUMN_NAME, context.getString(R.string.clint_eastwood_name));
        String clintEastwoodImageName = fm.saveToInternalStorage("image" + new Date().getTime() + ".png", FileManager.resourceToBitmap(R.mipmap.clint_eastwood));
        clintEastwood.put(DBController.COLUMN_IMAGE, clintEastwoodImageName);

        ContentValues darrenAronofsky = new ContentValues();
        darrenAronofsky.put(DBController.COLUMN_NAME, context.getString(R.string.darren_aronofsky_name));
        String darrenAronofskyImageName = fm.saveToInternalStorage("image" + new Date().getTime() + ".png", FileManager.resourceToBitmap(R.mipmap.darren_aronofsky));
        darrenAronofsky.put(DBController.COLUMN_IMAGE, darrenAronofskyImageName);

        ContentValues davidORussell = new ContentValues();
        davidORussell.put(DBController.COLUMN_NAME, context.getString(R.string.david_o_russell_name));
        String davidORussellImageName = fm.saveToInternalStorage("image" + new Date().getTime() + ".png", FileManager.resourceToBitmap(R.mipmap.david_orussell));
        davidORussell.put(DBController.COLUMN_IMAGE, davidORussellImageName);

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

    @Override
    public ArrayList<Director> search(String filter) {
        String upperFilter = filter.toUpperCase();
        ArrayList<Director> all = ctrl.all();
        ArrayList<Director> result = new ArrayList<>();
        for (Director director : all) {
            String name = director.getName().toUpperCase();
            if (name.contains(upperFilter)) result.add(director);
        }
        return result;
    }

    @Override
    public void update(Director director) {
        ctrl.update(director.getId(), getValues(director));
    }

    @Override
    public Long add(Director director) {
        return ctrl.insert(getValues(director));
    }

    public Director getByName(String name) {
        return ctrl.getByName(name);
    }

    private ContentValues getValues(Director director) {
        ContentValues values = new ContentValues();
        values.put(DBController.COLUMN_NAME, director.getName());
        values.put(DBController.COLUMN_IMAGE, director.getImage());
        return values;
    }
}
