package com.redfox.redfox.beans;

import java.io.InputStream;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

public class MovieBean implements Serializable {
    private int id;
    private String title;
    private String director;
    private ArrayList<String> mainCharacters;
    private String genre;
    private Date yearOfProduction;
    private double averageRating;
    private String synopsis;
    private String base64Image;
    
    public MovieBean() {
    }

    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }

   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public ArrayList<String> getMainCharacters() {
        return mainCharacters;
    }

    public void setMainCharacters(ArrayList<String> mainCharacters) {
        this.mainCharacters = mainCharacters;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Date getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(Date yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }
}
