package idi.felixjulen.movieadmin.domain.model;

/**
 * Film
 * Created by pr_idi on 10/11/16.
 */
public class Film {

    // Basic film data manipulation class
    // Contains basic information on the film

    private Long _id;
    private String title;
    private Integer director;
    private String country;
    private Integer year;
    private Integer protagonist;
    private Integer rate;

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

    public Integer getDirector() {
        return director;
    }

    public void setDirector(Integer director) {
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

    public Integer getProtagonist() {
        return protagonist;
    }

    public void setProtagonist(Integer protagonist) {
        this.protagonist = protagonist;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        return String.format("%s - %s", title, director);
    }
}