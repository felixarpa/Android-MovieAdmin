package idi.felixjulen.movieadmin.domain.model;

/**
 * Film
 * Created by pr_idi on 10/11/16.
 */
public class Film extends Entity {

    // Basic film data manipulation class
    // Contains basic information on the film

    private Long director;
    private String country;
    private Integer year;
    private Long protagonist;
    private Integer rate;

    public String getTitle() {
        return getName();
    }

    public void setTitle(String title) {
        setName(title);
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
}