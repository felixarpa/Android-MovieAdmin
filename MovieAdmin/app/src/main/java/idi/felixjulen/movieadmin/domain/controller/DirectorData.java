package idi.felixjulen.movieadmin.domain.controller;

import android.content.Context;

import idi.felixjulen.movieadmin.data.CtrlDirectorDB;
import idi.felixjulen.movieadmin.domain.dataInterface.CtrlDirector;

public class DirectorData {

    private static DirectorData instance;
    private static CtrlDirector ctrl;
    private static Context context;

    public static DirectorData getInstance(Context mContext) {
        context = mContext;
        if (instance == null) {
            instance = new DirectorData(context);
        }
        return instance;
    }

    private DirectorData(Context context) {
        ctrl = CtrlDirectorDB.getInstance(context);
    }
}
