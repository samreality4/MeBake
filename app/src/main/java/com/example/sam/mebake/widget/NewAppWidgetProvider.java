package com.example.sam.mebake.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;

import com.example.sam.mebake.Model.Ingredients;
import com.example.sam.mebake.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Implementation of App Widget functionality.
 */
public class NewAppWidgetProvider extends AppWidgetProvider {

    //string names to pick up data from json
    private static final String PREF_NAME = "prefname";
    private static final String RECIPES_PREF_KEY ="prefkey";
    private static final String RECIPES_ID_PREF ="recipeid";
    private static final String RECIPES_SIZE_PREF="recipesize";
    private static final String WIDGET_UPDATE_ACTION = "android.appwidget.action.APPWIDGET_UPDATE";
    private String recipeName;


    public void onReceive(Context context, Intent intent) {
        //receive the update from intent
        super.onReceive(context, intent);
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context.getApplicationContext());
        ComponentName thisWidget = new ComponentName(context.getApplicationContext(), NewAppWidgetProvider.class);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
        //updateRecipeId(context);
        onUpdate(context, appWidgetManager, appWidgetIds);

    }

    /*private void updateRecipeId(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //getting the recipe id to findout which recipe to pick up
        int recipeId = sharedPreferences.getInt(RECIPES_ID_PREF, 0);

        int recipeSize = sharedPreferences.getInt(RECIPES_SIZE_PREF, 1);

        if(recipeId < recipeSize - 1){
            recipeId++;
        }else{
            recipeId = 0;
        }
        editor.putString(RECIPES_PREF_KEY, recipeId);
        editor.apply();

    }*/

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them (if one ID, or IDs)
        for(int appWidgetId : appWidgetIds){
            //load the updateAppWidget below
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }


    }

    public void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId){
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);

        Intent intent = new Intent(context, NewAppRemoteViewsService.class);
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        //saving things in sharedpreference
        String json1 = preferences.getString(RECIPES_ID_PREF, null);
        String name = json1;{
            recipeName = name;
        }
        //has to put the name string in here because remoteviewfactory is like an
        // adapter it takes multiple things instead of one
        views.setTextViewText(R.id.title_text, recipeName);
        views.setRemoteAdapter(R.id.widget_listview, intent);

        views.setEmptyView(R.id.widget_listview, R.id.empty_view);

        //tells widget manager to update
        appWidgetManager.updateAppWidget(appWidgetId, views);
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, R.id.widget_listview);

    }


    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

//todo send the receive the intent and then somebody should send the intent(example like in our recyclerview)