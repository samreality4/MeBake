package com.example.sam.mebake;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sam.mebake.Model.Ingredients;
import com.example.sam.mebake.Model.Recipes;
import com.example.sam.mebake.Model.Steps;

import java.util.ArrayList;

/**
 * Created by sam on 12/26/17.
 */

public class IngredientAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context mContext;
    private LayoutInflater inflater;
    private ArrayList<Ingredients> ingredientLists;


    /*public interface ingredientClickListener{
        void onIngredientClickListener(View v, int position);}*/


    public IngredientAdapter(Context context, ArrayList<Ingredients> list) {
        this.mContext = context;
        //this.listener = (ingredientClickListener) context;
        this.ingredientLists = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.detail_page_ingredients, parent, false);
        /*view.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(listener !=null){
                    listener.onIngredientClickListener((v, myholder.getAdapterPosition());
                }
            }
        });*/

        return new Myholder(view);



    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        IngredientAdapter.Myholder myholder = (IngredientAdapter.Myholder) holder;
        Ingredients ingredients = ingredientLists.get(position);
        myholder.ingredientsInfo.setText(ingredients.getQuantity() + " " + ingredients.getMeasure()
        + " of " + ingredients.getIngredient());
    }

    @Override
    public int getItemCount() {
        return ingredientLists.size();
    }


    class Myholder extends RecyclerView.ViewHolder{
        TextView ingredientsInfo;

        public Myholder(View itemView){
            super(itemView);
            ingredientsInfo = itemView.findViewById(R.id.ingredients_info);
        }
    }

}
