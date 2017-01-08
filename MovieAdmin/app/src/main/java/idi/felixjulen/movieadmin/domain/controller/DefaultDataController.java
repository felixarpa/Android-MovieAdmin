package idi.felixjulen.movieadmin.domain.controller;

import java.util.ArrayList;

import idi.felixjulen.movieadmin.domain.model.Entity;

interface DefaultDataController<T extends Entity> {

    void makeDefault();

    ArrayList<T> list();

    ArrayList<T> search(String filter);

    T get(Long id);

    void delete(Long id);

    void update(T data);

    Long add(T data);
}
