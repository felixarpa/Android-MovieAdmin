package idi.felixjulen.movieadmin.domain.model;

public class Character extends Person {

    public Character(String name, String imageFilePath) {
        super(name, imageFilePath);
    }

    public static String createTable() {
        return "CREATE TABLE CHARACTER (name TEXT PRIMARY KEY, image_file_path TEXT);";
    }
}
