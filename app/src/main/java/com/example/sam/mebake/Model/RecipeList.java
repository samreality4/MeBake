package com.example.sam.mebake.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by sam on 11/29/17.
 */

public class RecipeList {
    @SerializedName("ingredients")
    @Expose
    private ArrayList<Ingredients> ingredients;
    @SerializedName("recipes")
    @Expose
    private ArrayList<Recipes> recipes;
    @SerializedName("steps")
    @Expose
    private ArrayList<Steps> steps;

    public ArrayList<Ingredients> getIngredients(){
        return ingredients;
    }
    public ArrayList<Recipes> getRecipes(){
        return recipes;
    }

    public ArrayList<Steps>getSteps(){
        return steps;
    }

    public void setIngredients(ArrayList<Ingredients> ingredients){
        this.ingredients = ingredients;
    }
    public void setRecipes(ArrayList<Recipes> recipes){
        this.recipes = recipes;
    }
    public void setSteps(ArrayList<Steps> steps){
        this.steps = steps;
    }
}
