package idi.felixjulen.movieadmin.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import idi.felixjulen.movieadmin.domain.model.Movie;

public class DBMovieController extends DBController<Movie> {

    public DBMovieController(Context context) {
        super(context, "MOVIE");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Movie.createTable());
    }

    @Override
    protected void add(Cursor cursor, ArrayList<Movie> movies) {

    }

    @Override
    public ArrayList<Movie> getAll() {
        return null;
    }

    @Override
    public ArrayList<Movie> getWhere(String attribute, String value) {
        return null;
    }

    @Override
    public Boolean update(Movie movie) {
        return null;
    }

    @Override
    public Boolean insert(Movie movie) {
        return null;
    }

    @Override
    public Boolean delete(Movie movie) {
        return null;
    }
}
