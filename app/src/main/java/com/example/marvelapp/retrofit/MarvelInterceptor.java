package com.example.marvelapp.retrofit;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class MarvelInterceptor implements Interceptor {

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request original = chain.request();
        HttpUrl originalUrl = original.url();

        HttpUrl url = originalUrl.newBuilder()
                .addQueryParameter("apikey","658791e59fd52e517abe2e09f27b9c17")
                .addQueryParameter("ts", "1")
                .addQueryParameter("hash", "3d60d3b8d2c2b56ed6d0c03d504ba2c2")
                .build();
        Request.Builder requestBuilder = original.newBuilder().url(url);
        Request request = requestBuilder.build();

        return chain.proceed(request);
    }
}
