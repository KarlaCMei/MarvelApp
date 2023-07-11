package com.example.marvelapp.webview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.marvelapp.R;
import com.example.marvelapp.databinding.ActivitySuperHeroDetailBinding;
import com.example.marvelapp.databinding.ActivityUrlBinding;
import com.example.marvelapp.retrofit.model.Result;
import com.example.marvelapp.retrofit.model.Url;
import com.example.marvelapp.superherodetail.viewmodel.SuperHeroDetailViewModel;
import com.example.marvelapp.utils.BaseActivity;
import com.example.marvelapp.utils.BaseViewModel;
import com.example.marvelapp.utils.constants.Constants;

import java.util.List;

public class UrlActivity extends BaseActivity<ActivityUrlBinding, BaseViewModel> {
    private WebView sitioWeb;
    private List<Url> urlWeb;


    @Override
    protected BaseViewModel createViewModel() {
        return new ViewModelProvider(this).get(BaseViewModel.class);

    }

    @NonNull
    @Override
    protected ActivityUrlBinding createViewBinding(LayoutInflater layoutInflater) {
        return ActivityUrlBinding.inflate(layoutInflater);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String urlComics = getIntent().getExtras().getString(Constants.URL_COMICS);

        sitioWeb = findViewById(R.id.webview);
        sitioWeb.setWebViewClient(new WebViewClient());
        sitioWeb.getSettings().setDomStorageEnabled(true);
        sitioWeb.getSettings().setJavaScriptEnabled(true);
        sitioWeb.loadUrl(urlComics);


    }
}