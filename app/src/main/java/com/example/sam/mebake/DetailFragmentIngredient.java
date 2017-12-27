package com.example.sam.mebake;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sam.mebake.Model.Ingredients;

import java.util.ArrayList;

/**
 * Created by sam on 12/22/17.
 */

public class DetailFragmentIngredient extends android.support.v4.app.Fragment{

ArrayList<Ingredients> ingredientsList;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.detail_page_ingredients, container, false);

        Bundle bundle = getArguments();
        if (bundle != null) {
            ingredientsList = bundle.getParcelableArrayList("ingredients");
        }

        return rootView;

        }
    }