package com.example.sam.mebake;


import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.sam.mebake.Model.Recipes;
import com.example.sam.mebake.Model.Steps;
import com.example.sam.mebake.remote.ApiClient;
import com.example.sam.mebake.remote.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sam on 12/15/17.
 */

public class RecipeDetail extends AppCompatActivity {
    private ArrayList<Recipes> recipesArrayList;
   ArrayList<Steps> stepsList;
    StepAdapter stepAdapter;
    RecyclerView recyclerView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tablet_layout);


        if (savedInstanceState == null) {

            Intent intent = getIntent();

            final Recipes recipes = intent.getParcelableExtra("recipes" );
            stepsList = recipes.getStepses();

            //Bundle bundle = getIntent().getExtras();
            //recipesArrayList = bundle.getParcelableArrayList("recipes");
            //stepsList = bundle.getParcelableArrayList("recipes");


            DetailFragmentA detailFragmentA = new DetailFragmentA();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.detail_fragment_a, detailFragmentA)
                    .commit();


            Bundle stepBundle = new Bundle();
            stepBundle.putParcelableArrayList("steps", stepsList);

            detailFragmentA.setArguments(stepBundle);


            //DetailFragmentB detailFragmentB = new DetailFragmentB();


            /*getSupportFragmentManager().beginTransaction()
                    .add(R.id.detail_fragment_b, detailFragmentB)
                    .commit();
                    */




        }

    }

}