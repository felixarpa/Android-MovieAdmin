package idi.felixjulen.movieadmin.domain.dataInterface;

import java.util.ArrayList;

import idi.felixjulen.movieadmin.domain.model.Character;

public interface CtrlCharacter extends CtrlEntity<Character> {

    ArrayList<Character> search(String word);

}
