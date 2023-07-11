package com.example.marvelapp.listsuperheroesresults.repository;

import com.example.marvelapp.retrofit.CustomCallback;
import com.example.marvelapp.retrofit.MarvelService;
import com.example.marvelapp.retrofit.RetrofitService;
import com.example.marvelapp.retrofit.model.Root;

public class ListSuperHeroResultsRepository {
    public static ListSuperHeroResultsRepository instance;

    public static ListSuperHeroResultsRepository getInstance() {
        if (instance == null) instance = new ListSuperHeroResultsRepository();
        return instance;
    }

    public void searchSuperHero(CustomCallback<Root> callback, String name){
        MarvelService service = RetrofitService.getRetrofitInstance().create(MarvelService.class);
        service.getCharecterResults(name).enqueue(callback);
    }
}
