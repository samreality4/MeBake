package com.example.sam.mebake.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by sam on 12/2/17.
 */

public class Recipes implements Parcelable{
    @SerializedName("id")
    private String id;
    @SerializedName("servings")
    private String servings;
    @SerializedName("name")
    private String name;
    @SerializedName("image")
    private String image;
    @SerializedName("ingredients")
    private ArrayList<Ingredients> ingredientses = new ArrayList<>();
    @SerializedName("steps")
    private ArrayList<Steps> stepses = new ArrayList<>();


    public ArrayList<Recipes> recipes = new ArrayList<>();


    public static final Creator<Recipes> CREATOR = new Creator<Recipes>() {
        @Override
        public Recipes createFromParcel(Parcel in) {
            return new Recipes(in);
        }

        @Override
        public Recipes[] newArray(int size) {
            return new Recipes[size];
        }
    };


    public ArrayList<Steps> getStepses(){
        return stepses;
    }


    public ArrayList<Ingredients> getIngredientses(){
        return ingredientses;
    }
    public ArrayList<Recipes> getRecipes(){
        return recipes;
    }


    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getServings ()
    {
        return servings;
    }

    public void setServings (String servings)
    {
        this.servings = servings;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getImage ()
    {
        return image;
    }

    public void setImage (String image)
    {
        this.image = image;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(recipes);
        parcel.writeString(id);
        parcel.writeString(servings);
        parcel.writeString(name);
        parcel.writeString(image);
        parcel.writeList(stepses);
        parcel.writeList(ingredientses);

    }
    private Recipes (Parcel in){
        in.readList(recipes, getClass().getClassLoader());
        id= in.readString();
        servings= in.readString();
        name=in.readString();
        image=in.readString();
        in.readList(ingredientses, getClass().getClassLoader());
        in.readList(stepses, getClass().getClassLoader());

    }

}
