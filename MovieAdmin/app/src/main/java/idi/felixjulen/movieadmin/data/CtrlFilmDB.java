package idi.felixjulen.movieadmin.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import idi.felixjulen.movieadmin.domain.dataInterface.CtrlFilm;
import idi.felixjulen.movieadmin.domain.model.Film;


public class CtrlFilmDB implements CtrlFilm {

    private SQLiteDatabase writableDatabase;
    private SQLiteDatabase readableDatabase;
    private String[] columns = {
            DBController.COLUMN_ID,
            DBController.COLUMN_TITLE,
            DBController.COLUMN_COUNTRY,
            DBController.COLUMN_YEAR,
            DBController.COLUMN_DIRECTOR,
            DBController.COLUMN_MAIN_CHARACTER,
            DBController.COLUMN_RATE,
    };

    public CtrlFilmDB(Context context) {
        DBController databaseController = new DBController(context);
        writableDatabase = databaseController.getWritableDatabase();
        readableDatabase = databaseController.getReadableDatabase();
    }

    @Override
    public Long insert(ContentValues values) {
        return writableDatabase.insert(
                DBController.TABLE_FILMS,
                null,
                values
        );
    }

    @Override
    public Boolean delete(Long id) {
        Integer deletions = writableDatabase.delete(
                DBController.TABLE_FILMS,
                DBController.COLUMN_ID + " = " + id,
                null
        );
        return deletions > 0;
    }

    @Override
    public Film get(Long id) {
        Cursor cursor = readableDatabase.query(
                DBController.TABLE_FILMS,
                columns,
                DBController.COLUMN_ID + " = " + id,
                null, null, null, null
        );
        Film result;
        if (cursor.moveToFirst()) {
            result = cursorToFilm(cursor);
        } else {
            result = new Film();
        }
        cursor.close();
        return result;
    }

    @Override
    public ArrayList<Film> all() {
        Cursor cursor = readableDatabase.query(
                DBController.TABLE_FILMS,
                columns,
                null, null, null, null, null
        );
        ArrayList<Film> result = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                result.add(cursorToFilm(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return result;
    }

    @Override
    public Boolean update(Long id, ContentValues values) {
        Integer updates = writableDatabase.update(
                DBController.TABLE_FILMS,
                values,
                DBController.COLUMN_ID + " = " + id,
                null
        );
        return updates > 0;
    }

    private Film cursorToFilm(Cursor cursor) {
        Film result = new Film();
        result.setId(cursor.getLong(cursor.getColumnIndex(DBController.COLUMN_ID)));
        result.setTitle(cursor.getString(cursor.getColumnIndex(DBController.COLUMN_TITLE)));
        result.setCountry(cursor.getString(cursor.getColumnIndex(DBController.COLUMN_COUNTRY)));
        result.setTitle(cursor.getString(cursor.getColumnIndex(DBController.COLUMN_TITLE)));
        result.setTitle(cursor.getString(cursor.getColumnIndex(DBController.COLUMN_TITLE)));
        result.setTitle(cursor.getString(cursor.getColumnIndex(DBController.COLUMN_TITLE)));
        return result;
    }

}
