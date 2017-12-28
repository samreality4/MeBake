package com.example.sam.mebake;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
IngredientAdapter ingredientAdapter;
RecyclerView recyclerView;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.ingredients_recyclerview, container, false);

        Bundle bundle = getArguments();
        if (bundle != null) {
            ingredientsList = bundle.getParcelableArrayList("ingredients");
        }

        ingredientAdapter = new IngredientAdapter(getContext(), ingredientsList);
        recyclerView = rootView.findViewById(R.id.recyclerView_ingredients);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(ingredientAdapter);

        return rootView;

        }
    }