package com.example.sam.mebake;


import android.content.Intent;
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

    @Override
    public void onRecipeClickListener(View v, int position) {
        //Bundle bundle = new Bundle();
        //add the position to the arraylist (wow)
        Recipes recipes = recipeList.get(position);
        //bundle.putParcelableArrayList("recipes", recipeList);
        //bundle.putParcelableArrayList("steps", stepList);
        final Intent intent = new Intent(this, RecipeDetail.class);
        intent.putExtra("recipes", recipes);
        startActivity(intent);
    }
}

