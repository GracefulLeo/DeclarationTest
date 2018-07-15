package com.example.adventurer.declarationtest.operations;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.adventurer.declarationtest.model.APIResponse;
import com.example.adventurer.declarationtest.model.Item;
import com.example.adventurer.declarationtest.utils.App;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkOperations {

    private APIResponse mRespons;
    private Context mContext;

    public APIResponse getResponses() {

        App.getServerAPI().getResponse().enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(@NonNull Call<APIResponse> call,
                                   @NonNull Response<APIResponse> response) {
                if (response.body() != null) {
                    mRespons = response.body();
                    System.out.println(mRespons == null);
                }
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                Toast.makeText(mContext.getApplicationContext(), "An error occurred during networking", Toast.LENGTH_SHORT).show();
            }
        });
        return mRespons;
    }

}
