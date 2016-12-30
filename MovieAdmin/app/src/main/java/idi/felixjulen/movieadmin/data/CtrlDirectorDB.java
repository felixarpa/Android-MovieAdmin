package idi.felixjulen.movieadmin.data;

import android.content.ContentValues;
import android.content.Context;

import java.util.ArrayList;

import idi.felixjulen.movieadmin.domain.dataInterface.CtrlDirector;
import idi.felixjulen.movieadmin.domain.model.Director;


public class CtrlDirectorDB implements CtrlDirector {

    public CtrlDirectorDB(Context context) {
        DBController databaseController = new DBController(context);
    }

    @Override
    public Long insert(ContentValues values) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }

    @Override
    public Director get(Long id) {
        return null;
    }

    @Override
    public ArrayList<Director> all() {
        return null;
    }

    @Override
    public Boolean update(Long id, ContentValues values) {
        return null;
    }
}
