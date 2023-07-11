package com.example.marvelapp.retrofit;

import com.example.marvelapp.retrofit.model.Root;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MarvelService {

    @GET("/v1/public/characters")
    Call<Root> getCharecterResults(@Query("nameStartsWith") String name);


    @GET("/v1/public/characters/{superHeroId}")
    Call<Root> getCharecterResultsId(@Path("superHeroId") int id);
}
