package com.chef.emzah.starkchef.UI;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.chef.emzah.starkchef.ModalClasses.Ingredient;
import com.chef.emzah.starkchef.ModalClasses.Recipe;
import com.chef.emzah.starkchef.ModalClasses.Step;
import com.chef.emzah.starkchef.R;
import com.chef.emzah.starkchef.UI.Fragments.IngredietFragment;
import com.chef.emzah.starkchef.UI.Fragments.RecipeDetailsFragment;
import com.chef.emzah.starkchef.UI.Fragments.StepsFragment;

import java.util.List;

public class RecipeSteps extends AppCompatActivity {
List<Recipe> ingredientList;
    List<Step> stepList;
    private int CurrentPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_steps);

        stepList=getIntent().getParcelableArrayListExtra("stepsList");

        Intent intent=getIntent();
        int positionB=intent.getIntExtra("position",0);

        Bundle bundle=new Bundle();
        bundle.putInt("position",positionB);

        RecipeDetailsFragment fragment=new RecipeDetailsFragment();
        fragment.setArguments(bundle);
        FragmentManager fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.container,fragment)
                .commit();




    }
}
