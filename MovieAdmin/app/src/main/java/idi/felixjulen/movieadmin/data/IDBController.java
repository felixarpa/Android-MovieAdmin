package idi.felixjulen.movieadmin.data;

import java.util.ArrayList;

public interface IDBController<T> {

    ArrayList<T> getAll();

    ArrayList<T> getWhere(String attribute, String value);

    Boolean update(T t);

    Boolean insert(T t);

    Boolean delete(T t);

}
