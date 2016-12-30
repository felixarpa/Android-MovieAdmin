package idi.felixjulen.movieadmin.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import idi.felixjulen.movieadmin.domain.model.Country;

public class DBCountryController extends DBController<Country> {

    private static final String TABLE_NAME = "COUNTRY";

    public DBCountryController(Context context) {
        super(context, "COUNTRY");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Country.createTable());
    }

    @Override
    protected void add(Cursor cursor, ArrayList<Country> countries) {
        String name = cursor.getString(cursor.getColumnIndex("name"));
        String imageFilePath = cursor.getString(cursor.getColumnIndex("image_file_path"));
        countries.add(
                new Country(name, imageFilePath)
        );
    }

    @Override
    public ArrayList<Country> getAll() {
        String[] columns = { "name", "image_file_path" };
        return where(TABLE_NAME, columns, null, null);
    }

    @Override
    public ArrayList<Country> getWhere(String attribute, String value) {
        String[] columns = { "name", "image_file_path" };
        String[] where = { value };
        return where(TABLE_NAME, columns, 0, where);
    }

    @Override
    public Boolean update(Country country) {
        return null;
    }

    @Override
    public Boolean insert(Country country) {
        return null;
    }

    @Override
    public Boolean delete(Country country) {
        return null;
    }
}
