package com.yqritc.scalablevideoview;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnInfoListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.TextureView;
import android.view.TextureView.SurfaceTextureListener;
import java.io.FileDescriptor;
import java.io.IOException;

public class ScalableVideoView extends TextureView implements OnVideoSizeChangedListener, SurfaceTextureListener {
    /* access modifiers changed from: protected */
    public MediaPlayer mMediaPlayer;
    protected ScalableType mScalableType;

    public ScalableVideoView(Context context) {
        this(context, null);
    }

    public ScalableVideoView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScalableVideoView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mScalableType = ScalableType.NONE;
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.scaleStyle, 0, 0);
            if (a != null) {
                int scaleType = a.getInt(R.styleable.scaleStyle_scalableType, ScalableType.NONE.ordinal());
                a.recycle();
                this.mScalableType = ScalableType.values()[scaleType];
            }
        }
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int width, int height) {
        Surface surface = new Surface(surfaceTexture);
        if (this.mMediaPlayer != null) {
            this.mMediaPlayer.setSurface(surface);
        }
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        return false;
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surface) {
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mMediaPlayer != null) {
            if (isPlaying()) {
                stop();
            }
            release();
        }
    }

    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
        scaleVideoSize(width, height);
    }

    private void scaleVideoSize(int videoWidth, int videoHeight) {
        if (videoWidth != 0 && videoHeight != 0) {
            Matrix matrix = new ScaleManager(new Size(getWidth(), getHeight()), new Size(videoWidth, videoHeight)).getScaleMatrix(this.mScalableType);
            if (matrix != null) {
                setTransform(matrix);
            }
        }
    }

    private void initializeMediaPlayer() {
        if (this.mMediaPlayer == null) {
            this.mMediaPlayer = new MediaPlayer();
            this.mMediaPlayer.setOnVideoSizeChangedListener(this);
            setSurfaceTextureListener(this);
            return;
        }
        reset();
    }

    public void setRawData(int id) throws IOException {
        setDataSource(getResources().openRawResourceFd(id));
    }

    public void setAssetData(String assetName) throws IOException {
        setDataSource(getContext().getAssets().openFd(assetName));
    }

    private void setDataSource(AssetFileDescriptor afd) throws IOException {
        setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
        afd.close();
    }

    public void setDataSource(String path) throws IOException {
        initializeMediaPlayer();
        this.mMediaPlayer.setDataSource(path);
    }

    public void setDataSource(Context context, Uri uri) throws IOException {
        initializeMediaPlayer();
        this.mMediaPlayer.setDataSource(context, uri);
    }

    public void setDataSource(FileDescriptor fd, long offset, long length) throws IOException {
        initializeMediaPlayer();
        this.mMediaPlayer.setDataSource(fd, offset, length);
    }

    public void setDataSource(FileDescriptor fd) throws IOException {
        initializeMediaPlayer();
        this.mMediaPlayer.setDataSource(fd);
    }

    public void setScalableType(ScalableType scalableType) {
        this.mScalableType = scalableType;
        scaleVideoSize(getVideoWidth(), getVideoHeight());
    }

    public void prepareAsync(OnPreparedListener listener) throws IllegalStateException {
        this.mMediaPlayer.setOnPreparedListener(listener);
        this.mMediaPlayer.prepareAsync();
    }

    public void setOnErrorListener(OnErrorListener listener) {
        this.mMediaPlayer.setOnErrorListener(listener);
    }

    public void setOnCompletionListener(OnCompletionListener listener) {
        this.mMediaPlayer.setOnCompletionListener(listener);
    }

    public void setOnInfoListener(OnInfoListener listener) {
        this.mMediaPlayer.setOnInfoListener(listener);
    }

    public int getCurrentPosition() {
        return this.mMediaPlayer.getCurrentPosition();
    }

    public int getDuration() {
        return this.mMediaPlayer.getDuration();
    }

    public int getVideoHeight() {
        return this.mMediaPlayer.getVideoHeight();
    }

    public int getVideoWidth() {
        return this.mMediaPlayer.getVideoWidth();
    }

    public boolean isPlaying() {
        return this.mMediaPlayer.isPlaying();
    }

    public void pause() {
        this.mMediaPlayer.pause();
    }

    public void seekTo(int msec) {
        this.mMediaPlayer.seekTo(msec);
    }

    public void setLooping(boolean looping) {
        this.mMediaPlayer.setLooping(looping);
    }

    public void setVolume(float leftVolume, float rightVolume) {
        this.mMediaPlayer.setVolume(leftVolume, rightVolume);
    }

    public void start() {
        this.mMediaPlayer.start();
    }

    public void stop() {
        this.mMediaPlayer.stop();
    }

    public void reset() {
        this.mMediaPlayer.reset();
    }

    public void release() {
        reset();
        this.mMediaPlayer.release();
        this.mMediaPlayer = null;
    }
}
