package com.devbrackets.android.exomedia.core.video;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View.OnTouchListener;
import com.devbrackets.android.exomedia.core.EMListenerMux;
import com.devbrackets.android.exomedia.core.api.VideoViewApi;
import com.devbrackets.android.exomedia.core.builder.RenderBuilder;
import com.google.android.exoplayer.MediaFormat;
import java.util.List;
import java.util.Map;

public class NativeVideoView extends TextureVideoView implements VideoViewApi {
    protected EMListenerMux listenerMux;
    private OnTouchListener touchListener;

    public NativeVideoView(Context context) {
        super(context);
        setup();
    }

    public NativeVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setup();
    }

    public NativeVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setup();
    }

    @TargetApi(21)
    public NativeVideoView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setup();
    }

    public boolean onTouchEvent(MotionEvent ev) {
        boolean flag = false;
        if (this.touchListener != null) {
            flag = this.touchListener.onTouch(this, ev);
        }
        return flag || super.onTouchEvent(ev);
    }

    public void setOnTouchListener(OnTouchListener listener) {
        this.touchListener = listener;
        super.setOnTouchListener(listener);
    }

    public void setVideoUri(Uri uri) {
        setVideoUri(uri, null);
    }

    public void setVideoUri(Uri uri, RenderBuilder renderBuilder) {
        super.setVideoURI(uri);
    }

    public boolean setVolume(float volume) {
        return false;
    }

    public int getDuration() {
        if (!this.listenerMux.isPrepared()) {
            return 0;
        }
        return super.getDuration();
    }

    public int getCurrentPosition() {
        if (!this.listenerMux.isPrepared()) {
            return 0;
        }
        return super.getCurrentPosition();
    }

    public int getBufferedPercent() {
        return getBufferPercentage();
    }

    public void start() {
        super.start();
        this.listenerMux.setNotifiedCompleted(false);
    }

    public boolean restart() {
        if (this.currentState != State.COMPLETED) {
            return false;
        }
        seekTo(0);
        start();
        this.listenerMux.setNotifiedPrepared(false);
        this.listenerMux.setNotifiedCompleted(false);
        return true;
    }

    public void stopPlayback() {
        super.stopPlayback();
        this.listenerMux.clearSurfaceWhenReady(this);
    }

    public void release() {
    }

    public boolean trackSelectionAvailable() {
        return false;
    }

    public void setTrack(int trackType, int trackIndex) {
    }

    public Map<Integer, List<MediaFormat>> getAvailableTracks() {
        return null;
    }

    public void setListenerMux(EMListenerMux listenerMux2) {
        this.listenerMux = listenerMux2;
        setOnCompletionListener(listenerMux2);
        setOnPreparedListener(listenerMux2);
        setOnBufferingUpdateListener(listenerMux2);
        setOnSeekCompleteListener(listenerMux2);
        setOnErrorListener(listenerMux2);
    }

    public void onVideoSizeChanged(int width, int height) {
        if (updateVideoSize(width, height)) {
            requestLayout();
        }
    }

    /* access modifiers changed from: protected */
    public void setup() {
    }
}
