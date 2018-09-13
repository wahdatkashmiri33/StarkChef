package com.chef.emzah.starkchef.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chef.emzah.starkchef.ModalClasses.Ingredient;
import com.chef.emzah.starkchef.ModalClasses.Recipe;
import com.chef.emzah.starkchef.R;
import com.chef.emzah.starkchef.UI.RecipeSteps;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
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
    public void onBindViewHolder(@NonNull final RecipeViewHolder holder, final int position) {
         final Recipe recipe= recipies.get(position);

         holder.recipename.setText(recipe.getName());
         holder.servings.setText(String.format(Locale.US,"%d",recipe.getServings()));
         holder.steps.setText(String.format(Locale.US,"%d",recipe.getSteps().size()));
         holder.cardView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
             Recipe recipe1=new Recipe();
                 Intent intent=new Intent(context, RecipeSteps.class);
                 intent.putExtra("recipeData",recipies.get(holder.getAdapterPosition()));
                 intent.putParcelableArrayListExtra("ingredientsList", new ArrayList<Parcelable>(recipies.get(holder.getAdapterPosition()).getIngredients()));
                 intent.putParcelableArrayListExtra("stepsList",new ArrayList<Parcelable>(recipies.get(holder.getAdapterPosition()).getSteps()));
                 intent.putExtra("position",recipies.get(position));
                 intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                 context.startActivity(intent);
             }
         });



    }

    @Override
    public int getItemCount() {
       if (recipies==null){
           return 0;
       }
       return recipies.size();
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder{


        @BindView(R.id.cardlayout) CardView cardView;
        @BindView(R.id.servings) TextView servings;
        @BindView(R.id.steps) TextView steps;
        @BindView(R.id.recipe_name) TextView recipename;
        public RecipeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
