package idi.felixjulen.movieadmin.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.util.ArrayList;

import idi.felixjulen.movieadmin.domain.dataInterface.CtrlCountry;
import idi.felixjulen.movieadmin.domain.model.Country;


public class CtrlCountryDB implements CtrlCountry {

    private SQLiteDatabase writableDatabase;
    private SQLiteDatabase readableDatabase;
    private String[] columns = {
            DBController.COLUMN_ID,
            DBController.COLUMN_NAME,
            DBController.COLUMN_IMAGE
    };

    public CtrlCountryDB(Context context) {
        DBController databaseController = new DBController(context);
        writableDatabase = databaseController.getWritableDatabase();
        readableDatabase = databaseController.getReadableDatabase();
    }

    @Override
    public Long insert(ContentValues values) {
        return writableDatabase.insert(
                DBController.TABLE_COUNTRY,
                null,
                values
        );
    }

    @Override
    public Boolean delete(Long id) {
        Integer deletions = writableDatabase.delete(
                DBController.TABLE_COUNTRY,
                DBController.COLUMN_ID + " = " + id,
                null
        );
        return deletions > 0;
    }

    @Override
    public Country get(Long id) {
        Cursor cursor = readableDatabase.query(
                DBController.TABLE_COUNTRY,
                columns,
                DBController.COLUMN_ID + " = " + id,
                null, null, null, null
        );
        Country result;
        if (cursor.moveToFirst()) {
            result = cursorToCountry(cursor);
        } else {
            result = new Country();
        }
        cursor.close();
        return result;
    }

    @Override
    public ArrayList<Country> all() {
        Cursor cursor = readableDatabase.query(
                DBController.TABLE_COUNTRY,
                columns,
                null, null, null, null, null
        );
        ArrayList<Country> result = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                result.add(cursorToCountry(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return result;
    }

    @Override
    public Boolean update(Long id, ContentValues values) {
        Integer updates = writableDatabase.update(
                DBController.TABLE_COUNTRY,
                values,
                DBController.COLUMN_ID + " = " + id,
                null
        );
        return updates > 0;
    }

    private Country cursorToCountry(Cursor cursor) {
        Country result = new Country();
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
}
