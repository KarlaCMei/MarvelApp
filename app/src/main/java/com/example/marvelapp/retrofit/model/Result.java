package com.example.marvelapp.retrofit.model;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Date;

public class Result{
    public int id;
    public String name;
    public String description;
    public Date modified;
    public Thumbnail thumbnail;
    public String resourceURI;
    public Comics comics;
    public Series series;
    public Stories stories;
    public Events events;
    public ArrayList<Url> urls;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return thumbnail.getUrl();
    }
    public void setImage(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }


    /*@NonNull
        @Override
        public String toString() {
            return name;
        }*/
    @NonNull
    @Override
    public String toString() {
        return thumbnail.getUrl();
    }
}
