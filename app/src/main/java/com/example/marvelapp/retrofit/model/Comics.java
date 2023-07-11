package com.example.marvelapp.retrofit.model;

import java.util.ArrayList;

public class Comics{
    public String collectionURI;
    public ArrayList<Item> items;


    public String getUrl() {
        return collectionURI;
    }
    public void setImage(String collectionURI) {
        this.collectionURI = collectionURI;
    }
}
