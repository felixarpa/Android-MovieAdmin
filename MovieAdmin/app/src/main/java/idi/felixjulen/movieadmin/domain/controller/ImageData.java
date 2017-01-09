package idi.felixjulen.movieadmin.domain.controller;

import android.graphics.Bitmap;

public class ImageData {

    private Bitmap image;
    private String name;

    public static ImageData build(Bitmap image, String name) {
        return new ImageData(image, name);
    }

    private ImageData(Bitmap image, String name) {
        this.image = image;
        this.name = name;
    }

    public Bitmap getImage() {
        return image;
    }

    public String getName() {
        return name;
    }
}
