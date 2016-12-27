package idi.felixjulen.movieadmin.domain.model;

public class Director extends Person {

    public static String createTable() {
        return "CREATE TABLE DIRECTOR (name TEXT PRIMARY KEY, image_file_path TEXT);";
    }

}
