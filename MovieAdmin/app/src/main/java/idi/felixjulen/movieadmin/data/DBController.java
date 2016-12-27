package idi.felixjulen.movieadmin.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public abstract class DBController<T> extends SQLiteOpenHelper implements IDBController<T> {

    private static final Integer VERSION = 1;
    private static final String DB_NAME = "MOVIE_ADMIN_DB";
    private String tableName;


    public DBController(Context context, String tableName) {
        super(context, DB_NAME, null, VERSION);
        this.tableName = tableName;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + tableName);
        onCreate(sqLiteDatabase);
    }

    protected ArrayList<T> where(String tableName, String[] columns, Integer attr, String[] where) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String attributes = (attr == null) ? null : columns[attr] + " = ?";
        Cursor cursor = sqLiteDatabase.query(
                tableName,
                columns,
                attributes,
                where,
                null,
                null,
                null
        );
        ArrayList<T> ts = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                add(cursor, ts);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return ts;
    }

    protected abstract void add(Cursor cursor, ArrayList<T> ts);


}
