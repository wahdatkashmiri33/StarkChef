package com.chef.emzah.starkchef.UI;

import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

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


        position=getIntent().getIntExtra("videoposition",0);
        steps=getIntent().getParcelableArrayListExtra("videosteps");



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