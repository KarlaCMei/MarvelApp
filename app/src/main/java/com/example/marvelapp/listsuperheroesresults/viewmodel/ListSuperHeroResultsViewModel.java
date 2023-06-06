package com.example.marvelapp.listsuperheroesresults.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.marvelapp.home.repository.HomeRepository;
import com.example.marvelapp.listsuperheroesresults.repository.ListSuperHeroResultsRepository;
import com.example.marvelapp.retrofit.CustomCallback;
import com.example.marvelapp.retrofit.model.Result;
import com.example.marvelapp.retrofit.model.Root;
import com.example.marvelapp.utils.BaseViewModel;

import java.util.List;

public class ListSuperHeroResultsViewModel extends BaseViewModel {

        private ListSuperHeroResultsRepository listSuperHeroResultsRepository;
    private MutableLiveData<List<Result>> getResults = new MutableLiveData<>();

    public ListSuperHeroResultsViewModel() {
        listSuperHeroResultsRepository = ListSuperHeroResultsRepository.getInstance();
    }

    public void resultsSuperHeroes(String name){
        loading.postValue(true);

        listSuperHeroResultsRepository.searchSuperHero(new CustomCallback<Root>() {
            @Override
            public void onSuccess(Root response) {
                Log.e("Mensajede", "onSuccess: " + response.copyright );
                getResults.postValue(response.data.results);
                loading.postValue(false);

            }

            @Override
            public void onFailed(Throwable throwable) {
                Log.e("Mensajede: ", "onFailed: " + throwable.getMessage());
                loading.postValue(false);

            }

        },name);
    }

    public LiveData<List<Result>> getSuperHeroes(){
        return getResults;
    }

}
