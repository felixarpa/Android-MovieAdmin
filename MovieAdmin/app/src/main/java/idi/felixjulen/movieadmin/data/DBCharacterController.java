package idi.felixjulen.movieadmin.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import idi.felixjulen.movieadmin.domain.model.Character;

public class DBCharacterController extends DBController<Character> {

    private static final String TABLE_NAME = "CHARACTER";

    public DBCharacterController(Context context) {
        super(context, TABLE_NAME);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Character.createTable());
    }

    @Override
    public ArrayList<Character> getAll() {
        String[] columns = { "name", "image_file_path" };
        return where(TABLE_NAME, columns, null, null);
    }

    @Override
    public ArrayList<Character> getWhere(String attribute, String value) {
        String[] columns = { "name", "image_file_path" };
        String[] where = { value };
        return where(TABLE_NAME, columns, 0, where);
    }

    @Override
    public Boolean update(Character character) {
        return true;
    }

    @Override
    public Boolean insert(Character character) {
        return true;
    }

    @Override
    public Boolean delete(Character character) {
        return true;
    }

    @Override
    protected void add(Cursor cursor, ArrayList<Character> characters) {
        String name = cursor.getString(cursor.getColumnIndex("name"));
        String imageFilePath = cursor.getString(cursor.getColumnIndex("image_file_path"));
        characters.add(
                new Character(name, imageFilePath)
        );
    }
}
