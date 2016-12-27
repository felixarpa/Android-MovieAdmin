package idi.felixjulen.movieadmin.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import idi.felixjulen.movieadmin.domain.model.Director;

public class DBDirectorController extends DBController<Director> {

    public DBDirectorController(Context context) {
        super(context, "DIRECTOR");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Director.createTable());
    }

    @Override
    protected void add(Cursor cursor, ArrayList<Director> directors) {

    }

    @Override
    public ArrayList<Director> getAll() {
        return null;
    }

    @Override
    public ArrayList<Director> getWhere(String attribute, String value) {
        return null;
    }

    @Override
    public Boolean update(Director director) {
        return null;
    }

    @Override
    public Boolean insert(Director director) {
        return null;
    }

    @Override
    public Boolean delete(Director director) {
        return null;
    }
}
