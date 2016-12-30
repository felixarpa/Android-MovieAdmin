package idi.felixjulen.movieadmin.domain.model;

public class Movie {

    // region Attributes

    private String title;
    private Country country;
    private Integer year;
    private Person director;
    private Person mainCharacter;
    private Integer rate;


    // endregion

    // region Contructor

    public Movie() {
    }

    public Movie(String title, Country country, Integer year, Person director, Person mainCharacter, Integer rate) throws IncorrectRateException {
        this.title = title;
        this.country = country;
        this.year = year;
        this.director = director;
        this.mainCharacter = mainCharacter;
        setRate(rate);

    }

    // endregion

    // region Getters

    public String getTitle() {
        return title;
    }

    public Country getCountry() {
        return country;
    }

    public Integer getYear() {
        return year;
    }

    public Person getDirector() {
        return director;
    }

    public Person getMainCharacter() {
        return mainCharacter;
    }

    public Integer getRate() {
        return rate;
    }

    // endregion

    // region Setters

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setDirector(Person director) {
        this.director = director;
    }

    public void setMainCharacter(Person mainCharacter) {
        this.mainCharacter = mainCharacter;
    }

    public void setRate(Integer rate) throws IncorrectRateException {
        if (rate < 0 || rate > 10) throw new IncorrectRateException();
        this.rate = rate;
    }

    // endregion




}
