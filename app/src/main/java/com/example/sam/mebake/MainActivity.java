package com.example.sam.mebake;

import android.support.v7.app.AlertController;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.sam.mebake.Model.RecipeList;
import com.example.sam.mebake.remote.ApiClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

import static android.os.Build.ID;

public class MainActivity extends AppCompatActivity {
    public ArrayList<RecipeList> RecipeList;
    private RecycleView



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_recyclerview);
    }

    APIService apiService = ApiClient.getClient().create(APIService.class);
    Call<RecipeList> call = apiService.getEverything();
        call.enqueue(new Callback<RecipeList>() {
        @Override
        public void onResponse(Call<RecipeList> call, Response<RecipeList> response) {
            com.example.sam.mebake.Model.RecipeList = response.body().getResults();
            Log.i("list", response.body().toString());


            reviewsAdapter = new ReviewsAdapter(ReviewActivity.this, reviewsList);
            recyclerView = (RecyclerView) findViewById(R.id.reviews_reyclerview);
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
            recyclerView.setAdapter(reviewsAdapter);

        }


        @Override
        public void onFailure(Call<ReviewsResults> call, Throwable t) {
            // Log error here since request failed
            Log.e(TAG, t.toString());
        }
    });


}
}
