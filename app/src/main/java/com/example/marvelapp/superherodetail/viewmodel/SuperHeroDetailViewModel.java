package com.example.marvelapp.superherodetail.viewmodel;

import android.database.Observable;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.marvelapp.listsuperheroesresults.repository.ListSuperHeroResultsRepository;
import com.example.marvelapp.retrofit.CustomCallback;
import com.example.marvelapp.retrofit.model.Result;
import com.example.marvelapp.retrofit.model.Root;
import com.example.marvelapp.retrofit.model.Url;
import com.example.marvelapp.superherodetail.repository.SuperHeroDetailRepository;
import com.example.marvelapp.utils.BaseViewModel;

import java.util.List;

public class SuperHeroDetailViewModel extends BaseViewModel {
    private MutableLiveData<Result> getResultsSuperHeros = new MutableLiveData<>();

    private MutableLiveData<String> getUrlComics = new MutableLiveData<>();
    private MutableLiveData<String> getUrldetail = new MutableLiveData<>();
    private MutableLiveData<String> getUrlWiki = new MutableLiveData<>();

    private SuperHeroDetailRepository superHeroDetailRepository;

    public SuperHeroDetailViewModel() {
        superHeroDetailRepository = SuperHeroDetailRepository.getInstance();
    }

    public void responseSuperHero(int id){
        loading.postValue(true);

        superHeroDetailRepository.searchSuperHeroId(new CustomCallback<Root>() {
            @Override
            public void onSuccess(Root response) {
                List<Result> result = response.data.results;

                if(!result.isEmpty()){
                    getResultsSuperHeros.postValue(result.get(0));
                    List<Url> listaurl = result.get(0).urls;

                    if(!listaurl.isEmpty()){
                        getUrldetail.postValue(String.valueOf(listaurl.get(0).getUrl()));

                    if(listaurl.size() > 1){
                        getUrlComics.postValue(String.valueOf(listaurl.get(1).getUrl()));
                    }

                    if(listaurl.size() > 2){
                        getUrlWiki.postValue(String.valueOf(listaurl.get(2).getUrl()));
                    }

                    }

                }

                Log.e("Mensajede", "onSuccess: " + response.copyright );
                loading.postValue(false);

            }

            @Override
            public void onFailed(Throwable throwable) {
                Log.e("Mensajede: ", "onFailed: " + throwable.getMessage());
                msgError.postValue(throwable.getMessage());
                //loading.postValue(false);
            }
        },id);
    }

    public LiveData<Result> getSuperHeroes(){
        return getResultsSuperHeros;
    }

    public LiveData<String> getUrlComics(){
        return getUrlComics;
    }

    public LiveData<String> getUrlDetail(){
        return getUrldetail;
    }

    public LiveData<String> getUrlWiki(){
        return getUrlWiki;
    }

}
