package com.devbrackets.android.exomedia.core.video;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnInfoListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnSeekCompleteListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Surface;
import android.view.TextureView.SurfaceTextureListener;
import android.widget.MediaController.MediaPlayerControl;
import java.io.IOException;
import java.util.Map;

public class TextureVideoView extends ResizingTextureView implements MediaPlayerControl {
    protected int currentBufferPercent;
    protected State currentState = State.IDLE;
    protected Map<String, String> headers;
    protected InternalListeners internalListeners = new InternalListeners();
    protected MediaPlayer mediaPlayer;
    protected OnBufferingUpdateListener onBufferingUpdateListener;
    protected OnCompletionListener onCompletionListener;
    protected OnErrorListener onErrorListener;
    protected OnInfoListener onInfoListener;
    protected OnPreparedListener onPreparedListener;
    protected OnSeekCompleteListener onSeekCompleteListener;
    protected boolean playRequested = false;
    protected int requestedSeek;

    protected class InternalListeners implements OnBufferingUpdateListener, OnCompletionListener, OnErrorListener, OnInfoListener, OnPreparedListener, OnSeekCompleteListener, OnVideoSizeChangedListener {
        protected InternalListeners() {
        }

        public void onBufferingUpdate(MediaPlayer mp, int percent) {
            TextureVideoView.this.currentBufferPercent = percent;
            if (TextureVideoView.this.onBufferingUpdateListener != null) {
                TextureVideoView.this.onBufferingUpdateListener.onBufferingUpdate(mp, percent);
            }
        }

        public void onCompletion(MediaPlayer mp) {
            TextureVideoView.this.currentState = State.COMPLETED;
            if (TextureVideoView.this.onCompletionListener != null) {
                TextureVideoView.this.onCompletionListener.onCompletion(TextureVideoView.this.mediaPlayer);
            }
        }

        public void onSeekComplete(MediaPlayer mp) {
            if (TextureVideoView.this.onSeekCompleteListener != null) {
                TextureVideoView.this.onSeekCompleteListener.onSeekComplete(mp);
            }
        }

        public boolean onError(MediaPlayer mp, int what, int extra) {
            Log.d("TextureVideoView", "Error: " + what + "," + extra);
            TextureVideoView.this.currentState = State.ERROR;
            return TextureVideoView.this.onErrorListener == null || TextureVideoView.this.onErrorListener.onError(TextureVideoView.this.mediaPlayer, what, extra);
        }

        public void onPrepared(MediaPlayer mp) {
            TextureVideoView.this.currentState = State.PREPARED;
            if (TextureVideoView.this.onPreparedListener != null) {
                TextureVideoView.this.onPreparedListener.onPrepared(TextureVideoView.this.mediaPlayer);
            }
            TextureVideoView.this.updateVideoSize(mp.getVideoWidth(), mp.getVideoHeight());
            if (TextureVideoView.this.requestedSeek != 0) {
                TextureVideoView.this.seekTo(TextureVideoView.this.requestedSeek);
            }
            if (TextureVideoView.this.playRequested) {
                TextureVideoView.this.start();
            }
        }

        public boolean onInfo(MediaPlayer mp, int what, int extra) {
            return TextureVideoView.this.onInfoListener == null || TextureVideoView.this.onInfoListener.onInfo(mp, what, extra);
        }

        public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
            if (TextureVideoView.this.updateVideoSize(mp.getVideoWidth(), mp.getVideoHeight())) {
                TextureVideoView.this.requestLayout();
            }
        }
    }

    protected enum State {
        ERROR,
        IDLE,
        PREPARING,
        PREPARED,
        PLAYING,
        PAUSED,
        COMPLETED
    }

    protected class TextureVideoViewSurfaceListener implements SurfaceTextureListener {
        protected TextureVideoViewSurfaceListener() {
        }

        public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int width, int height) {
            TextureVideoView.this.mediaPlayer.setSurface(new Surface(surfaceTexture));
            if (TextureVideoView.this.playRequested) {
                TextureVideoView.this.start();
            }
        }

        public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
            if (TextureVideoView.this.mediaPlayer != null && width > 0 && height > 0) {
                if (TextureVideoView.this.requestedSeek != 0) {
                    TextureVideoView.this.seekTo(TextureVideoView.this.requestedSeek);
                }
                if (TextureVideoView.this.playRequested) {
                    TextureVideoView.this.start();
                }
            }
        }

        public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
            surface.release();
            TextureVideoView.this.suspend();
            return true;
        }

        public void onSurfaceTextureUpdated(SurfaceTexture surface) {
        }
    }

    public TextureVideoView(Context context) {
        super(context);
        setup(context, null);
    }

    public TextureVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setup(context, attrs);
    }

    public TextureVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setup(context, attrs);
    }

    @TargetApi(21)
    public TextureVideoView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setup(context, attrs);
    }

    public void start() {
        if (isReady()) {
            this.mediaPlayer.start();
            requestFocus();
            this.currentState = State.PLAYING;
        }
        this.playRequested = true;
    }

    public void pause() {
        if (isReady() && this.mediaPlayer.isPlaying()) {
            this.mediaPlayer.pause();
            this.currentState = State.PAUSED;
        }
        this.playRequested = false;
    }

    public int getDuration() {
        if (isReady()) {
            return this.mediaPlayer.getDuration();
        }
        return 0;
    }

    public int getCurrentPosition() {
        if (isReady()) {
            return this.mediaPlayer.getCurrentPosition();
        }
        return 0;
    }

    public void seekTo(int msec) {
        if (isReady()) {
            this.mediaPlayer.seekTo(msec);
            this.requestedSeek = 0;
            return;
        }
        this.requestedSeek = msec;
    }

    public boolean isPlaying() {
        return isReady() && this.mediaPlayer.isPlaying();
    }

    public int getBufferPercentage() {
        if (this.mediaPlayer != null) {
            return this.currentBufferPercent;
        }
        return 0;
    }

    public boolean canPause() {
        return this.currentState == State.PREPARED || this.currentState == State.PLAYING;
    }

    public boolean canSeekBackward() {
        return this.currentState == State.PREPARED || this.currentState == State.PLAYING || this.currentState == State.PAUSED;
    }

    public boolean canSeekForward() {
        return this.currentState == State.PREPARED || this.currentState == State.PLAYING || this.currentState == State.PAUSED;
    }

    public void stopPlayback() {
        this.currentState = State.IDLE;
        if (isReady()) {
            try {
                this.mediaPlayer.stop();
            } catch (Exception e) {
                Log.d("TextureVideoView", "stopPlayback: error calling mediaPlayer.stop()", e);
            }
        }
        this.playRequested = false;
    }

    public void suspend() {
        this.currentState = State.IDLE;
        try {
            this.mediaPlayer.reset();
            this.mediaPlayer.release();
        } catch (Exception e) {
            Log.d("TextureVideoView", "stopPlayback: error calling mediaPlayer.reset() or mediaPlayer.release()", e);
        }
        this.playRequested = false;
    }

    public void setVideoURI(Uri uri) {
        setVideoURI(uri, null);
    }

    public void setVideoURI(Uri uri, Map<String, String> headers2) {
        this.headers = headers2;
        this.requestedSeek = 0;
        this.playRequested = false;
        openVideo(uri);
        requestLayout();
        invalidate();
    }

    public void setOnPreparedListener(OnPreparedListener listener) {
        this.onPreparedListener = listener;
    }

    public void setOnCompletionListener(OnCompletionListener listener) {
        this.onCompletionListener = listener;
    }

    public void setOnBufferingUpdateListener(OnBufferingUpdateListener listener) {
        this.onBufferingUpdateListener = listener;
    }

    public void setOnSeekCompleteListener(OnSeekCompleteListener listener) {
        this.onSeekCompleteListener = listener;
    }

    public void setOnErrorListener(OnErrorListener listener) {
        this.onErrorListener = listener;
    }

    public void setOnInfoListener(OnInfoListener listener) {
        this.onInfoListener = listener;
    }

    public int getAudioSessionId() {
        return this.mediaPlayer.getAudioSessionId();
    }

    /* access modifiers changed from: protected */
    public boolean isReady() {
        return (this.currentState == State.ERROR || this.currentState == State.IDLE || this.currentState == State.PREPARING) ? false : true;
    }

    /* access modifiers changed from: protected */
    public void openVideo(Uri uri) {
        if (uri != null) {
            this.currentBufferPercent = 0;
            try {
                this.mediaPlayer.setDataSource(getContext().getApplicationContext(), uri, this.headers);
                this.mediaPlayer.prepareAsync();
                this.currentState = State.PREPARING;
            } catch (IOException | IllegalArgumentException ex) {
                Log.w("TextureVideoView", "Unable to open content: " + uri, ex);
                this.currentState = State.ERROR;
                this.internalListeners.onError(this.mediaPlayer, 1, 0);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void setup(Context context, AttributeSet attrs) {
        initMediaPlayer();
        setSurfaceTextureListener(new TextureVideoViewSurfaceListener());
        setFocusable(true);
        setFocusableInTouchMode(true);
        requestFocus();
        updateVideoSize(0, 0);
        this.currentState = State.IDLE;
    }

    /* access modifiers changed from: protected */
    public void initMediaPlayer() {
        this.mediaPlayer = new MediaPlayer();
        this.mediaPlayer.setOnInfoListener(this.internalListeners);
        this.mediaPlayer.setOnErrorListener(this.internalListeners);
        this.mediaPlayer.setOnPreparedListener(this.internalListeners);
        this.mediaPlayer.setOnCompletionListener(this.internalListeners);
        this.mediaPlayer.setOnSeekCompleteListener(this.internalListeners);
        this.mediaPlayer.setOnBufferingUpdateListener(this.internalListeners);
        this.mediaPlayer.setOnVideoSizeChangedListener(this.internalListeners);
        this.mediaPlayer.setAudioStreamType(3);
        this.mediaPlayer.setScreenOnWhilePlaying(true);
    }
}
