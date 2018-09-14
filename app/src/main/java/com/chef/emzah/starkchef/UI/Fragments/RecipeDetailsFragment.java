package com.chef.emzah.starkchef.UI.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.chef.emzah.starkchef.Adapters.RecipeAdapter;
import com.chef.emzah.starkchef.ModalClasses.Recipe;
import com.chef.emzah.starkchef.ModalClasses.Step;
import com.chef.emzah.starkchef.R;
import com.chef.emzah.starkchef.UI.RecipeSteps;
import com.chef.emzah.starkchef.UI.Steps;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeDetailsFragment extends Fragment {



    @BindView(R.id.cardingredients) CardView cardViewIngredients;
    @BindView(R.id.recipe_name) TextView recipeName;
    public RecipeDetailsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.details_activity_fragment,container,false);
        Recipe recipe=getActivity().getIntent().getParcelableExtra("recipeData");


        ButterKnife.bind(this,rootView);
        recipeName.setText(recipe.getName());
        cardViewIngredients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IngredietFragment ingredietFragment=new IngredietFragment();
                FragmentManager fragmentManager1=getActivity().getSupportFragmentManager();
                fragmentManager1.beginTransaction().replace(R.id.container,ingredietFragment)
                        .addToBackStack(null) .commit();
                Toast.makeText(getContext(), "this is detail fragment", Toast.LENGTH_SHORT).show();
            }
        });


        return rootView;
    }
}
