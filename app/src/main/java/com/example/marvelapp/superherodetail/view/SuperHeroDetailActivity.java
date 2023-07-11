package com.example.marvelapp.superherodetail.view;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.example.marvelapp.databinding.ActivitySuperHeroDetailBinding;
import com.example.marvelapp.webview.UrlActivity;
import com.example.marvelapp.retrofit.model.Result;
import com.example.marvelapp.retrofit.model.Url;
import com.example.marvelapp.superherodetail.viewmodel.SuperHeroDetailViewModel;
import com.example.marvelapp.utils.BaseActivity;
import com.example.marvelapp.utils.constants.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SuperHeroDetailActivity extends BaseActivity<ActivitySuperHeroDetailBinding, SuperHeroDetailViewModel> {

    private List<Url> urlWeb;


    @Override
    protected SuperHeroDetailViewModel createViewModel() {
        return new ViewModelProvider(this).get(SuperHeroDetailViewModel.class);
    }

    @NonNull
    @Override
    protected ActivitySuperHeroDetailBinding createViewBinding(LayoutInflater layoutInflater) {
        return ActivitySuperHeroDetailBinding.inflate(layoutInflater);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int superHeroId = getIntent().getExtras().getInt(Constants.SUPERHERO_ID);
        viewModel.responseSuperHero(superHeroId);

        observers();

        binding.btnComics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (viewModel.getUrlComics().getValue() == null) {
                    viewModel.msgError.postValue("No se encontraron resultados");
                } else {

                    Intent intent = new Intent(SuperHeroDetailActivity.this, UrlActivity.class);
                    intent.putExtra(Constants.URL_COMICS, viewModel.getUrlComics().getValue());
                    startActivity(intent);
                }


            }
        });

        binding.btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (viewModel.getUrlDetail().getValue() == null) {
                    viewModel.msgError.postValue("No se encontraron resultados");
                } else {
                    Intent intent = new Intent(SuperHeroDetailActivity.this, UrlActivity.class);
                    intent.putExtra(Constants.URL_COMICS, viewModel.getUrlDetail().getValue());
                    startActivity(intent);
                }

            }
        });

        binding.btnWiki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewModel.getUrlWiki().getValue() == null) {
                    viewModel.msgError.postValue("No se encontraron resultados");
                } else {
                    Intent intent = new Intent(SuperHeroDetailActivity.this, UrlActivity.class);
                    intent.putExtra(Constants.URL_COMICS, viewModel.getUrlWiki().getValue());
                    startActivity(intent);
                }

            }
        });
    }

    private void observers() {

        viewModel.getSuperHeroes().observe(this, new Observer<Result>() {
            @Override
            public void onChanged(Result result) {
                binding.textSuperHeroName.setText(result.getName());
                binding.textDescription.setText(result.getDescription());
                Picasso.with(SuperHeroDetailActivity.this).load(result.getImage() + ".jpg").into(binding.imgSuperHero);
                Log.e("URL Imagen del super heroe", result.getImage());
            }
        });

    }


}
