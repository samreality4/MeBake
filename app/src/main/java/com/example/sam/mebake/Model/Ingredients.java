package com.example.sam.mebake.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.sam.mebake.Model.Recipes;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sam on 11/29/17.
 */

public class Ingredients implements Parcelable{


    @SerializedName("measure")
    private String measure;
    @SerializedName("ingredient")
    private String ingredient;
    @SerializedName("quantity")
    private double quantity;

    public static final Creator<Ingredients> CREATOR = new Creator<Ingredients>() {
        @Override
        public Ingredients createFromParcel(Parcel in) {
            return new Ingredients(in);
        }

        @Override
        public Ingredients[] newArray(int size) {
            return new Ingredients[size];
        }
    };

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

    public double getQuantity ()
    {
        return quantity;
    }

    public void setQuantity (double quantity)
    {
        this.quantity = quantity;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(measure);
        parcel.writeString(ingredient);
        parcel.writeDouble(quantity);

    }

    private Ingredients (Parcel in){
        measure = in.readString();
        ingredient = in.readString();
        quantity = in.readDouble();
    }
}
