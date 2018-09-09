package com.chef.emzah.starkchef.UI;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.chef.emzah.starkchef.R;
import com.chef.emzah.starkchef.UI.Fragments.IngredietFragment;
import com.chef.emzah.starkchef.UI.Fragments.RecipeDetailsFragment;

public class RecipeSteps extends AppCompatActivity {

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
