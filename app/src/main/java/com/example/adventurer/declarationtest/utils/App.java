package com.example.adventurer.declarationtest.utils;

import android.app.Application;

import com.example.adventurer.declarationtest.operations.ServerAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.adventurer.declarationtest.utils.AppConfig.API_BASE_URL;

public class App extends Application {

    private static ServerAPI serverAPI;
    private Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();

        retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        serverAPI = retrofit.create(ServerAPI.class);
    }

    public static ServerAPI getServerAPI() {
        return serverAPI;
    }

}
