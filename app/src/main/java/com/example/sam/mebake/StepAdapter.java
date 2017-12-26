package com.example.sam.mebake;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sam.mebake.Model.Recipes;
import com.example.sam.mebake.Model.Steps;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sam on 12/17/17.
 */

public class StepAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context mContext;
    private LayoutInflater inflater;
    private ArrayList<Recipes> recipesList;
    private ArrayList<Steps> stepLists;
    public stepClickListener listener;



    public interface stepClickListener{
        void onStepClickListener(View v, int position);}


    public StepAdapter(Context context, ArrayList<Steps> list) {
        this.mContext = context;
        //this.listener = (stepClickListener) context;
        this.stepLists = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.detail_page_a, parent, false);
        final Myholder myholder = new Myholder(view);
        view.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(listener !=null){
                    listener.onStepClickListener(v, myholder.getAdapterPosition());
                }
            }
        });

        return myholder;



    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Myholder myholder = (StepAdapter.Myholder) holder;
        Steps steps = stepLists.get(position);
        myholder.shortDescription.setText(steps.getShortDescription());
        //todo take out the recipe data in stepLists
    }

    @Override
    public int getItemCount() {
        return stepLists.size();
    }


    class Myholder extends RecyclerView.ViewHolder{
        TextView shortDescription;

        public Myholder(View itemView){
            super(itemView);
            shortDescription = itemView.findViewById(R.id.recipe_name);
        }
    }

}

