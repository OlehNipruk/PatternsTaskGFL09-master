package patterns.example;

import java.io.Serializable;
import java.util.List;

public class Movie implements Serializable {
    private final String title;
    private final MovieType priceCode;
    private final String countryOfOrigin;
    private final String shortDescription;
    private final String director;
    private final List<String> actors;

    public enum MovieType {
        REGULAR, NEW_RELEASE, CHILDRENS, DRAMA, COMEDY, THRILLER
    }

    public Movie(String title, MovieType priceCode, String countryOfOrigin, String shortDescription, String director, List<String> actors) {
        this.title = title;
        this.priceCode = priceCode;
        this.countryOfOrigin = countryOfOrigin;
        this.shortDescription = shortDescription;
        this.director = director;
        this.actors = actors;
    }

    public MovieType getPriceCode() {
        return priceCode;
    }

    public String getTitle() {
        return title;
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getDirector() {
        return director;
    }

    public List<String> getActors() {
        return actors;
    }
    public static Movie createMovie(String title, MovieType priceCode, String countryOfOrigin, String shortDescription, String director, List<String> actors) {
        return new Movie(title, priceCode, countryOfOrigin, shortDescription, director, actors);
    }


}
