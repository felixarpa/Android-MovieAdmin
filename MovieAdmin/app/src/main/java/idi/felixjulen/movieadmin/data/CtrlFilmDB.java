package idi.felixjulen.movieadmin.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.util.ArrayList;

import idi.felixjulen.movieadmin.domain.dataInterface.CtrlFilm;
import idi.felixjulen.movieadmin.domain.model.Film;


public class CtrlFilmDB implements CtrlFilm {

    private static CtrlFilm instance;
    private static SQLiteDatabase writableDatabase;
    private static SQLiteDatabase readableDatabase;
    private String[] columns = {
            DBController.COLUMN_ID,
            DBController.COLUMN_TITLE,
            DBController.COLUMN_COUNTRY,
            DBController.COLUMN_YEAR,
            DBController.COLUMN_DIRECTOR,
            DBController.COLUMN_MAIN_CHARACTER,
            DBController.COLUMN_RATE,
            DBController.COLUMN_IMAGE
    };

    public static CtrlFilm getInstance(Context context) {
        if (instance == null) {
            instance = new CtrlFilmDB(context);
        }
        return instance;
    }

    private CtrlFilmDB(Context context) {
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

    @Override
    public void purge() {
        writableDatabase.delete(DBController.TABLE_FILMS, null, null);
    }

    @Override
    public ArrayList<Film> getByCharacter(Long id) {
        return getBy(DBController.COLUMN_MAIN_CHARACTER, id);
    }

    @Override
    public ArrayList<Film> getByDirector(Long id) {
        return getBy(DBController.COLUMN_DIRECTOR, id);
    }

    private ArrayList<Film> getBy(String column, Long id) {
        String[] selectionArgs = new String[] { String.valueOf(id) };
        Cursor cursor = readableDatabase.query(
                DBController.TABLE_FILMS,
                columns,
                column + " = ?",
                selectionArgs,
                null, null, null
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

    private Bitmap stringToBitmap(String str) {
        byte[] decodedString = Base64.decode(str, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    }

    private Film cursorToFilm(Cursor cursor) {
        Film result = new Film();
        result.setId(cursor.getLong(cursor.getColumnIndex(DBController.COLUMN_ID)));
        result.setTitle(cursor.getString(cursor.getColumnIndex(DBController.COLUMN_TITLE)));
        result.setCountry(cursor.getString(cursor.getColumnIndex(DBController.COLUMN_COUNTRY)));
        result.setYear(cursor.getInt(cursor.getColumnIndex(DBController.COLUMN_YEAR)));
        result.setDirector(cursor.getLong(cursor.getColumnIndex(DBController.COLUMN_DIRECTOR)));
        result.setProtagonist(cursor.getLong(cursor.getColumnIndex(DBController.COLUMN_MAIN_CHARACTER)));
        result.setRate(cursor.getInt(cursor.getColumnIndex(DBController.COLUMN_RATE)));
        String image = cursor.getString(cursor.getColumnIndex(DBController.COLUMN_IMAGE));
        result.setImage(stringToBitmap(image));
        return result;
    }

}
