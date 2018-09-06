package com.chef.emzah.starkchef.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chef.emzah.starkchef.ModalClasses.Recipe;
import com.chef.emzah.starkchef.R;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>{

    private List<Recipe> recipies;
    private Context context;

    public RecipeAdapter(List<Recipe> recipies, Context context) {
        this.recipies = recipies;
        this.context = context;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_list,parent,false);
        return new RecipeViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
         Recipe recipe= recipies.get(position);
         holder.recipename.setText(recipe.getName());
         holder.servings.setText(String.format(Locale.US,"%d",recipe.getServings()));
         holder.steps.setText(String.format(Locale.US,"%d",recipe.getSteps().size()));


    }

    @Override
    public int getItemCount() {
       if (recipies==null){
           return 0;
       }
       return recipies.size();
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder{


        @BindView(R.id.servings) TextView servings;
        @BindView(R.id.steps) TextView steps;
        @BindView(R.id.recipe_name)  TextView recipename;
        public RecipeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
