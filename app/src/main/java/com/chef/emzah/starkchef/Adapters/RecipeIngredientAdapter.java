package com.chef.emzah.starkchef.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chef.emzah.starkchef.ModalClasses.Ingredient;
import com.chef.emzah.starkchef.R;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeIngredientAdapter extends RecyclerView.Adapter<RecipeIngredientAdapter.RecipeIngredientViewholder>{

    private List<Ingredient> ingredientList;
    private Context context;

    public RecipeIngredientAdapter(List<Ingredient> ingredientList, Context context) {
        this.ingredientList = ingredientList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecipeIngredientViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredients_list_item,parent,false);
        return new RecipeIngredientViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeIngredientViewholder holder, int position) {
       Ingredient ingredient=ingredientList.get(position);
       float qty=ingredient.getQuantity();
       holder.qtyMeasuretxtView.setText(String.valueOf(qty)+" "+ingredient.getMeasure());

       holder.ingredientDetailTextview.setText(ingredient.getIngredient());
    }

    @Override
    public int getItemCount() {
        if (ingredientList==null){
            return 0;
        }
        else {
            return ingredientList.size();
        }

    }
    public void setIngredients(List<Ingredient> ingredientList) {
        this.ingredientList=ingredientList;
        notifyDataSetChanged();
    }
    public class RecipeIngredientViewholder extends RecyclerView.ViewHolder{


        @BindView(R.id.qtymeasure) TextView qtyMeasuretxtView;
        @BindView(R.id.ingredientdetail) TextView ingredientDetailTextview;
        public RecipeIngredientViewholder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
