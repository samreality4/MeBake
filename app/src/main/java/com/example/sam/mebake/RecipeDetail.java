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
import java.util.List;

/**
 * Created by sam on 12/15/17.
 */


//Communication between Fragments should be done via host Activity in ex. Listeners
public class RecipeDetail extends AppCompatActivity implements StepAdapter.stepClickListener, DetailFragmentB.StepButtonClickListener{
    private ArrayList<Recipes> recipesList;
    ArrayList<Steps> stepsList;
    ArrayList<Ingredients> ingredientsList;
    StepAdapter stepAdapter;
    RecyclerView recyclerView;
    static String Back_Stack_Step = "Back_Stack_Step";


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
        if((findViewById(R.id.detail_fragment_a) !=null) &&
                (findViewById(R.id.detail_fragment_b) == null)) {

            DetailFragmentIngredient detailFragmentIngredient = new DetailFragmentIngredient();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.detail_fragment_a, detailFragmentIngredient)
                    .commit();
            Bundle ingredientsBundle = new Bundle();
            ingredientsBundle.putParcelableArrayList("ingredients", ingredientsList);

            detailFragmentIngredient.setArguments(ingredientsBundle);

        }else if(findViewById(R.id.detail_fragment_b) != null) {

            DetailFragmentIngredient detailFragmentIngredient = new DetailFragmentIngredient();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.detail_fragment_b, detailFragmentIngredient)
                    .commit();
            Bundle ingredientsBundle = new Bundle();
            ingredientsBundle.putParcelableArrayList("ingredients", ingredientsList);

            detailFragmentIngredient.setArguments(ingredientsBundle);

        }
    }

    @Override
    public void onStepClickListener(View v, int position) {
        if((findViewById(R.id.detail_fragment_a) !=null) &&
                (findViewById(R.id.detail_fragment_b) == null)){
        DetailFragmentB detailFragmentB = new DetailFragmentB();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.detail_fragment_a, detailFragmentB)
                    .commit();
            Bundle bundle = new Bundle();
            bundle.putInt("position", position);
            bundle.putParcelable("stepsdetail", stepsList.get(position));
            detailFragmentB.setArguments(bundle);
        }else if (findViewById(R.id.detail_fragment_b) != null) {
           DetailFragmentB detailFragmentB = new DetailFragmentB();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.detail_fragment_b, detailFragmentB)
                    .commit();

            Bundle bundle = new Bundle();
            bundle.putInt("position", position);
            //stepsList.get(position) is to get the all the items from that position.
            bundle.putParcelable("stepsdetail", stepsList.get(position));
            detailFragmentB.setArguments(bundle);}

    }




    @Override
    public void onStepButtonClickListener(List <Steps> steps, int position) {
            if(findViewById(R.id.detail_fragment_a)!=null && findViewById(R.id.detail_fragment_b) == null) {

                DetailFragmentB detailFragmentB = new DetailFragmentB();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.detail_fragment_a, detailFragmentB)
                        .commit();
                // has to use same STRING name, so it can receive the new data.
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("steplist", stepsList);
                bundle.putInt("position", position);
                bundle.putParcelable("stepsdetail", stepsList.get(position));
                detailFragmentB.setArguments(bundle);
                //never leave ! = , alway use !=
            }else if(findViewById(R.id.detail_fragment_b)!= null && findViewById(R.id.detail_fragment_a) != null){
                DetailFragmentB detailFragmentB = new DetailFragmentB();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.detail_fragment_b, detailFragmentB).addToBackStack(Back_Stack_Step)
                        .commit();

                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("steplist", stepsList);
                bundle.putInt("position", position);
                bundle.putParcelable("stepsdetail", stepsList.get(position));
                detailFragmentB.setArguments(bundle);
            }

            /*bundle = new Bundle();
        bundle.putParcelableArrayList("steplist",stepsList);
        bundle.putInt("position", position);
            bundle.putParcelable("stepsdetail", stepsList.get(position));
            detailFragmentB.setArguments(bundle);*/
        }
        }






