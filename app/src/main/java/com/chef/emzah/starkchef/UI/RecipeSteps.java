package com.chef.emzah.starkchef.UI;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.chef.emzah.starkchef.ModalClasses.Ingredient;
import com.chef.emzah.starkchef.ModalClasses.Recipe;
import com.chef.emzah.starkchef.R;
import com.chef.emzah.starkchef.UI.Fragments.IngredietFragment;
import com.chef.emzah.starkchef.UI.Fragments.RecipeDetailsFragment;

import java.util.List;

public class RecipeSteps extends AppCompatActivity {
List<Recipe> ingredientList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_steps);
        RecipeDetailsFragment fragment=new RecipeDetailsFragment();
        FragmentManager fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.container,fragment)
                .commit();




    }
}
