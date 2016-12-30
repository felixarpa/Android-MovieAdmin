package com.example.pr_idi.mydatabaseexample.data;

import android.content.ContentValues;
import android.content.Context;

import com.example.pr_idi.mydatabaseexample.domain.dataInterface.CtrlDirector;
import com.example.pr_idi.mydatabaseexample.domain.model.Director;

import java.util.ArrayList;


public class CtrlDirectorDB implements CtrlDirector {

    private DBController databaseController;

    public CtrlDirectorDB(Context context) {
        databaseController = new DBController(context);
    }

    @Override
    public Long insert(ContentValues values) {

    }

    @Override
    public Boolean delete(Long id) {

    }

    @Override
    public Director get(Long id) {

    }

    @Override
    public ArrayList<Director> all() {

    }

    @Override
    public Boolean update(Long id, ContentValues values) {

    }
}
