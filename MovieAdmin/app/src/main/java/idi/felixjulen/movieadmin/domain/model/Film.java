package idi.felixjulen.movieadmin.domain.model;

import android.graphics.Bitmap;

/**
 * Film
 * Created by pr_idi on 10/11/16.
 */
public class Film {

    // Basic film data manipulation class
    // Contains basic information on the film

    private Long _id;
    private String title;
    private Long director;
    private String country;
    private Integer year;
    private Long protagonist;
    private Integer rate;
    private Bitmap image;

    public Long getId() {
        return _id;
    }

    public void setId(Long id) {
        this._id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getDirector() {
        return director;
    }

    public void setDirector(Long director) {
        this.director = director;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Long getProtagonist() {
        return protagonist;
    }

    public void setProtagonist(Long protagonist) {
        this.protagonist = protagonist;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}