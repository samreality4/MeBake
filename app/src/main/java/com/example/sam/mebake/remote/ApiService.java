package com.example.sam.mebake.remote;


import com.example.sam.mebake.Model.Recipes;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by sam on 11/29/17.
 */

public interface ApiService {
    @GET("baking.json")
    Call <ArrayList<Recipes>> getEverything();
}

