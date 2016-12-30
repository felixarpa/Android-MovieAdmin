package idi.felixjulen.movieadmin.data;

import android.content.ContentValues;

import java.util.ArrayList;

import idi.felixjulen.movieadmin.domain.dataInterface.CtrlCountry;
import idi.felixjulen.movieadmin.domain.model.Country;


public class CtrlFilmDB implements CtrlCountry {
    @Override
    public Long insert(ContentValues values) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }

    @Override
    public Country get(Long id) {
        return null;
    }

    @Override
    public ArrayList<Country> all() {
        return null;
    }

    @Override
    public Boolean update(Long id, ContentValues values) {
        return null;
    }
}
