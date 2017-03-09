package com.kehinde.alc.API;

import com.google.gson.JsonObject;

import java.lang.reflect.Array;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by kehinde on 3/7/17.
 */

public interface APIService {

    @GET("search/users?q=type:user+location:lagos+language:java")
    Call<JsonObject> getDevelopers();
}
