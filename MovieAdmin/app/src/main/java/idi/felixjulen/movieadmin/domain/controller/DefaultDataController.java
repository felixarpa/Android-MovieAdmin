package idi.felixjulen.movieadmin.domain.controller;

import java.util.ArrayList;

interface DefaultDataController<T> {

    void makeDefault();

    ArrayList<T> list();

    T get(Long id);

    void delete(Long id);
}
