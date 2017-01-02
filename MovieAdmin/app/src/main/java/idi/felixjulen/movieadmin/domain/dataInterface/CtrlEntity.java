package idi.felixjulen.movieadmin.domain.dataInterface;

import android.content.ContentValues;

import java.util.ArrayList;

public interface CtrlEntity<T> {

    Long insert(ContentValues values);
    Boolean delete(Long id);
    T get(Long id);
    ArrayList<T> all();
    Boolean update(Long id, ContentValues values);
    void purge();
}
