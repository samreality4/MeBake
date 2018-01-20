package com.example.sam.mebake;


import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

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
    Context context = this;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_detail);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE &
                (findViewById(R.id.detail_fragment_b) == null)) {
            getSupportActionBar().hide();
        }


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


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {

        switch (item.getItemId()) {
            //android default go back home button (can be access through getActionbar())
            case android.R.id.home:
                onBackPressed();
                Toast toast = Toast.makeText(context, "Just going back", Toast.LENGTH_SHORT);
                toast.show();
                return true;
            case R.id.gohome:
                NavUtils.navigateUpFromSameTask(this);

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    //modified the backpressed to pop the fragments
    @Override
    public void onBackPressed(){
        if(getFragmentManager().getBackStackEntryCount() > 0){
            getFragmentManager().popBackStack();
        }
        else{
            super.onBackPressed();
        }
    }

    public void onIngredientClick(){
        if((findViewById(R.id.detail_fragment_a) !=null) &&
                (findViewById(R.id.detail_fragment_b) == null)) {

            DetailFragmentIngredient detailFragmentIngredient = new DetailFragmentIngredient();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.detail_fragment_a, detailFragmentIngredient)
                    .addToBackStack(Back_Stack_Step)
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
                    .addToBackStack(Back_Stack_Step)
                    .commit();
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("steplist", stepsList);
            bundle.putInt("position", position);
            bundle.putParcelable("stepsdetail", stepsList.get(position));
            detailFragmentB.setArguments(bundle);
        }else if (findViewById(R.id.detail_fragment_b) != null) {
           DetailFragmentB detailFragmentB = new DetailFragmentB();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.detail_fragment_b, detailFragmentB)
                    .commit();

            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("steplist", stepsList);
            bundle.putInt("position", position);
            //stepsList.get(position) is to get the all the items from that position.
            bundle.putParcelable("stepsdetail", stepsList.get(position));
            detailFragmentB.setArguments(bundle);}

    }




    @Override
    public void onStepButtonClickListener(List <Steps> steps, int position) {
            if(findViewById(R.id.detail_fragment_a)!=null && findViewById(R.id.detail_fragment_b) == null ) {

                DetailFragmentB detailFragmentB = new DetailFragmentB();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.detail_fragment_a, detailFragmentB)
                        .addToBackStack(Back_Stack_Step)
                        .commit();
                // has to use same STRING name, so it can receive the new data.
                Bundle bundle = new Bundle();
                //pass in the entire list so, it can take data from the list.
                bundle.putParcelableArrayList("steplist", stepsList);
                bundle.putInt("position", position);
                bundle.putParcelable("stepsdetail", stepsList.get(position));
                detailFragmentB.setArguments(bundle);
                //never leave ! = , alway use !=
            }else if(findViewById(R.id.detail_fragment_b)!= null && findViewById(R.id.detail_fragment_a) != null ){
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

        }
        }

//todo position is lost onResume;




