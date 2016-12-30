package com.example.pr_idi.mydatabaseexample.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.pr_idi.mydatabaseexample.domain.dataInterface.CtrlCountry;
import com.example.pr_idi.mydatabaseexample.domain.model.Country;

import java.util.ArrayList;


public class CtrlFilmDB implements CtrlCountry {

    private DBController databaseController;
    private SQLiteDatabase writableDatabase;
    private SQLiteDatabase readableDatabase;

    public CtrlFilmDB(Context context) {
        databaseController = new DBController(context);
        writableDatabase = databaseController.getWritableDatabase();
        readableDatabase = databaseController.getReadableDatabase();
    }

    @Override
    public Long insert(ContentValues values) {
        return writableDatabase.insert(DBController.TABLE_COUNTRY, null, values);
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

    }

    @Override
    public ArrayList<Country> all() {

    }

    @Override
    public Boolean update(Long id, ContentValues values) {

    }
}
