package idi.felixjulen.movieadmin.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.util.ArrayList;

import idi.felixjulen.movieadmin.domain.dataInterface.CtrlDirector;
import idi.felixjulen.movieadmin.domain.model.Director;

public class CtrlDirectorDB implements CtrlDirector {

    private static CtrlDirector instance;
    private static SQLiteDatabase writableDatabase;
    private static SQLiteDatabase readableDatabase;
    private String[] columns = {
            DBController.COLUMN_ID,
            DBController.COLUMN_NAME,
            DBController.COLUMN_IMAGE
    };

    public static CtrlDirector getInstance(Context context) {
        if (instance == null) {
            instance = new CtrlDirectorDB(context);
        }
        return instance;
    }

    private CtrlDirectorDB(Context context) {
        DBController databaseController = new DBController(context);
        writableDatabase = databaseController.getWritableDatabase();
        readableDatabase = databaseController.getReadableDatabase();
    }

    @Override
    public Long insert(ContentValues values) {
        return writableDatabase.insert(
                DBController.TABLE_DIRECTOR,
                null,
                values
        );
    }

    @Override
    public Boolean delete(Long id) {
        Integer deletions = writableDatabase.delete(
                DBController.TABLE_DIRECTOR,
                DBController.COLUMN_ID + " = " + id,
                null
        );
        return deletions > 0;
    }

    @Override
    public Director get(Long id) {
        Cursor cursor = readableDatabase.query(
                DBController.TABLE_DIRECTOR,
                columns,
                DBController.COLUMN_ID + " = " + id,
                null, null, null, null
        );
        Director result;
        if (cursor.moveToFirst()) {
            result = cursorToDirector(cursor);
        } else {
            result = new Director();
        }
        cursor.close();
        return result;
    }

    @Override
    public ArrayList<Director> all() {
        Cursor cursor = readableDatabase.query(
                DBController.TABLE_DIRECTOR,
                columns,
                null, null, null, null, null
        );
        return directorArrayListFrom(cursor);
    }

    @Override
    public Boolean update(Long id, ContentValues values) {
        Integer updates = writableDatabase.update(
                DBController.TABLE_DIRECTOR,
                values,
                DBController.COLUMN_ID + " = " + id,
                null
        );
        return updates > 0;
    }

    @Override
    public void purge() {
        writableDatabase.delete(DBController.TABLE_DIRECTOR, null, null);
    }

    private Director cursorToDirector(Cursor cursor) {
        Director result = new Director();
        result.setId(cursor.getLong(cursor.getColumnIndex(DBController.COLUMN_ID)));
        result.setName(cursor.getString(cursor.getColumnIndex(DBController.COLUMN_NAME)));
        String image = cursor.getString(cursor.getColumnIndex(DBController.COLUMN_IMAGE));
        result.setImage(stringToBitmap(image));
        return result;
    }

    private Bitmap stringToBitmap(String str) {
        byte[] decodedString = Base64.decode(str, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    }

    private ArrayList<Director> directorArrayListFrom(Cursor cursor) {
        ArrayList<Director> result = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                result.add(cursorToDirector(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return result;
    }
}
