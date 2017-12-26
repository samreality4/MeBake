package com.example.sam.mebake;

import com.example.sam.mebake.Model.Recipes;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sam on 11/29/17.
 */

public class RecipeList {
    @SerializedName("recipes")
    private List<Recipes> recipes;


    private RecipeList() {

    }

    public List<Recipes> getRecipes() {
        return recipes;


    }

    public void setRecipes(List<Recipes> recipes){
        this.recipes = recipes;
    }
}
