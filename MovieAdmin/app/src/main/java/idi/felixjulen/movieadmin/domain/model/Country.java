package idi.felixjulen.movieadmin.domain.model;

public class Country {

    private String name;
    private String imageFilePath;

    // regionConstructor
    public Country() {
    }

    public Country(String name, String imageFilePath) {
        this.name = name;
        this.imageFilePath = imageFilePath;
    }

    // endregion

    // regionGetters

    public String getName() {
        return name;
    }

    public String getImageFilePath() {
        return imageFilePath;
    }

    // endregion

    public static String createTable() {
        return "CREATE TABLE COUNTRY (name TEXT PRIMARY KEY, image_file_path TEXT);";
    }

}
