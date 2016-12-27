package idi.felixjulen.movieadmin.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import idi.felixjulen.movieadmin.domain.model.Country;

public class DBCountryController extends DBController<Country> {

    public DBCountryController(Context context) {
        super(context, "COUNTRY");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Country.createTable());
    }

    @Override
    protected void add(Cursor cursor, ArrayList<Country> countries) {

    }

    @Override
    public ArrayList<Country> getAll() {
        return null;
    }

    @Override
    public ArrayList<Country> getWhere(String attribute, String value) {
        return null;
    }

    @Override
    public Boolean update(Country country) {
        return null;
    }

    @Override
    public Boolean insert(Country country) {
        return null;
    }

    @Override
    public Boolean delete(Country country) {
        return null;
    }
}
