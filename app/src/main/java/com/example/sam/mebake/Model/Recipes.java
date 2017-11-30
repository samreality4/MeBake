package com.example.sam.mebake.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sam on 11/29/17.
 */

public class Recipes {
    private Ingredients[] ingredients;
    @SerializedName("id")
    private String id;
    @SerializedName("servings")
    private String servings;
    @SerializedName("name")
    private String name;
    @SerializedName("image")
    private String image;


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


}

