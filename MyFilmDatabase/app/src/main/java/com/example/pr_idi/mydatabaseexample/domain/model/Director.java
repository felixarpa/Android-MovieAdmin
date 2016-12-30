package com.example.pr_idi.mydatabaseexample.domain.model;

import android.graphics.Bitmap;

public class Director {

    private Long _id;
    private String name;
    private Bitmap image;

    public Long getId() {
        return _id;
    }

    public void setId(Long id) {
        this._id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
