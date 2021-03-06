package com.chef.emzah.starkchef.UI.Fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StepsFragment extends Fragment {
    @BindView(R.id.thumbnailurl)ImageView thumnail;
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



    private static final String SAVED_INSTANCE_POSITION = "position";
    private static final String SAVED_PLAYBACK_POSITION = "playback_position";
    private static final String SAVED_PLAYBACK_WINDOW = "current_window";
    private static final String SAVED_PLAY_WHEN_READY = "play_when_ready";
    public StepsFragment() {
    }
    public void setCurrentStep(int currentStepPosition) {

        this.currentPosition = currentStepPosition;
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (savedInstanceState != null){
            currentPosition = savedInstanceState.getInt(SAVED_INSTANCE_POSITION, 0);
            playbackPosition = savedInstanceState.getLong(SAVED_PLAYBACK_POSITION, 0);
            currentWindow = savedInstanceState.getInt(SAVED_PLAYBACK_WINDOW, 0);
            playWhenReady = savedInstanceState.getBoolean(SAVED_PLAY_WHEN_READY, false);

        }
//

    View view=inflater.inflate(R.layout.videoplayersteps,container,false);
    ButterKnife.bind(this,view);
      steps=getArguments().getParcelableArrayList("videosteps");
      //initViews();
        //setUpNxtPrevListeners();



return view;
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }
//

   private void initViews() {


       int currentOrientation = getResources().getConfiguration().orientation;
       if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE){
           hideSystemUi();
       }
       else {
           stepLabel.setText(steps.get(currentPosition).getShortDescription());
           StepDescription.setText(steps.get(currentPosition).getDescription());
       }
       setUpNxtPrevListeners();




   }
    private void setUpNxtPrevListeners() {
        fabNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentPosition < steps.size()-1){
                    setCurrentStep(currentPosition +1);




                    if (steps.get(currentPosition).getVideoURL() !=null){
                        releasePlayer();
                        initViews();
                        initilizePlayer();
                    }
                    else {
                        playerView.setVisibility(View.GONE);
                        thumnail.setVisibility(View.VISIBLE);
                        initViews();
                        if (steps.get(currentPosition).getThumbnailURL()==null){
                            Picasso.get().load(R.drawable.ic_launcher_background).into(thumnail);
                        } else {
                            Picasso.get().load(steps.get(currentPosition).getThumbnailURL()).into(thumnail);
                        }
                    }


                }
            }
        });
        fabPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentPosition>0){
                    setCurrentStep(currentPosition -1);



                    if (steps.get(currentPosition).getVideoURL() !=null){
                        releasePlayer();
                        initViews();
                        initilizePlayer();
                        thumnail.setVisibility(View.GONE);
                    }
                    else {
                        playerView.setVisibility(View.GONE);
                        thumnail.setVisibility(View.VISIBLE);
                        initViews();
                        if (steps.get(currentPosition).getThumbnailURL()==null){
                            Picasso.get().load(R.drawable.ic_launcher_background).into(thumnail);
                        } else {
                            Picasso.get().load(steps.get(currentPosition).getThumbnailURL()).into(thumnail);
                        }
                        ;
                    }

                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

      if (Util.SDK_INT<23 || player==null){

          if (steps.get(currentPosition).getVideoURL()!=null){
              initilizePlayer();
              initViews();
              thumnail.setVisibility(View.GONE);
          }
          else {
              playerView.setVisibility(View.GONE);
              thumnail.setVisibility(View.VISIBLE);
              initViews();
              if (steps.get(currentPosition).getThumbnailURL()==null){
                  Picasso.get().load(R.drawable.ic_launcher_background).into(thumnail);
              }
              else {
                  Picasso.get().load(steps.get(currentPosition).getThumbnailURL()).into(thumnail);
              }
          }

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

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) playerView.getLayoutParams();
        params.width = params.MATCH_PARENT;
        params.height = params.MATCH_PARENT;
        playerView.setLayoutParams(params);
    }

    
    @Override
    public void onStart() {
        super.onStart();
if (Util.SDK_INT >23){

    if (steps.get(currentPosition).getVideoURL() !=null){
        initilizePlayer();

    }
    else {
        playerView.setVisibility(View.GONE);
        thumnail.setVisibility(View.VISIBLE);
        if (steps.get(currentPosition).getThumbnailURL()==null){
            Picasso.get().load(R.drawable.ic_launcher_background).into(thumnail);
        }
       else {
            Picasso.get().load(steps.get(currentPosition).getThumbnailURL()).into(thumnail);
        }
    }




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
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SAVED_INSTANCE_POSITION, currentPosition);
        outState.putLong(SAVED_PLAYBACK_POSITION, playbackPosition);
        outState.putInt(SAVED_PLAYBACK_WINDOW, currentWindow);
        outState.putBoolean(SAVED_PLAY_WHEN_READY, playWhenReady);

    }
}
