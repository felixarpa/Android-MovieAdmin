package idi.felixjulen.movieadmin.domain.dataInterface;

import android.content.ContentValues;

import java.util.ArrayList;

import idi.felixjulen.movieadmin.domain.model.Character;

public interface CtrlEntity<T> {

    Long insert(ContentValues values);
    Boolean delete(Long id);
    T get(Long id);
    ArrayList<T> all();
    Boolean update(Long id, ContentValues values);
}
