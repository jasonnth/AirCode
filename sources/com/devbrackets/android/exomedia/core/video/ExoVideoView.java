package com.devbrackets.android.exomedia.core.video;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.TextureView.SurfaceTextureListener;
import com.devbrackets.android.exomedia.core.EMListenerMux;
import com.devbrackets.android.exomedia.core.api.VideoViewApi;
import com.devbrackets.android.exomedia.core.builder.DashRenderBuilder;
import com.devbrackets.android.exomedia.core.builder.HlsRenderBuilder;
import com.devbrackets.android.exomedia.core.builder.RenderBuilder;
import com.devbrackets.android.exomedia.core.builder.SmoothStreamRenderBuilder;
import com.devbrackets.android.exomedia.core.exoplayer.EMExoPlayer;
import com.devbrackets.android.exomedia.core.listener.Id3MetadataListener;
import com.devbrackets.android.exomedia.listener.OnBufferUpdateListener;
import com.devbrackets.android.exomedia.type.MediaSourceType;
import com.devbrackets.android.exomedia.util.MediaSourceUtil;
import com.google.android.exoplayer.MediaFormat;
import com.google.android.exoplayer.audio.AudioCapabilities;
import com.google.android.exoplayer.audio.AudioCapabilitiesReceiver;
import com.google.android.exoplayer.audio.AudioCapabilitiesReceiver.Listener;
import com.google.android.exoplayer.metadata.id3.Id3Frame;
import java.util.List;
import java.util.Map;

@TargetApi(16)
public class ExoVideoView extends ResizingTextureView implements VideoViewApi, Listener {
    protected AudioCapabilities audioCapabilities;
    protected AudioCapabilitiesReceiver audioCapabilitiesReceiver;
    protected EMExoPlayer emExoPlayer;
    protected InternalListeners internalListeners = new InternalListeners();
    protected EMListenerMux listenerMux;
    protected boolean playRequested = false;

    protected class EMExoVideoSurfaceTextureListener implements SurfaceTextureListener {
        protected EMExoVideoSurfaceTextureListener() {
        }

        public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int width, int height) {
            ExoVideoView.this.emExoPlayer.setSurface(new Surface(surfaceTexture));
            if (ExoVideoView.this.playRequested) {
                ExoVideoView.this.emExoPlayer.setPlayWhenReady(true);
            }
        }

        public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
            ExoVideoView.this.emExoPlayer.blockingClearSurface();
            surfaceTexture.release();
            return true;
        }

        public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int width, int height) {
        }

        public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        }
    }

    protected class InternalListeners implements Id3MetadataListener, OnBufferUpdateListener {
        protected InternalListeners() {
        }

        public void onId3Metadata(List<Id3Frame> metadata) {
            ExoVideoView.this.listenerMux.onId3Metadata(metadata);
        }

        public void onBufferingUpdate(int percent) {
            ExoVideoView.this.listenerMux.onBufferingUpdate(percent);
        }
    }

    public ExoVideoView(Context context) {
        super(context);
        setup();
    }

    public ExoVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setup();
    }

    public ExoVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setup();
    }

    public ExoVideoView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setup();
    }

    public void setVideoUri(Uri uri) {
        setVideoUri(uri, uri == null ? null : getRendererBuilder(MediaSourceUtil.getType(uri), uri));
    }

    public void setVideoUri(Uri uri, RenderBuilder renderBuilder) {
        this.playRequested = false;
        if (uri == null) {
            this.emExoPlayer.replaceRenderBuilder(null);
        } else {
            this.emExoPlayer.replaceRenderBuilder(renderBuilder);
            this.listenerMux.setNotifiedCompleted(false);
        }
        this.listenerMux.setNotifiedPrepared(false);
        this.emExoPlayer.seekTo(0);
    }

    public boolean restart() {
        if (!this.emExoPlayer.restart()) {
            return false;
        }
        this.listenerMux.setNotifiedPrepared(false);
        this.listenerMux.setNotifiedCompleted(false);
        return true;
    }

    public boolean setVolume(float volume) {
        this.emExoPlayer.setVolume(volume);
        return true;
    }

    public void seekTo(int milliseconds) {
        this.emExoPlayer.seekTo((long) milliseconds);
    }

    public boolean isPlaying() {
        return this.emExoPlayer.getPlayWhenReady();
    }

    public void start() {
        this.emExoPlayer.setPlayWhenReady(true);
        this.listenerMux.setNotifiedCompleted(false);
        this.playRequested = true;
    }

    public void pause() {
        this.emExoPlayer.setPlayWhenReady(false);
        this.playRequested = false;
    }

    public void stopPlayback() {
        this.emExoPlayer.stop();
        this.playRequested = false;
        this.listenerMux.clearSurfaceWhenReady(this);
    }

    public void suspend() {
        this.emExoPlayer.release();
        this.playRequested = false;
    }

    public int getDuration() {
        if (!this.listenerMux.isPrepared()) {
            return 0;
        }
        return (int) this.emExoPlayer.getDuration();
    }

    public int getCurrentPosition() {
        if (!this.listenerMux.isPrepared()) {
            return 0;
        }
        return (int) this.emExoPlayer.getCurrentPosition();
    }

    public int getBufferedPercent() {
        return this.emExoPlayer.getBufferedPercentage();
    }

    public boolean trackSelectionAvailable() {
        return true;
    }

    public void setTrack(int trackType, int trackIndex) {
        this.emExoPlayer.setSelectedTrack(trackType, trackIndex);
    }

    public Map<Integer, List<MediaFormat>> getAvailableTracks() {
        return this.emExoPlayer.getAvailableTracks();
    }

    public void onAudioCapabilitiesChanged(AudioCapabilities audioCapabilities2) {
        if (!audioCapabilities2.equals(this.audioCapabilities)) {
            this.audioCapabilities = audioCapabilities2;
        }
    }

    public void release() {
        this.emExoPlayer.release();
        if (this.audioCapabilitiesReceiver != null) {
            this.audioCapabilitiesReceiver.unregister();
            this.audioCapabilitiesReceiver = null;
        }
    }

    public void setListenerMux(EMListenerMux listenerMux2) {
        this.listenerMux = listenerMux2;
        this.emExoPlayer.addListener(listenerMux2);
    }

    public void onVideoSizeChanged(int width, int height) {
        if (updateVideoSize(width, height)) {
            requestLayout();
        }
    }

    /* access modifiers changed from: protected */
    public void setup() {
        initExoPlayer();
        this.audioCapabilitiesReceiver = new AudioCapabilitiesReceiver(getContext().getApplicationContext(), this);
        this.audioCapabilitiesReceiver.register();
        setSurfaceTextureListener(new EMExoVideoSurfaceTextureListener());
        updateVideoSize(0, 0);
    }

    /* access modifiers changed from: protected */
    public void initExoPlayer() {
        this.emExoPlayer = new EMExoPlayer(null);
        this.emExoPlayer.setMetadataListener(this.internalListeners);
        this.emExoPlayer.setBufferUpdateListener(this.internalListeners);
    }

    /* access modifiers changed from: protected */
    public RenderBuilder getRendererBuilder(MediaSourceType renderType, Uri uri) {
        switch (renderType) {
            case HLS:
                return new HlsRenderBuilder(getContext().getApplicationContext(), getUserAgent(), uri.toString());
            case DASH:
                return new DashRenderBuilder(getContext().getApplicationContext(), getUserAgent(), uri.toString());
            case SMOOTH_STREAM:
                return new SmoothStreamRenderBuilder(getContext().getApplicationContext(), getUserAgent(), uri.toString());
            default:
                return new RenderBuilder(getContext().getApplicationContext(), getUserAgent(), uri.toString());
        }
    }

    public String getUserAgent() {
        return String.format("EMVideoView %s / Android %s / %s", new Object[]{"3.0.5 (30500)", VERSION.RELEASE, Build.MODEL});
    }
}
