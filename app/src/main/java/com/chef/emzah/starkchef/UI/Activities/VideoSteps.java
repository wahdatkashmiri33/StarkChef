package com.chef.emzah.starkchef.UI.Activities;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.chef.emzah.starkchef.ModalClasses.Step;
import com.chef.emzah.starkchef.R;
import com.chef.emzah.starkchef.UI.Fragments.StepsFragment;

import java.util.ArrayList;
import java.util.List;

public class VideoSteps extends AppCompatActivity {

    public List<Step> steps;
    public int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_steps);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(getIntent().hasExtra("videoposition") && getIntent().hasExtra("videosteps"))

            position=getIntent().getIntExtra("videoposition",0);
        steps=getIntent().getParcelableArrayListExtra("videosteps");

        setUpFragment();

//
    }

    private void setUpFragment() {

        StepsFragment stepsFragment= new StepsFragment();
        Bundle bundle=new Bundle();
        bundle.putParcelableArrayList("videosteps",new ArrayList<Parcelable>(steps));
        stepsFragment.setArguments(bundle);
        stepsFragment.setCurrentStep(position);


        FragmentManager fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.videostepsContainer,stepsFragment)
                .commit();

    }
}