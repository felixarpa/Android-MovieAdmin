package idi.felixjulen.movieadmin.domain.dataInterface;

import idi.felixjulen.movieadmin.domain.model.Character;

public interface CtrlCharacter extends CtrlEntity<Character> {

    Character getByName(String name);

}
