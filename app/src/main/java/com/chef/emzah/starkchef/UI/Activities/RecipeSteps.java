package com.chef.emzah.starkchef.UI.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.chef.emzah.starkchef.ModalClasses.Recipe;
import com.chef.emzah.starkchef.ModalClasses.Step;
import com.chef.emzah.starkchef.R;
import com.chef.emzah.starkchef.UI.Fragments.IngredietFragment;
import com.chef.emzah.starkchef.UI.Fragments.RecipeDetailsFragment;
import com.chef.emzah.starkchef.UI.Fragments.StepsFragment;

import java.util.ArrayList;
import java.util.List;

public class RecipeSteps extends AppCompatActivity implements IngredietFragment.ItemClickListener{

    private List<Recipe> ingredientList;
    private List<Step> stepList;
    private boolean mtwopane;
    private int positionB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_steps);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setUpActivity();


        setUpFragment();


    }

    /**
     * A method that initializes the activity
     */
    private void setUpActivity() {


        Intent intent = getIntent();
        if(intent.hasExtra("stepsList") && intent.hasExtra("position")) {

            stepList=getIntent().getParcelableArrayListExtra("stepsList");

            positionB=intent.getIntExtra("position",0);
        }

        if (findViewById(R.id.videostepsContainer) != null){
            mtwopane = true;
            setUpTabletFragment();
        }
        else {
            mtwopane=false;
        }





    }


    /**
     * A method that sets up a fragment for recipe steps in both phone and Tablet
     */
    private void setUpFragment() {

        Bundle bundle=new Bundle();
        bundle.putInt("position",positionB);

        RecipeDetailsFragment fragment=new RecipeDetailsFragment();
        fragment.setArguments(bundle);

        FragmentManager fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.container,fragment)
                .commit();



        IngredietFragment ingredietFragment=new IngredietFragment();
        ingredietFragment.setItemClickListener(this);
    }

    private void setUpTabletFragment() {

        StepsFragment stepsFragment= new StepsFragment();

        Bundle bundle=new Bundle();
        bundle.putParcelableArrayList("videosteps",new ArrayList<Parcelable>(stepList));
        stepsFragment.setArguments(bundle);
        stepsFragment.setCurrentStep(positionB);


        FragmentManager fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.videostepsContainer,stepsFragment)
                .commit();

    }

    @Override
    public void OnClickednstep(int position) {

        StepsFragment stepsFragment=new StepsFragment();
        stepsFragment.setCurrentStep(position);

        getSupportFragmentManager().beginTransaction().replace(R.id.container,stepsFragment)
                .commit();
    }
}
