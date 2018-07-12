package com.example.adventurer.declarationtest.operations;

import com.example.adventurer.declarationtest.model.APIResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ServerAPI {


    @GET("declaration")
    Call<APIResponse> getResponse();

}
