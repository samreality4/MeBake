package com.example.sam.mebake;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.sam.mebake.Model.Ingredients;
import com.example.sam.mebake.Model.Recipes;
import com.example.sam.mebake.Model.Steps;

import java.util.ArrayList;

/**
 * Created by sam on 12/15/17.
 */


//Communication between Fragments should be done via host Activity in ex. Listeners
public class RecipeDetail extends AppCompatActivity implements StepAdapter.stepClickListener{
    private ArrayList<Recipes> recipesList;
    ArrayList<Steps> stepsList;
    ArrayList<Ingredients> ingredientsList;
    StepAdapter stepAdapter;
    RecyclerView recyclerView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_detail);


        if (savedInstanceState == null) {

            Intent intent = getIntent();

            final Recipes recipes = intent.getParcelableExtra("recipes" );
            stepsList = recipes.getStepses();
            ingredientsList = recipes.getIngredientses();


            DetailFragmentA detailFragmentA = new DetailFragmentA();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.detail_fragment_a, detailFragmentA)
                    .commit();


            Bundle stepBundle = new Bundle();
            stepBundle.putParcelableArrayList("steps", stepsList);

            detailFragmentA.setArguments(stepBundle);



            if(findViewById(R.id.detail_fragment_b) != null) {


                final DetailFragmentIngredient detailFragmentIngredient = new DetailFragmentIngredient();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.detail_fragment_b, detailFragmentIngredient)
                        .commit();
                Bundle ingredientsBundle = new Bundle();
                ingredientsBundle.putParcelableArrayList("ingredients", ingredientsList);

                detailFragmentIngredient.setArguments(ingredientsBundle);

            }


        }

    }

    public void onIngredientClick(){

        DetailFragmentIngredient detailFragmentIngredient = new DetailFragmentIngredient();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.detail_fragment_a, detailFragmentIngredient)
                .commit();
        Bundle ingredientsBundle = new Bundle();
        ingredientsBundle.putParcelableArrayList("ingredients", ingredientsList);

        detailFragmentIngredient.setArguments(ingredientsBundle);

        if(findViewById(R.id.detail_fragment_b) != null) {

            detailFragmentIngredient = new DetailFragmentIngredient();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.detail_fragment_b, detailFragmentIngredient)
                    .commit();
            ingredientsBundle = new Bundle();
            ingredientsBundle.putParcelableArrayList("ingredients", ingredientsList);

            detailFragmentIngredient.setArguments(ingredientsBundle);

        }
    }

    @Override
    public void onStepClickListener(View v, int position) {
        DetailFragmentB detailFragmentB = new DetailFragmentB();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.detail_fragment_a,detailFragmentB)
                .commit();
        Bundle bundle = new Bundle();
        bundle.putParcelable("stepsdetail", stepsList.get(position));
        detailFragmentB.setArguments(bundle);

        if(findViewById(R.id.detail_fragment_b) != null) {
            detailFragmentB = new DetailFragmentB();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.detail_fragment_b, detailFragmentB)
                    .commit();

            bundle = new Bundle();
        bundle.putParcelable("stepsdetail", stepsList.get(position));
        detailFragmentB.setArguments(bundle);}

    }

    /*public void onStepListClick() {

        DetailFragmentB detailFragmentB = new DetailFragmentB();


            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.detail_fragment_b, detailFragmentB)
                    .commit();*/
    }

