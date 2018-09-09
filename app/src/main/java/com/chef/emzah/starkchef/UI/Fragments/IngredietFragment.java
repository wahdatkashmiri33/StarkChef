package com.chef.emzah.starkchef.UI.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chef.emzah.starkchef.Adapters.RecipeIngredientAdapter;
import com.chef.emzah.starkchef.ModalClasses.Ingredient;
import com.chef.emzah.starkchef.ModalClasses.Recipe;
import com.chef.emzah.starkchef.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IngredietFragment extends Fragment {
    @BindView(R.id.ingredientsrecyclerview) RecyclerView recyclerView;

    RecipeIngredientAdapter adapter;
    public List<Ingredient> ingredientList;
    public IngredietFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.ingredients_fragment,container,false);


        ButterKnife.bind(this,view);
        final LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setHasFixedSize(true);
        adapter=new RecipeIngredientAdapter(ingredientList,getContext());

        if (adapter.getItemCount()==0){
            Toast.makeText(getContext(), "kuch nhi ha adapter ma", Toast.LENGTH_SHORT).show();
        }
        else {
            recyclerView.setAdapter(adapter);
            recyclerView.getAdapter().notifyDataSetChanged();
        }


       return view;

    }
}
