package idi.felixjulen.movieadmin.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.util.ArrayList;

import idi.felixjulen.movieadmin.domain.dataInterface.CtrlCharacter;
import idi.felixjulen.movieadmin.domain.model.Character;

public class CtrlCharacterDB implements CtrlCharacter {

    private SQLiteDatabase writableDatabase;
    private SQLiteDatabase readableDatabase;
    private String[] columns = {
            DBController.COLUMN_ID,
            DBController.COLUMN_NAME,
            DBController.COLUMN_IMAGE
    };

    public CtrlCharacterDB(Context context) {
        DBController databaseController = new DBController(context);
        writableDatabase = databaseController.getWritableDatabase();
        readableDatabase = databaseController.getReadableDatabase();
    }

    @Override
    public Long insert(ContentValues values) {
        return writableDatabase.insert(
                DBController.TABLE_CHARACTER,
                null,
                values
        );
    }

    @Override
    public Boolean delete(Long id) {
        Integer deletions = writableDatabase.delete(
                DBController.TABLE_CHARACTER,
                DBController.COLUMN_ID + " = " + id,
                null
        );
        return deletions > 0;
    }

    @Override
    public Character get(Long id) {
        Cursor cursor = readableDatabase.query(
                DBController.TABLE_COUNTRY,
                columns,
                DBController.COLUMN_ID + " = " + id,
                null, null, null, null
        );
        Character result;
        if (cursor.moveToFirst()) {
            result = cursorToCharacter(cursor);
        } else {
            result = new Character();
        }
        cursor.close();
        return result;
    }

    @Override
    public ArrayList<Character> all() {
        Cursor cursor = readableDatabase.query(
                DBController.TABLE_CHARACTER,
                columns,
                null, null, null, null, null
        );
        ArrayList<Character> result = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                result.add(cursorToCharacter(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return result;
    }

    @Override
    public Boolean update(Long id, ContentValues values) {
        Integer updates = writableDatabase.update(
                DBController.TABLE_CHARACTER,
                values,
                DBController.COLUMN_ID + " = " + id,
                null
        );
        return updates > 0;
    }

    private Character cursorToCharacter(Cursor cursor) {
        Character result = new Character();
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
