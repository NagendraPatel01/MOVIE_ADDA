package com.apk.movie1.model;

public class Trendingpersonmoviemodel {

    String title;
    String vote_average;
    String release_date;
    String backdrop_path;
    String id;


    @Override
    public String toString() {
        return "Trendingpersonmoviemodel{" +
                "title='" + title + '\'' +
                ", vote_average='" + vote_average + '\'' +
                ", release_date='" + release_date + '\'' +
                ", backdrop_path='" + backdrop_path + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }
}

