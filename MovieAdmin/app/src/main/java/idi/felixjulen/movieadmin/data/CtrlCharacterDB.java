package idi.felixjulen.movieadmin.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import idi.felixjulen.movieadmin.domain.dataInterface.CtrlCharacter;
import idi.felixjulen.movieadmin.domain.model.Character;

public class CtrlCharacterDB implements CtrlCharacter {

    private static CtrlCharacter instance;
    private static SQLiteDatabase writableDatabase;
    private static SQLiteDatabase readableDatabase;
    private static final String[] columns = {
            DBController.COLUMN_ID,
            DBController.COLUMN_NAME,
            DBController.COLUMN_IMAGE
    };

    public static CtrlCharacter getInstance(Context context) {
        if (instance == null) {
            instance = new CtrlCharacterDB(context);
        }
        return instance;
    }

    private CtrlCharacterDB(Context context) {
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
                DBController.TABLE_CHARACTER,
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
        return characterArrayListFrom(cursor);
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

    @Override
    public void purge() {
        writableDatabase.delete(DBController.TABLE_CHARACTER, null, null);
    }

    private Character cursorToCharacter(Cursor cursor) {
        Character result = new Character();
        result.setId(cursor.getLong(cursor.getColumnIndex(DBController.COLUMN_ID)));
        result.setName(cursor.getString(cursor.getColumnIndex(DBController.COLUMN_NAME)));
        result.setImage(cursor.getString(cursor.getColumnIndex(DBController.COLUMN_IMAGE)));
        return result;
    }

    private ArrayList<Character> characterArrayListFrom(Cursor cursor) {
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
    public Character getByName(String name) {
        Cursor cursor = readableDatabase.query(
                DBController.TABLE_CHARACTER,
                columns,
                DBController.COLUMN_NAME + " = ?",
                new String[]{name}, null, null, null
        );
        Character result;
        if (cursor.moveToFirst()) {
            result = cursorToCharacter(cursor);
        } else {
            result = new Character();
            result.setId(-1L);
        }
        cursor.close();
        return result;
    }
}
