package com.example.sam.mebake;


import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;


import com.example.sam.mebake.Model.Ingredients;
import com.example.sam.mebake.Model.Recipes;
import com.example.sam.mebake.Model.Steps;
import com.example.sam.mebake.remote.ApiClient;
import com.example.sam.mebake.remote.ApiService;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements RecipeAdapter.recipeClickListener {
    public ArrayList<Recipes> recipeList;
    public ArrayList<Ingredients> ingredientsList;
    public ArrayList<Steps> stepList;
    private RecyclerView recyclerView;
    RecipeAdapter recipeAdapter;
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String PREF_NAME = "prefname";
    private static final String RECIPES_PREF_KEY ="prefkey";
    private static final String RECIPES_NAME_PREF ="recipename";
    private static final String RECIPES_SIZE_PREF="recipesize";
    private static final String WIDGET_UPDATE_ACTION = "android.appwidget.action.APPWIDGET_UPDATE";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_recyclerview);


        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<ArrayList<Recipes>> call = apiService.getEverything();
        call.enqueue(new Callback<ArrayList<Recipes>>() {
            @Override
            public void onResponse(Call<ArrayList<Recipes>> call, Response<ArrayList<Recipes>> response) {
                Log.d("MainActivity", "Status Code = " + response.code());
                recipeList = response.body();


                recipeAdapter = new RecipeAdapter(MainActivity.this, recipeList);
                recyclerView = findViewById(R.id.main_recyclerview);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
                recyclerView.setAdapter(recipeAdapter);

            }


            @Override
            public void onFailure(Call<ArrayList<Recipes>> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, toString());

            }
        });

    }

    private void saveToSharedPreference(List<Ingredients> ingredients, String name){
        //setting up sharedpreferences with name & mode
        SharedPreferences preferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        //setting up the editor to edit sharepreference
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
        Gson gson = new Gson();
        //converting ingredient list to json
        String newJsonData = gson.toJson(ingredients);
        String jsonRecipeName= gson.toJson(name);
        editor.putString(RECIPES_PREF_KEY, newJsonData);
        editor.putString(RECIPES_NAME_PREF, jsonRecipeName);
        editor.putInt(RECIPES_SIZE_PREF, ingredients.size());
        editor.apply();

    }

    @Override
    public void onRecipeClickListener(View v, int position) {
        //Bundle bundle = new Bundle();
        //add the position to the arraylist (wow)
        Recipes recipes = recipeList.get(position);
        //put the ingreident into the ingredient list
        String name = recipes.getName();
        List<Ingredients> ingredients = recipes.getIngredientses();
        final Intent intent = new Intent(this, RecipeDetail.class);
        intent.putExtra("recipes", recipes);
        startActivity(intent);
        //save the CURRENT(onclick) to sharedpreference
        saveToSharedPreference(ingredients, name);
    }
}

