package idi.felixjulen.movieadmin.data;

import android.content.ContentValues;
import android.content.Context;

import java.util.ArrayList;

import idi.felixjulen.movieadmin.domain.dataInterface.CtrlCharacter;

public class CtrlCharacterDB implements CtrlCharacter {

    public CtrlCharacterDB(Context context) {
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
    public Character get(Long id) {
        return null;
    }

    @Override
    public ArrayList all() {
        return null;
    }

    @Override
    public Boolean update(Long id, ContentValues values) {
        return null;
    }
}
