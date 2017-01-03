package idi.felixjulen.movieadmin.domain.controller;

import java.util.ArrayList;

import idi.felixjulen.movieadmin.domain.model.Character;

public interface DefaultDataController<T> {

    void makeDefault();

    ArrayList<T> list();

    Character get(Long id);
}
