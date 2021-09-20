package dtos;

import entities.Movie;
import java.util.ArrayList;
import java.util.List;

public class MovieDTO {
        private String title;
        private int year;
        private List<String> actors = new ArrayList<>();

    public MovieDTO(Movie m) {
        this.title = m.getTitel();
        this.year = m.getYear();
        this.actors = m.getActors();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<String> getActors() {
        return actors;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }
}
