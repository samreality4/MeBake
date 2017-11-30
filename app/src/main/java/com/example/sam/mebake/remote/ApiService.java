package com.example.sam.mebake.remote;

import com.example.sam.mebake.Model.Ingredients;
import com.example.sam.mebake.Model.RecipeList;
import com.example.sam.mebake.Model.Recipes;
import com.example.sam.mebake.Model.Steps;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by sam on 11/29/17.
 */

public interface ApiService {
    @GET("/baking.json")
    Call<RecipeList> getEverything();
}

