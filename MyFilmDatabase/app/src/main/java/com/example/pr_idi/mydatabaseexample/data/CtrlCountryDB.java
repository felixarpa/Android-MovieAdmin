package com.example.pr_idi.mydatabaseexample.data;

import android.content.ContentValues;
import android.content.Context;

import com.example.pr_idi.mydatabaseexample.domain.dataInterface.CtrlCountry;

import java.util.ArrayList;


public class CtrlCountryDB implements CtrlCountry {

    private DBController databaseController;

    public CtrlCountryDB(Context context) {
        databaseController = new DBController(context);
    }

    @Override
    public Long insert(ContentValues values) {

    }

    @Override
    public Boolean delete(Long id) {

    }

    @Override
    public Object get(Long id) {

    }

    @Override
    public ArrayList all() {

    }

    @Override
    public Boolean update(Long id, ContentValues values) {

    }
}
