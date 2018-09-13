package com.chef.emzah.starkchef.UI.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
    @BindView(R.id.player_view) PlayerView playerView;
    private SimpleExoPlayer player;
   public List<Step> steps;
    public StepsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view=inflater.inflate(R.layout.videoplayersteps,container,false);


        ButterKnife.bind(this,view);
return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        steps=getActivity().getIntent().getParcelableArrayListExtra("stepsList");
        Intent intent=getActivity().getIntent();
     String positions=   intent.getStringExtra("positions");
     int position=Integer.parseInt(positions);
        Step step=steps.get(position);
       Uri mediauri=Uri.parse(step.getVideoURL());
        player=ExoPlayerFactory.newSimpleInstance(
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
    public void onStop() {
        super.onStop();
        playerView.setPlayer(null);
        player.release();
        player=null;
    }
}
