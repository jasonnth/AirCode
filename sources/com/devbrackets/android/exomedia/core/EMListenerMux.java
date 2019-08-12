package com.devbrackets.android.exomedia.core;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnSeekCompleteListener;
import android.os.Handler;
import com.devbrackets.android.exomedia.core.exoplayer.EMExoPlayer;
import com.devbrackets.android.exomedia.core.listener.ExoPlayerListener;
import com.devbrackets.android.exomedia.core.listener.Id3MetadataListener;
import com.devbrackets.android.exomedia.core.video.ResizingTextureView;
import com.devbrackets.android.exomedia.listener.OnBufferUpdateListener;
import com.devbrackets.android.exomedia.listener.OnSeekCompletionListener;
import com.google.android.exoplayer.metadata.id3.Id3Frame;
import java.lang.ref.WeakReference;
import java.util.List;

public class EMListenerMux implements OnBufferingUpdateListener, OnCompletionListener, OnErrorListener, OnPreparedListener, OnSeekCompleteListener, ExoPlayerListener, Id3MetadataListener, OnBufferUpdateListener {
    private OnBufferUpdateListener bufferUpdateListener;
    private boolean clearRequested = false;
    private WeakReference<ResizingTextureView> clearTextureView = new WeakReference<>(null);
    /* access modifiers changed from: private */
    public com.devbrackets.android.exomedia.listener.OnCompletionListener completionListener;
    private Handler delayedHandler = new Handler();
    private com.devbrackets.android.exomedia.listener.OnErrorListener errorListener;
    private Id3MetadataListener id3MetadataListener;
    private EMListenerMuxNotifier muxNotifier;
    private boolean notifiedCompleted = false;
    private boolean notifiedPrepared = false;
    private com.devbrackets.android.exomedia.listener.OnPreparedListener preparedListener;
    private OnSeekCompletionListener seekCompletionListener;

    public static abstract class EMListenerMuxNotifier {
        public abstract void onExoPlayerError(EMExoPlayer eMExoPlayer, Exception exc);

        public abstract void onMediaPlaybackEnded();

        public abstract boolean shouldNotifyCompletion(long j);

        public void onSeekComplete() {
        }

        public void onBufferUpdated(int percent) {
        }

        public void onVideoSizeChanged(int width, int height, int unAppliedRotationDegrees, float pixelWidthHeightRatio) {
        }

        public void onPrepared() {
        }

        public void onPreviewImageStateChanged(boolean toVisible) {
        }
    }

    public EMListenerMux(EMListenerMuxNotifier notifier) {
        this.muxNotifier = notifier;
    }

    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        onBufferingUpdate(percent);
    }

    public void onCompletion(MediaPlayer mp) {
        if (this.completionListener != null) {
            this.completionListener.onCompletion();
        }
    }

    public boolean onError(MediaPlayer mp, int what, int extra) {
        return notifyErrorListener();
    }

    public void onSeekComplete(MediaPlayer mp) {
        if (this.seekCompletionListener != null) {
            this.seekCompletionListener.onSeekComplete();
        }
    }

    public void onPrepared(MediaPlayer mp) {
        notifyPreparedListener();
    }

    public void onError(EMExoPlayer emExoPlayer, Exception e) {
        this.muxNotifier.onMediaPlaybackEnded();
        this.muxNotifier.onExoPlayerError(emExoPlayer, e);
        notifyErrorListener();
    }

    public void onStateChanged(boolean playWhenReady, int playbackState) {
        if (playbackState == 5) {
            this.muxNotifier.onMediaPlaybackEnded();
            if (!this.notifiedCompleted) {
                notifyCompletionListener();
            }
        } else if (playbackState == 4 && !this.notifiedPrepared) {
            notifyPreparedListener();
        }
        if (playbackState == 4 && playWhenReady) {
            this.muxNotifier.onPreviewImageStateChanged(false);
        }
        if (playbackState == 1 && this.clearRequested) {
            this.clearRequested = false;
            ResizingTextureView textureView = (ResizingTextureView) this.clearTextureView.get();
            if (textureView != null) {
                textureView.clearSurface();
                this.clearTextureView = new WeakReference<>(null);
            }
        }
    }

    public void onSeekComplete() {
        this.muxNotifier.onSeekComplete();
        if (this.seekCompletionListener != null) {
            this.seekCompletionListener.onSeekComplete();
        }
    }

    public void onBufferingUpdate(int percent) {
        this.muxNotifier.onBufferUpdated(percent);
        if (this.bufferUpdateListener != null) {
            this.bufferUpdateListener.onBufferingUpdate(percent);
        }
    }

    public void onId3Metadata(List<Id3Frame> metadata) {
        if (this.id3MetadataListener != null) {
            this.id3MetadataListener.onId3Metadata(metadata);
        }
    }

    public void onVideoSizeChanged(int width, int height, int unAppliedRotationDegrees, float pixelWidthHeightRatio) {
        this.muxNotifier.onVideoSizeChanged(width, height, unAppliedRotationDegrees, pixelWidthHeightRatio);
    }

    public void clearSurfaceWhenReady(ResizingTextureView textureView) {
        this.clearRequested = true;
        this.clearTextureView = new WeakReference<>(textureView);
    }

    public void setOnPreparedListener(com.devbrackets.android.exomedia.listener.OnPreparedListener listener) {
        this.preparedListener = listener;
    }

    public void setOnCompletionListener(com.devbrackets.android.exomedia.listener.OnCompletionListener listener) {
        this.completionListener = listener;
    }

    public void setOnBufferUpdateListener(OnBufferUpdateListener listener) {
        this.bufferUpdateListener = listener;
    }

    public void setOnSeekCompletionListener(OnSeekCompletionListener listener) {
        this.seekCompletionListener = listener;
    }

    public void setOnErrorListener(com.devbrackets.android.exomedia.listener.OnErrorListener listener) {
        this.errorListener = listener;
    }

    public void setId3MetadataListener(Id3MetadataListener listener) {
        this.id3MetadataListener = listener;
    }

    public void setNotifiedPrepared(boolean wasNotified) {
        this.notifiedPrepared = wasNotified;
        this.muxNotifier.onPreviewImageStateChanged(true);
    }

    public boolean isPrepared() {
        return this.notifiedPrepared;
    }

    public void setNotifiedCompleted(boolean wasNotified) {
        this.notifiedCompleted = wasNotified;
    }

    private boolean notifyErrorListener() {
        return this.errorListener != null && this.errorListener.onError();
    }

    private void notifyPreparedListener() {
        this.notifiedPrepared = true;
        this.delayedHandler.post(new Runnable() {
            public void run() {
                EMListenerMux.this.performPreparedHandlerNotification();
            }
        });
    }

    /* access modifiers changed from: private */
    public void performPreparedHandlerNotification() {
        this.muxNotifier.onPrepared();
        if (this.preparedListener != null) {
            this.preparedListener.onPrepared();
        }
    }

    private void notifyCompletionListener() {
        if (this.muxNotifier.shouldNotifyCompletion(1000)) {
            this.notifiedCompleted = true;
            this.delayedHandler.post(new Runnable() {
                public void run() {
                    if (EMListenerMux.this.completionListener != null) {
                        EMListenerMux.this.completionListener.onCompletion();
                    }
                }
            });
        }
    }
}
