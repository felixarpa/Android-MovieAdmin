package idi.felixjulen.movieadmin.domain.model;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class MovieTest {

    private Movie movie;

    @Before
    public void setUp() {
        movie = new Movie();
    }

    @Test
    public void setRate3() throws Exception {
        movie.setRate(3);
        Integer result = 3;
        assertEquals("Movie's rate is 3", result, movie.getRate());
    }

    @Test
    public void setRate10() throws Exception {
        movie.setRate(10);
        Integer result = 10;
        assertEquals("Movie's rate is 10", result, movie.getRate());
    }

    @Test(expected = IncorrectRateException.class)
    public void setIncorrectHighRate() throws Exception {
        movie.setRate(11);
    }

    @Test(expected = IncorrectRateException.class)
    public void setIncorrectLowRate() throws Exception {
        movie.setRate(-5);
    }

}