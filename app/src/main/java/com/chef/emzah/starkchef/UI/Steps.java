package com.chef.emzah.starkchef.UI;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.chef.emzah.starkchef.ModalClasses.Step;
import com.chef.emzah.starkchef.R;
import com.chef.emzah.starkchef.UI.Fragments.StepsFragment;

public class Steps extends AppCompatActivity {

private int CurrentPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);

        Intent intent=getIntent();
       // Step data=getIntent().getParcelableExtra("stepslist");
       CurrentPosition= intent.getIntExtra("stepslist",0);

        Log.d("position C ki",""+CurrentPosition);
        StepsFragment stepsFragment=new StepsFragment();
        stepsFragment.setCurrentStep(CurrentPosition);

        FragmentManager fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.videocontainer,stepsFragment)
                .commit();


    }


}
