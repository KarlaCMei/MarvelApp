package com.example.marvelapp.listsuperheroesresults.view;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import com.example.marvelapp.databinding.ActivityListSuperHeroResultsBinding;
import com.example.marvelapp.listsuperheroesresults.view.adapterlistsuperhero.CustomSuperHeroAdapter;
import com.example.marvelapp.listsuperheroesresults.view.adapterlistsuperhero.OnClicSuperHeroListener;
import com.example.marvelapp.listsuperheroesresults.viewmodel.ListSuperHeroResultsViewModel;
import com.example.marvelapp.retrofit.model.Result;
import com.example.marvelapp.superherodetail.view.SuperHeroDetailActivity;
import com.example.marvelapp.utils.BaseActivity;
import com.example.marvelapp.utils.constants.Constants;

import java.util.List;

public class ListSuperHeroResultsActivity extends BaseActivity<ActivityListSuperHeroResultsBinding, ListSuperHeroResultsViewModel> {
    private CustomSuperHeroAdapter customSuperHeroAdapter;

    @Override
    protected ListSuperHeroResultsViewModel createViewModel() {
        return new ViewModelProvider(this).get(ListSuperHeroResultsViewModel.class);

    }

    @NonNull
    @Override
    protected ActivityListSuperHeroResultsBinding createViewBinding(LayoutInflater layoutInflater) {
        return ActivityListSuperHeroResultsBinding.inflate(layoutInflater);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String superHeroName = getIntent().getExtras().getString(Constants.TITLE_SEARCH_NAME);
        viewModel.resultsSuperHeroes(superHeroName);
        observers();

    }

    private void observers() {
        viewModel.getSuperHeroes().observe(this, new Observer<List<Result>>() {
            @Override
            public void onChanged(List<Result> results) {
                Log.e("Resultados: ", String.valueOf(results));

                customSuperHeroAdapter = new CustomSuperHeroAdapter(results, new OnClicSuperHeroListener() {
                    @Override
                    public void onClicSuperHeroListener(int superHeroId) {
                        Intent detailActivity = new Intent(ListSuperHeroResultsActivity.this, SuperHeroDetailActivity.class);
                        detailActivity.putExtra(Constants.SUPERHERO_ID, superHeroId);
                        Log.e("ID de superheroe: ", String.valueOf(superHeroId));

                        startActivity(detailActivity);
                    }
                });
                binding.listSuperHeros.setAdapter(customSuperHeroAdapter);

            }
        });
    }


}