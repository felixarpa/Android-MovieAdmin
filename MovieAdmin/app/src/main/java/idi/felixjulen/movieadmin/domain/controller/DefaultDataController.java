package idi.felixjulen.movieadmin.domain.controller;

import java.util.ArrayList;

public interface DefaultDataController<T> {

    void makeDefault();

    ArrayList<T> list();

}
