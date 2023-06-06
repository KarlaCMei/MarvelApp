package com.example.marvelapp.retrofit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    private static Retrofit instance;
    private static final String BASE_URL = "https://gateway.marvel.com:443/";

    public static Retrofit getRetrofitInstance() {

        if (instance == null) {
            HttpLoggingInterceptor interceptorLogs = new HttpLoggingInterceptor();
            interceptorLogs.level(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient httpClient = new OkHttpClient.Builder().addInterceptor(new MarvelInterceptor())
                    .addInterceptor(interceptorLogs).build();

            instance = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient)
                    .build();
        }
        return instance;
    }

}
