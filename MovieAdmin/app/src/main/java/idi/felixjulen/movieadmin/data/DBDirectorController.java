package idi.felixjulen.movieadmin.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import idi.felixjulen.movieadmin.domain.model.Director;

public class DBDirectorController extends DBController<Director> {

    private static final String TABLE_NAME = "DIRECTOR";

    public DBDirectorController(Context context) {
        super(context, "DIRECTOR");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(createTable());
    }

    @Override
    protected void add(Cursor cursor, ArrayList<Director> directors) {
        String name = cursor.getString(cursor.getColumnIndex("name"));
        String imageFilePath = cursor.getString(cursor.getColumnIndex("image_file_path"));
        directors.add(
                new Director(name, imageFilePath)
        );
    }

    @Override
    public ArrayList<Director> getAll() {
        String[] columns = { "name", "image_file_path" };
        return where(TABLE_NAME, columns, null, null);
    }

    @Override
    public ArrayList<Director> getWhere(String attribute, String value) {
        String[] columns = { "name", "image_file_path" };
        String[] where = { value };
        return where(TABLE_NAME, columns, 0, where);
    }

    @Override
    public Boolean update(Director director) {
        return null;
    }

    @Override
    public Boolean insert(Director director) {
        return null;
    }

    @Override
    public Boolean delete(Director director) {
        return null;
    }

    private static String createTable() {
        return "CREATE TABLE DIRECTOR (name TEXT PRIMARY KEY, image_file_path TEXT);";
    }

}
