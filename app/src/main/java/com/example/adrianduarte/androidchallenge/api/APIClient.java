package com.example.adrianduarte.androidchallenge.api;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

    // Constants
    private static final String BASE_URL = "https://api.imgur.com/";
    private static final String CLIENT_ID = "f8db18ea9eac7a9";

    // Attributes
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        // Initialize http login interceptor
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        // Initialize http client
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(httpLoggingInterceptor);
        httpClient.addInterceptor(new Interceptor() {
              @Override
              public Response intercept(Interceptor.Chain chain) throws IOException {
              Request original = chain.request();
              Request request = original.newBuilder()
                  .header("Authorization", "Client-ID " + CLIENT_ID)
                  .method(original.method(), original.body())
                  .build()
              ;
              return chain.proceed(request);
              }
        });
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                return chain.proceed(request);
            }
        });

        // Initialize retrofit
        retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
        ;

        return retrofit;
    }

}