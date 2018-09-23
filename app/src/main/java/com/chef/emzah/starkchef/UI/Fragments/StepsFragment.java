package com.chef.emzah.starkchef.UI.Fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chef.emzah.starkchef.ModalClasses.Step;
import com.chef.emzah.starkchef.R;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.RenderersFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StepsFragment extends Fragment {
    @BindView(R.id.fab_next) FloatingActionButton  fabNext;
    @BindView(R.id.fab_prev) FloatingActionButton fabPrev;
    @BindView(R.id.player_view) PlayerView playerView;
    @BindView(R.id.txt_step_label) TextView stepLabel;
    @BindView(R.id.txt_step_description) TextView StepDescription;
    private SimpleExoPlayer player;

   public List<Step> steps;
   public int currentPosition;
    public long playbackPosition = 0;
    public int currentWindow = 0;
    public boolean playWhenReady = true;
    public StepsFragment() {
    }
    public void setCurrentStep(int currentStepPosition) {

        this.currentPosition = currentStepPosition;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view=inflater.inflate(R.layout.videoplayersteps,container,false);
    ButterKnife.bind(this,view);
      steps=getArguments().getParcelableArrayList("videosteps");
      initViews();
    setUpNxtPrevListeners();

return view;
    }

   private void initViews() {
        stepLabel.setText(steps.get(currentPosition).getShortDescription());
        StepDescription.setText(steps.get(currentPosition).getDescription());
   }

    private void setUpNxtPrevListeners() {
        fabNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentPosition < steps.size()-1){
                    setCurrentStep(currentPosition +1);
                    releasePlayer();
                initViews();
                    initilizePlayer();

                }
            }
        });
        fabPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentPosition>0){
                    setCurrentStep(currentPosition -1);
                    releasePlayer();
                  initViews();
                    initilizePlayer();

                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

      if (Util.SDK_INT<23 || player==null){
          initilizePlayer();
      }
    }

    @SuppressLint("InlinedApi")
    private void hideSystemUi() {
        playerView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    
    @Override
    public void onStart() {
        super.onStart();
if (Util.SDK_INT >23){
    initilizePlayer();

}

    }

    private void initilizePlayer() {
        Step step=steps.get(currentPosition);
        Uri mediauri=Uri.parse(step.getVideoURL());
        player= ExoPlayerFactory.newSimpleInstance(
                new DefaultRenderersFactory(getContext()),
                new DefaultTrackSelector(),new DefaultLoadControl());
        playerView.setPlayer(player);
        DefaultDataSourceFactory dataSourceFactory=new DefaultDataSourceFactory(
                getContext(), Util.getUserAgent(getContext(),"baking-app"));
        ExtractorMediaSource mediaSource=new ExtractorMediaSource.Factory(dataSourceFactory)
                .createMediaSource(mediauri);
        player.prepare(mediaSource);
        player.setPlayWhenReady(true);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT <= 23) {
            releasePlayer();
        }
    }

    private void releasePlayer() {
        if (player !=null){
            playbackPosition = player.getCurrentPosition();
            currentWindow = player.getCurrentWindowIndex();
            playWhenReady = player.getPlayWhenReady();
            player.release();
            player = null;
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT>23){
            releasePlayer();
        }

    }
}
