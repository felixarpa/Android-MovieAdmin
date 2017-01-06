package idi.felixjulen.movieadmin.domain.dataInterface;

import idi.felixjulen.movieadmin.domain.model.Director;

public interface CtrlDirector extends CtrlEntity<Director> {

    Director getByName(String name);

}
