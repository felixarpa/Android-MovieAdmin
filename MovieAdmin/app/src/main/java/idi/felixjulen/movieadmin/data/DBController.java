package idi.felixjulen.movieadmin.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import idi.felixjulen.movieadmin.domain.controller.CharacterData;
import idi.felixjulen.movieadmin.domain.controller.DirectorData;
import idi.felixjulen.movieadmin.domain.controller.FilmData;

public class DBController extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "films.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_FILMS = "films";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_COUNTRY = "country";
    public static final String COLUMN_YEAR = "year";
    public static final String COLUMN_DIRECTOR = "director";
    public static final String COLUMN_MAIN_CHARACTER = "main_character";
    public static final String COLUMN_RATE = "rate";

    public static final String TABLE_DIRECTOR = "directors";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_IMAGE = "image";

    public static final String TABLE_CHARACTER = "characters";

    // Database creation sql statement
    private static final String CREATE_FILM =
            "create table " + TABLE_FILMS + "( "
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_TITLE + " text not null, "
            + COLUMN_COUNTRY + " text not null, "
            + COLUMN_YEAR + " integer not null, "
            + COLUMN_DIRECTOR + " integer not null, "
            + COLUMN_MAIN_CHARACTER + " integer not null, "
            + COLUMN_RATE + " integer"
            + ");";

    private static final String CREATE_DIRECTOR =
            "create table " + TABLE_DIRECTOR + "( "
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_NAME + " text not null, "
            + COLUMN_IMAGE + " text"
            + ");";

    private static final String CREATE_CHARACTER =
            "create table " + TABLE_CHARACTER + "( "
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_NAME + " text not null, "
            + COLUMN_IMAGE + " text"
            + ");";
    private final Context context;


    public DBController(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_FILM);
        database.execSQL(CREATE_DIRECTOR);
        database.execSQL(CREATE_CHARACTER);
        CharacterData.getInstance(context).makeDefault();
        DirectorData.getInstance(context);//.makeDefault();
        FilmData.getInstance(context).makeDefault();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DBController.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FILMS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DIRECTOR);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHARACTER);
        onCreate(db);
    }
}
