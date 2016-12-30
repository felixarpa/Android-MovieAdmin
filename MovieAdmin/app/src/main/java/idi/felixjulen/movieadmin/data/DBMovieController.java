package idi.felixjulen.movieadmin.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import idi.felixjulen.movieadmin.domain.model.Movie;

public class DBMovieController extends DBController<Movie> {

    private static final String TABLE_NAME = "MOVIE";

    public DBMovieController(Context context) {
        super(context, "MOVIE");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(createTable());
    }

    @Override
    protected void add(Cursor cursor, ArrayList<Movie> movies) {
        String name = cursor.getString(cursor.getColumnIndex("title"));
        String imageFilePath = cursor.getString(cursor.getColumnIndex("country"));;
        /*movies.add(
                new Movie(name, imageFilePath)
        );*/
    }

    @Override
    public ArrayList<Movie> getAll() {
        String[] columns = { "title", "country", "year", "director", "main", "rate"};
        return where(TABLE_NAME, columns, null, null);
    }

    @Override
    public ArrayList<Movie> getWhere(String attribute, String value) {
        String[] columns = { "title", "country", "year", "director", "main", "rate"};
        String[] where = { value };
        return where(TABLE_NAME, columns, 0, where);
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

    private static String createTable() {
        return "CREATE TABLE MOVIE (title TEXT PRIMARY KEY, country TEXT, year INTEGER, director TEXT, main TEXT, rate INTEGER);";
    }
}
