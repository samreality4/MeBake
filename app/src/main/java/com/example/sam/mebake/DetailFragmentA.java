package com.example.sam.mebake;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.sam.mebake.Model.Recipes;
import com.example.sam.mebake.Model.Steps;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sam on 12/16/17.
 */

public class DetailFragmentA extends Fragment{
    RecyclerView recyclerView;
    ArrayList<Steps>stepsList;
    StepAdapter stepAdapter;

    public DetailFragmentA() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View rootView = inflater.inflate(R.layout.detail_page_a, container, false);

        Button readIngredients = rootView.findViewById(R.id.ingredients_button);
        readIngredients.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                //calling activity click method from Fragment
                ((RecipeDetail)getActivity()).onIngredientClick();
            }

        });

        Bundle bundle = getArguments();
        if (bundle != null) {
            stepsList= bundle.getParcelableArrayList("steps");
        }


        stepAdapter = new StepAdapter(getActivity(), stepsList);
        recyclerView = rootView.findViewById(R.id.Recyclerview_steps);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(stepAdapter);




        return rootView;
    }

}
//todo get all the fragments ready
//todo fragment needs adapters?