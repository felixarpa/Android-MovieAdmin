package idi.felixjulen.movieadmin.domain.dataInterface;

import java.util.ArrayList;

import idi.felixjulen.movieadmin.domain.model.Film;

public interface CtrlFilm extends CtrlEntity<Film> {

    ArrayList<Film> getByCharacter(Long id);
    ArrayList<Film> getByDirector(Long id);

}
