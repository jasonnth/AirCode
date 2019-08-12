package com.devbrackets.android.exomedia.core.api;

import android.net.Uri;
import android.view.View.OnTouchListener;
import com.devbrackets.android.exomedia.core.EMListenerMux;
import com.devbrackets.android.exomedia.core.builder.RenderBuilder;
import com.devbrackets.android.exomedia.core.video.scale.ScaleType;
import com.google.android.exoplayer.MediaFormat;
import java.util.List;
import java.util.Map;

public interface VideoViewApi {
    Map<Integer, List<MediaFormat>> getAvailableTracks();

    int getBufferedPercent();

    int getCurrentPosition();

    int getDuration();

    boolean isPlaying();

    void onVideoSizeChanged(int i, int i2);

    void pause();

    void release();

    boolean restart();

    void seekTo(int i);

    void setListenerMux(EMListenerMux eMListenerMux);

    void setMeasureBasedOnAspectRatioEnabled(boolean z);

    void setOnTouchListener(OnTouchListener onTouchListener);

    void setScaleType(ScaleType scaleType);

    void setTrack(int i, int i2);

    void setVideoRotation(int i, boolean z);

    void setVideoUri(Uri uri);

    void setVideoUri(Uri uri, RenderBuilder renderBuilder);

    boolean setVolume(float f);

    void start();

    void stopPlayback();

    void suspend();

    boolean trackSelectionAvailable();
}
