package idi.felixjulen.movieadmin.domain.model;

public abstract class Person {

    private String name;
    private String imageFilePath;

    // regionConstructor
    public Person() {
    }

    public Person(String name, String imageFilePath) {
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
}
