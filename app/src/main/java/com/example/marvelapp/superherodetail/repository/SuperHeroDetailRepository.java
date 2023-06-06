package com.example.marvelapp.superherodetail.repository;

import com.example.marvelapp.listsuperheroesresults.repository.ListSuperHeroResultsRepository;
import com.example.marvelapp.retrofit.CustomCallback;
import com.example.marvelapp.retrofit.MarvelService;
import com.example.marvelapp.retrofit.RetrofitService;
import com.example.marvelapp.retrofit.model.Root;

public class SuperHeroDetailRepository {

    public static SuperHeroDetailRepository instance;

    public static SuperHeroDetailRepository getInstance(){
        if (instance == null) instance = new SuperHeroDetailRepository();
        return instance;
    }

    public void searchSuperHeroId(CustomCallback<Root> callback, int id){
        MarvelService service = RetrofitService.getRetrofitInstance().create(MarvelService.class);
        service.getCharecterResultsId(id).enqueue(callback);
    }

}
