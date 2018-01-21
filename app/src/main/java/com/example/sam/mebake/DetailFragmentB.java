package com.example.sam.mebake;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sam.mebake.Model.Steps;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.RenderersFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.decoder.DecoderCounters;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.LoopingMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.video.VideoRendererEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

import static com.google.android.exoplayer2.ExoPlayerFactory.newSimpleInstance;

/**
 * Created by sam on 12/16/17.
 */

public class DetailFragmentB extends Fragment implements VideoRendererEventListener {

    private static final String TAG = "DetailFragmentB";
    private TextView stepTitle;
    private TextView stepDetail;
    private int currentPosition;
    String STEP_LIST = "Step List";
    String CURRENT_POSITION = "Current Position";
    private ArrayList<Steps> stepsList = new ArrayList<>();
    Context context;
    private StepButtonClickListener stepButtonClickListener;




    private String videoLink;
    private SimpleExoPlayerView simpleExoPlayerView;
    private SimpleExoPlayer player;
    RenderersFactory renderersFactory;
    Uri mp4VideoUri;
    private MediaSource videoSource;
    private boolean mExoPlayerFullscreen = false;
    //private FrameLayout mFullScreenButton;
    //private ImageView mFullScreenIcon;
    private Dialog mFullScreenDialog;

    private int mResumeWindow;
    private long mResumePosition;

    private final String STATE_RESUME_WINDOW = "resumeWindow";
    private final String STATE_RESUME_POSITION = "resumePosition";
    private final String STATE_PLAYER_FULLSCREEN = "playerFullscreen";

    public interface StepButtonClickListener {
        //pass in the List and position on this clicklistener
        void onStepButtonClickListener(List<Steps> everySteps, int position);
    }



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView =

                inflater.inflate(R.layout.detail_page_b, container, false);
        context = getActivity();
        stepTitle = rootView.findViewById(R.id.step_title);
        stepDetail = rootView.findViewById(R.id.step_detail);
        stepButtonClickListener = (RecipeDetail) getActivity();
        //use rootview to find the all the view ids.


        if(savedInstanceState !=null){
            mResumeWindow = savedInstanceState.getInt(STATE_RESUME_WINDOW);
            mResumePosition = savedInstanceState.getLong(STATE_RESUME_POSITION);
            mExoPlayerFullscreen = savedInstanceState.getBoolean(STATE_PLAYER_FULLSCREEN);
        }


        Bundle bundle = getArguments();
        if (bundle != null) {

            currentPosition = bundle.getInt("position");
            Steps step = bundle.getParcelable("stepsdetail");
            stepsList = bundle.getParcelableArrayList("steplist");
            stepDetail.setText(step.getDescription());
            stepTitle.setText(step.getShortDescription());
            videoLink = step.getVideoURL();
        }

        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory videoTrackSelectionFactory = new AdaptiveTrackSelection.Factory(bandwidthMeter);
        TrackSelector trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);

        LoadControl loadControl = new DefaultLoadControl();

        renderersFactory = new DefaultRenderersFactory(context);

        player = newSimpleInstance(renderersFactory, trackSelector, loadControl);
        simpleExoPlayerView = new SimpleExoPlayerView(context);
        simpleExoPlayerView = rootView.findViewById(R.id.video);

        simpleExoPlayerView.setUseController(true);
        simpleExoPlayerView.requestFocus();

        simpleExoPlayerView.setPlayer(player);


        mp4VideoUri = Uri.parse(videoLink);

        DefaultBandwidthMeter bandwidthMeterA = new DefaultBandwidthMeter();
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(context,
                Util.getUserAgent(context, "MeBake"), bandwidthMeterA);
        ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
        videoSource = new ExtractorMediaSource(mp4VideoUri,
                dataSourceFactory, extractorsFactory, null, null);
        player.prepare(videoSource, false, true);
        player.addListener(new Player.EventListener() {

            @Override
            public void onTimelineChanged(Timeline timeline, Object manifest) {
                Log.v(TAG, "Listener-onTimelineChanged...");
            }

            @Override
            public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {
                Log.v(TAG, "Listener-onTracksChanged...");
            }

            @Override
            public void onLoadingChanged(boolean isLoading) {
                Log.v(TAG, "Listener-onLoadingChanged...isLoading:" + isLoading);
            }

            @Override
            public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
                Log.v(TAG, "Listener-onPlayerStateChanged..." + playbackState);
            }

            @Override
            public void onRepeatModeChanged(int repeatMode) {
                Log.v(TAG, "Listener-onRepeatModeChanged...");
            }

            @Override
            public void onPlayerError(ExoPlaybackException error) {
                Log.v(TAG, "Listener-onPlayerError...");
                player.stop();
                player.prepare(videoSource);
                player.setPlayWhenReady(true);
            }

            @Override
            public void onPositionDiscontinuity() {
                Log.v(TAG, "Listener-onPositionDiscontinuity...");
            }

            @Override
            public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
                Log.v(TAG, "Listener-onPlaybackParametersChanged...");
            }
        });

        mResumePosition = player.getCurrentPosition();
        player.seekTo(mResumePosition);
        player.setPlayWhenReady(true);
        player.setVideoDebugListener(this);

        Button mPrevStep = rootView.findViewById(R.id.previous_step);
        Button mNextStep = rootView.findViewById(R.id.next_step);

        mNextStep.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View view) {
                                                 if(player!=null){
                                                     player.stop();
                                                 }
                                        if(currentPosition + 1 < stepsList.size()) {
                                            stepButtonClickListener.onStepButtonClickListener(stepsList, currentPosition + 1);
                                        }else
                                            Toast.makeText(getActivity(), "End of the Line for you", Toast.LENGTH_SHORT).show();
                                         }


                                     });


                mPrevStep.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (player != null) {
                            player.stop();

                        }
                        if (currentPosition -1 > 0) {
                            stepButtonClickListener.onStepButtonClickListener(stepsList, currentPosition-1);
                        }else
                            Toast.makeText(getActivity(), "Beginning of the Line for you", Toast.LENGTH_SHORT).show();
                    }
                });



        return rootView;

}

@Override
public void onSaveInstanceState(Bundle outState){
        outState.putInt(STATE_RESUME_WINDOW, mResumeWindow);
        outState.putLong(STATE_RESUME_POSITION, mResumePosition);
        outState.putBoolean(STATE_PLAYER_FULLSCREEN, mExoPlayerFullscreen);
        /*outState.putInt(CURRENT_POSITION, currentPosition);
        outState.putParcelableArrayList(STEP_LIST,stepsList);*/
}

public void onActivityCreated(Bundle outState){
    super.onActivityCreated(outState);
    if (outState !=null){
    /*currentPosition = outState.getInt(CURRENT_POSITION);
    stepsList = outState.getParcelableArrayList(STEP_LIST);*/
    }

}

private void initFullscreenDialog(){
    mFullScreenDialog = new Dialog(context, android.R.style.Theme_Black_NoTitleBar_Fullscreen){
        public void onBackPressed(){
            if(!mExoPlayerFullscreen)
                openFullscreenDialog();
            else
                closeFullscreenDialog();
            super.onBackPressed();
        }
    };
}

private void openFullscreenDialog(){
    ((ViewGroup) simpleExoPlayerView.getParent()).removeView(simpleExoPlayerView);
    mFullScreenDialog.addContentView(simpleExoPlayerView, new ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    //mFullScreenIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_fullscreen_gosmall));
    mExoPlayerFullscreen=true;
    mFullScreenDialog.show();
}

private void closeFullscreenDialog(){
    ((ViewGroup) simpleExoPlayerView.getParent()).removeView(simpleExoPlayerView);
    ((FrameLayout) simpleExoPlayerView.findViewById(R.id.detail_fragment_a)).addView(simpleExoPlayerView);
    //mFullScreenIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_fullscreen_gobig));
    mExoPlayerFullscreen = false;
    mFullScreenDialog.dismiss();

}

@Override
public void onResume(){
    super.onResume();
    initFullscreenDialog();

}

@Override
public void onPause(){
    super.onPause();
    player.stop();
    player.release();
}

@Override
public void onStop(){
    super.onStop();
    player.stop();
    player.release();

}




    @Override
    public void onVideoEnabled(DecoderCounters counters) {

    }

    @Override
    public void onVideoDecoderInitialized(String decoderName, long initializedTimestampMs, long initializationDurationMs) {

    }

    @Override
    public void onVideoInputFormatChanged(Format format) {

    }

    @Override
    public void onDroppedFrames(int count, long elapsedMs) {

    }

    @Override
    public void onVideoSizeChanged(int width, int height, int unappliedRotationDegrees, float pixelWidthHeightRatio) {

    }

    @Override
    public void onRenderedFirstFrame(Surface surface) {

    }

    @Override
    public void onVideoDisabled(DecoderCounters counters) {

    }

}

