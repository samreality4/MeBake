package com.example.sam.mebake.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sam on 11/29/17.
 */

public class Ingredients {
    @SerializedName("measure")
    private String measure;
    @SerializedName("ingredient")
    private String ingredient;
    @SerializedName("quantity")
    private String quantity;

    public String getMeasure ()
    {
        return measure;
    }

    public void setMeasure (String measure)
    {
        this.measure = measure;
    }

    public String getIngredient ()
    {
        return ingredient;
    }

    public void setIngredient (String ingredient)
    {
        this.ingredient = ingredient;
    }

    public String getQuantity ()
    {
        return quantity;
    }

    public void setQuantity (String quantity)
    {
        this.quantity = quantity;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [measure = "+measure+", ingredient = "+ingredient+", quantity = "+quantity+"]";
    }
}
