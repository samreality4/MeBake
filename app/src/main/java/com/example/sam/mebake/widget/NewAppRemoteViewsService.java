package com.example.sam.mebake.widget;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.sam.mebake.MainActivity;
import com.example.sam.mebake.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

/**
 * Created by sam on 1/5/18.
 */

public class NewAppRemoteViewsService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new NewAppRemoteViewsFactory(this.getApplicationContext());
    }

    class NewAppRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory{

        private Context context;
        private ArrayList<NewAppWidgetObject> myObjects;

        public NewAppRemoteViewsFactory(Context context) {
            this.context = context;

            myObjects = new ArrayList<>();

        }

        @Override
        public void onCreate() {

        }

        @Override
        public void onDataSetChanged() {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            String json = preferences.getString(MainActivity.SHARED_PREFS_KEY, "");
            if(!json.equals("")){
                Gson gson = new Gson();
                myObjects = gson.fromJson(json, new TypeToken<ArrayList<NewAppWidgetObject>>(){
            }.getType();
            }
        }

        @Override
        public void onDestroy() {

        }

        @Override
        public int getCount() {
            if(myObjects != null){
                return myObjects.size();
            }else return 0;
        }

        @Override
        public RemoteViews getViewAt(int position) {
            RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
            rv.setTextViewText(R.id.widget_listview, myObjects.get(position).getWidgetString());
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
            return 0;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }


    }


}
