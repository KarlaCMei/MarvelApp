package com.example.marvelapp.utils;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.viewbinding.ViewBinding;

import com.airbnb.lottie.LottieAnimationView;
import com.example.marvelapp.R;
import com.example.marvelapp.retrofit.model.Result;

public abstract class BaseActivity <BINDING extends ViewBinding, VM extends BaseViewModel> extends AppCompatActivity {

    protected VM viewModel;
    protected BINDING binding;
    private Dialog dialog;
    protected abstract VM createViewModel();

    @NonNull
    protected abstract BINDING createViewBinding(LayoutInflater layoutInflater);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = createViewBinding(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        viewModel = createViewModel();
        observers();
    }


    private void observers() {

        viewModel.loading.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLoading) {
                if(isLoading){
                    showProgress();
                }else{
                    hideProgress();
                }
            }
        });

        viewModel.msgError.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String msgError) {
                dialogErrorLottie(msgError);
                //Toast.makeText(BaseActivity.this, msgError, Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void showProgress(){

        dialog = new Dialog(BaseActivity.this, R.style.customLottie);
        dialog.setContentView(R.layout.dialog_search_lottie);
        dialog.show();

        /*AlertDialog.Builder builder = new AlertDialog.Builder(BaseActivity.this, R.style.customLottie);
        LayoutInflater inflater = BaseActivity.this.getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_search_lottie, null);
        builder.setView(view);
        dialog = builder.create();
        dialog.show();*/
    }

    private void hideProgress(){
        dialog.dismiss();
    }


    private void dialogErrorLottie(String error){
        AlertDialog.Builder builder = new AlertDialog.Builder(BaseActivity.this);
        LayoutInflater inflater = BaseActivity.this.getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_no_results_found, null);
        builder.setView(view);

        TextView txtMensajeError;
        txtMensajeError =  view.findViewById(R.id.txtMsjError);
        txtMensajeError.setText(error);

        builder.show();
    }


}
