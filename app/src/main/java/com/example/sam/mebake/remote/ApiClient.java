package com.example.sam.mebake.remote;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sam on 11/29/17.
 */

public class ApiClient {
    public static final String BASE_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/";
    private static Retrofit retrofit;

    public static Retrofit getClient(){


        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();

        retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .callFactory(httpClientBuilder.build())
                    .build();

        return retrofit;
    }
}
