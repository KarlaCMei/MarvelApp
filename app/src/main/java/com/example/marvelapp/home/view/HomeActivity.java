package com.example.marvelapp.home.view;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.marvelapp.databinding.ActivityHomeBinding;
import com.example.marvelapp.home.viewmodel.HomeViewModel;
import com.example.marvelapp.listsuperheroesresults.view.ListSuperHeroResultsActivity;
import com.example.marvelapp.retrofit.MarvelAPIHashGenerator;
import com.example.marvelapp.retrofit.model.Result;
import com.example.marvelapp.utils.BaseActivity;
import com.example.marvelapp.utils.constants.Constants;

import java.util.List;

public class HomeActivity extends BaseActivity<ActivityHomeBinding, HomeViewModel> {
    String publicKey = "658791e59fd52e517abe2e09f27b9c17";
    String privateKey = "17534ccf51a609d1c4ca0a01fd79f0f2f71f2306";
    String timestamp = "1";
    String hash = MarvelAPIHashGenerator.generateHash(timestamp, privateKey, publicKey);


    @Override
    protected HomeViewModel createViewModel() {
        return new ViewModelProvider(this).get(HomeViewModel.class);
    }

    @NonNull
    @Override
    protected ActivityHomeBinding createViewBinding(LayoutInflater layoutInflater) {
        return ActivityHomeBinding.inflate(layoutInflater);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //System.out.println("Hash: " + hash);
        Log.e("Hash: ", hash);
        Log.e("Ts: ", timestamp);

        binding.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( (binding.txtSearchCharacter.getText() == null  ||  binding.txtSearchCharacter.getText().toString().equals(""))){
                    Toast.makeText(HomeActivity.this,"Ingrese un nombre", Toast.LENGTH_LONG).show();
                }else{
                        Intent intent = new Intent(HomeActivity.this, ListSuperHeroResultsActivity.class);
                    intent.putExtra(Constants.TITLE_SEARCH_NAME, binding.txtSearchCharacter.getText().toString());
                    startActivity(intent);
                }
            }
        });
    }



}