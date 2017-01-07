package idi.felixjulen.movieadmin.domain.controller;

import java.util.ArrayList;

import idi.felixjulen.movieadmin.domain.model.Film;

interface FilmDataController extends DefaultDataController<Film> {

    ArrayList<Film> getWithCharacter(Long id);
    ArrayList<Film> getWithDirector(Long id);
}
