package com.example.sam.mebake.widget;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.sam.mebake.MainActivity;
import com.example.sam.mebake.Model.Ingredients;
import com.example.sam.mebake.Model.Recipes;
import com.example.sam.mebake.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sam on 1/5/18.
 */

public class NewAppRemoteViewsService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new NewAppRemoteViewsFactory(this.getApplicationContext());
    }

    class NewAppRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

        private static final String PREF_NAME = "prefname";
        private static final String RECIPES_PREF_KEY = "prefkey";
        private static final String RECIPES_ID_PREF = "recipeid";


        private Context context;
        private List<Ingredients> ingredientsList;


        public NewAppRemoteViewsFactory(Context context) {
            this.context = context;
        }

        @Override
        public void onCreate() {

        }

        @Override
        public void onDataSetChanged() {
            //setting up sharedpreference in.
            SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE);
            //saving things in sharedpreference
            //int recipeId = preferences.getInt(RECIPES_ID_PREF, 0);
            String json = preferences.getString(RECIPES_PREF_KEY, null);
            Type type = new TypeToken<List<Ingredients>>() {
            }.getType();
            Gson gson = new Gson();
            List<Ingredients> ingredients = gson.fromJson(json, type);
            {
                ingredientsList = ingredients;
            }
        }
            /*editor.putString(String.valueOf(ingredientsArrayList), json);
            editor = preferences.edit();
            editor.commit();*/


        @Override
        public void onDestroy() {

        }

        @Override
        public int getCount() {
            if (ingredientsList != null) {
                //how big is this list?
                return ingredientsList.size();
            } else return 0;
        }

        @Override
        public RemoteViews getViewAt(int position) {
            // this works like a viewholder in the adapter for recyclerview
            RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.new_app_widget_ingredients);
            rv.setTextViewText(R.id.tv_ingredient_measure, String.valueOf(ingredientsList.get(position).getMeasure()));
            rv.setTextViewText(R.id.tv_ingredient_quantity, String.valueOf(ingredientsList.get(position).getQuantity()));
            rv.setTextViewText(R.id.tv_ingredient_description, ingredientsList.get(position).getIngredient());
            return rv;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 0;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }


    }

}

