package com.brentvatne.react;

import android.annotation.SuppressLint;
import android.content.res.AssetFileDescriptor;
import android.graphics.Matrix;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnInfoListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.webkit.CookieManager;
import android.widget.MediaController;
import android.widget.MediaController.MediaPlayerControl;
import com.airbnb.android.core.communitycommitment.CommunityCommitmentManager;
import com.airbnb.android.lib.fragments.GenderSelectionFragment;
import com.android.vending.expansion.zipfile.APKExpansionSupport;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.yqritc.scalablevideoview.ScalableType;
import com.yqritc.scalablevideoview.ScalableVideoView;
import com.yqritc.scalablevideoview.ScaleManager;
import com.yqritc.scalablevideoview.Size;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@SuppressLint({"ViewConstructor"})
public class ReactVideoView extends ScalableVideoView implements OnBufferingUpdateListener, OnCompletionListener, OnErrorListener, OnInfoListener, OnPreparedListener, MediaPlayerControl, LifecycleEventListener {
    /* access modifiers changed from: private */
    public boolean isCompleted = false;
    /* access modifiers changed from: private */
    public boolean mActiveStatePauseStatus = false;
    private boolean mActiveStatePauseStatusInitialized = false;
    /* access modifiers changed from: private */
    public RCTEventEmitter mEventEmitter;
    private int mMainVer = 0;
    /* access modifiers changed from: private */
    public boolean mMediaPlayerValid = false;
    private boolean mMuted = false;
    private int mPatchVer = 0;
    /* access modifiers changed from: private */
    public boolean mPaused = false;
    private boolean mPlayInBackground = false;
    /* access modifiers changed from: private */
    public Handler mProgressUpdateHandler = new Handler();
    /* access modifiers changed from: private */
    public float mProgressUpdateInterval = 250.0f;
    /* access modifiers changed from: private */
    public Runnable mProgressUpdateRunnable = null;
    private float mRate = 1.0f;
    private boolean mRepeat = false;
    private ScalableType mResizeMode = ScalableType.LEFT_TOP;
    private boolean mSrcIsAsset = false;
    private boolean mSrcIsNetwork = false;
    private String mSrcType = "mp4";
    private String mSrcUriString = null;
    private ThemedReactContext mThemedReactContext;
    private boolean mUseNativeControls = false;
    /* access modifiers changed from: private */
    public int mVideoBufferedDuration = 0;
    private int mVideoDuration = 0;
    private float mVolume = 1.0f;
    /* access modifiers changed from: private */
    public MediaController mediaController;
    private Handler videoControlHandler = new Handler();

    public enum Events {
        EVENT_LOAD_START("onVideoLoadStart"),
        EVENT_LOAD("onVideoLoad"),
        EVENT_ERROR("onVideoError"),
        EVENT_PROGRESS("onVideoProgress"),
        EVENT_SEEK("onVideoSeek"),
        EVENT_END("onVideoEnd"),
        EVENT_STALLED("onPlaybackStalled"),
        EVENT_RESUME("onPlaybackResume"),
        EVENT_READY_FOR_DISPLAY("onReadyForDisplay");
        
        private final String mName;

        private Events(String name) {
            this.mName = name;
        }

        public String toString() {
            return this.mName;
        }
    }

    public ReactVideoView(ThemedReactContext themedReactContext) {
        super(themedReactContext);
        this.mThemedReactContext = themedReactContext;
        this.mEventEmitter = (RCTEventEmitter) themedReactContext.getJSModule(RCTEventEmitter.class);
        themedReactContext.addLifecycleEventListener(this);
        initializeMediaPlayerIfNeeded();
        setSurfaceTextureListener(this);
        this.mProgressUpdateRunnable = new Runnable() {
            public void run() {
                if (ReactVideoView.this.mMediaPlayerValid && !ReactVideoView.this.isCompleted && !ReactVideoView.this.mPaused) {
                    WritableMap event = Arguments.createMap();
                    event.putDouble(com.airbnb.android.react.ReactVideoView.EVENT_PROP_CURRENT_TIME, ((double) ReactVideoView.this.mMediaPlayer.getCurrentPosition()) / 1000.0d);
                    event.putDouble(com.airbnb.android.react.ReactVideoView.EVENT_PROP_PLAYABLE_DURATION, ((double) ReactVideoView.this.mVideoBufferedDuration) / 1000.0d);
                    ReactVideoView.this.mEventEmitter.receiveEvent(ReactVideoView.this.getId(), Events.EVENT_PROGRESS.toString(), event);
                    ReactVideoView.this.mProgressUpdateHandler.postDelayed(ReactVideoView.this.mProgressUpdateRunnable, (long) Math.round(ReactVideoView.this.mProgressUpdateInterval));
                }
            }
        };
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (this.mUseNativeControls) {
            initializeMediaControllerIfNeeded();
            this.mediaController.show();
        }
        return super.onTouchEvent(event);
    }

    /* access modifiers changed from: protected */
    @SuppressLint({"DrawAllocation"})
    public void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (changed && this.mMediaPlayerValid) {
            int videoWidth = getVideoWidth();
            int videoHeight = getVideoHeight();
            if (videoWidth != 0 && videoHeight != 0) {
                Matrix matrix = new ScaleManager(new Size(getWidth(), getHeight()), new Size(videoWidth, videoHeight)).getScaleMatrix(this.mScalableType);
                if (matrix != null) {
                    setTransform(matrix);
                }
            }
        }
    }

    private void initializeMediaPlayerIfNeeded() {
        if (this.mMediaPlayer == null) {
            this.mMediaPlayerValid = false;
            this.mMediaPlayer = new MediaPlayer();
            this.mMediaPlayer.setScreenOnWhilePlaying(true);
            this.mMediaPlayer.setOnVideoSizeChangedListener(this);
            this.mMediaPlayer.setOnErrorListener(this);
            this.mMediaPlayer.setOnPreparedListener(this);
            this.mMediaPlayer.setOnBufferingUpdateListener(this);
            this.mMediaPlayer.setOnCompletionListener(this);
            this.mMediaPlayer.setOnInfoListener(this);
        }
    }

    private void initializeMediaControllerIfNeeded() {
        if (this.mediaController == null) {
            this.mediaController = new MediaController(getContext());
        }
    }

    public void cleanupMediaPlayerResources() {
        if (this.mediaController != null) {
            this.mediaController.hide();
        }
        if (this.mMediaPlayer != null) {
            this.mMediaPlayerValid = false;
            release();
        }
    }

    public void setSrc(String uriString, String type, boolean isNetwork, boolean isAsset) {
        setSrc(uriString, type, isNetwork, isAsset, 0, 0);
    }

    public void setSrc(String uriString, String type, boolean isNetwork, boolean isAsset, int expansionMainVersion, int expansionPatchVersion) {
        this.mSrcUriString = uriString;
        this.mSrcType = type;
        this.mSrcIsNetwork = isNetwork;
        this.mSrcIsAsset = isAsset;
        this.mMainVer = expansionMainVersion;
        this.mPatchVer = expansionPatchVersion;
        this.mMediaPlayerValid = false;
        this.mVideoDuration = 0;
        this.mVideoBufferedDuration = 0;
        initializeMediaPlayerIfNeeded();
        this.mMediaPlayer.reset();
        if (isNetwork) {
            try {
                String cookie = CookieManager.getInstance().getCookie(Uri.parse(uriString).buildUpon().build().toString());
                Map<String, String> headers = new HashMap<>();
                if (cookie != null) {
                    headers.put("Cookie", cookie);
                }
                setDataSource(uriString);
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        } else if (!isAsset) {
            AssetFileDescriptor fd = null;
            if (this.mMainVer > 0) {
                try {
                    fd = APKExpansionSupport.getAPKExpansionZipFile(this.mThemedReactContext, this.mMainVer, this.mPatchVer).getAssetFileDescriptor(uriString.replace(".mp4", "") + ".mp4");
                } catch (IOException e2) {
                    e2.printStackTrace();
                } catch (NullPointerException e3) {
                    e3.printStackTrace();
                }
            }
            if (fd == null) {
                int identifier = this.mThemedReactContext.getResources().getIdentifier(uriString, "drawable", this.mThemedReactContext.getPackageName());
                if (identifier == 0) {
                    identifier = this.mThemedReactContext.getResources().getIdentifier(uriString, "raw", this.mThemedReactContext.getPackageName());
                }
                setRawData(identifier);
            } else {
                setDataSource(fd.getFileDescriptor(), fd.getStartOffset(), fd.getLength());
            }
        } else if (uriString.startsWith("content://")) {
            Uri parsedUrl = Uri.parse(uriString);
            setDataSource(this.mThemedReactContext, parsedUrl);
        } else {
            setDataSource(uriString);
        }
        WritableMap src = Arguments.createMap();
        src.putString("uri", uriString);
        src.putString("type", type);
        src.putBoolean("isNetwork", isNetwork);
        if (this.mMainVer > 0) {
            src.putInt(ReactVideoViewManager.PROP_SRC_MAINVER, this.mMainVer);
            if (this.mPatchVer > 0) {
                src.putInt(ReactVideoViewManager.PROP_SRC_PATCHVER, this.mPatchVer);
            }
        }
        WritableMap event = Arguments.createMap();
        event.putMap("src", src);
        this.mEventEmitter.receiveEvent(getId(), Events.EVENT_LOAD_START.toString(), event);
        try {
            prepareAsync(this);
        } catch (Exception e4) {
            e4.printStackTrace();
        }
    }

    public void setResizeModeModifier(ScalableType resizeMode) {
        this.mResizeMode = resizeMode;
        if (this.mMediaPlayerValid) {
            setScalableType(resizeMode);
            invalidate();
        }
    }

    public void setRepeatModifier(boolean repeat) {
        this.mRepeat = repeat;
        if (this.mMediaPlayerValid) {
            setLooping(repeat);
        }
    }

    public void setPausedModifier(boolean paused) {
        this.mPaused = paused;
        if (!this.mActiveStatePauseStatusInitialized) {
            this.mActiveStatePauseStatus = this.mPaused;
            this.mActiveStatePauseStatusInitialized = true;
        }
        if (this.mMediaPlayerValid) {
            if (this.mPaused) {
                if (this.mMediaPlayer.isPlaying()) {
                    pause();
                }
            } else if (!this.mMediaPlayer.isPlaying()) {
                start();
                this.mProgressUpdateHandler.post(this.mProgressUpdateRunnable);
            }
        }
    }

    public void setMutedModifier(boolean muted) {
        this.mMuted = muted;
        if (this.mMediaPlayerValid) {
            if (this.mMuted) {
                setVolume(0.0f, 0.0f);
            } else {
                setVolume(this.mVolume, this.mVolume);
            }
        }
    }

    public void setVolumeModifier(float volume) {
        this.mVolume = volume;
        setMutedModifier(this.mMuted);
    }

    public void setProgressUpdateInterval(float progressUpdateInterval) {
        this.mProgressUpdateInterval = progressUpdateInterval;
    }

    public void setRateModifier(float rate) {
        this.mRate = rate;
        if (this.mMediaPlayerValid) {
            Log.e("RCTVideo", "Setting playback rate is not yet supported on Android");
        }
    }

    public void applyModifiers() {
        setResizeModeModifier(this.mResizeMode);
        setRepeatModifier(this.mRepeat);
        setPausedModifier(this.mPaused);
        setMutedModifier(this.mMuted);
        setProgressUpdateInterval(this.mProgressUpdateInterval);
    }

    public void setPlayInBackground(boolean playInBackground) {
        this.mPlayInBackground = playInBackground;
    }

    public void setControls(boolean controls) {
        this.mUseNativeControls = controls;
    }

    public void onPrepared(MediaPlayer mp) {
        this.mMediaPlayerValid = true;
        this.mVideoDuration = mp.getDuration();
        WritableMap naturalSize = Arguments.createMap();
        naturalSize.putInt("width", mp.getVideoWidth());
        naturalSize.putInt("height", mp.getVideoHeight());
        if (mp.getVideoWidth() > mp.getVideoHeight()) {
            naturalSize.putString("orientation", "landscape");
        } else {
            naturalSize.putString("orientation", "portrait");
        }
        WritableMap event = Arguments.createMap();
        event.putDouble("duration", ((double) this.mVideoDuration) / 1000.0d);
        event.putDouble(com.airbnb.android.react.ReactVideoView.EVENT_PROP_CURRENT_TIME, ((double) mp.getCurrentPosition()) / 1000.0d);
        event.putMap("naturalSize", naturalSize);
        event.putBoolean(com.airbnb.android.react.ReactVideoView.EVENT_PROP_FAST_FORWARD, true);
        event.putBoolean(com.airbnb.android.react.ReactVideoView.EVENT_PROP_SLOW_FORWARD, true);
        event.putBoolean(com.airbnb.android.react.ReactVideoView.EVENT_PROP_SLOW_REVERSE, true);
        event.putBoolean(com.airbnb.android.react.ReactVideoView.EVENT_PROP_REVERSE, true);
        event.putBoolean(com.airbnb.android.react.ReactVideoView.EVENT_PROP_FAST_FORWARD, true);
        event.putBoolean(com.airbnb.android.react.ReactVideoView.EVENT_PROP_STEP_BACKWARD, true);
        event.putBoolean(com.airbnb.android.react.ReactVideoView.EVENT_PROP_STEP_FORWARD, true);
        this.mEventEmitter.receiveEvent(getId(), Events.EVENT_LOAD.toString(), event);
        applyModifiers();
        if (this.mUseNativeControls) {
            initializeMediaControllerIfNeeded();
            this.mediaController.setMediaPlayer(this);
            this.mediaController.setAnchorView(this);
            this.videoControlHandler.post(new Runnable() {
                public void run() {
                    ReactVideoView.this.mediaController.setEnabled(true);
                    ReactVideoView.this.mediaController.show();
                }
            });
        }
    }

    public boolean onError(MediaPlayer mp, int what, int extra) {
        WritableMap error = Arguments.createMap();
        error.putInt("what", what);
        error.putInt("extra", extra);
        WritableMap event = Arguments.createMap();
        event.putMap("error", error);
        this.mEventEmitter.receiveEvent(getId(), Events.EVENT_ERROR.toString(), event);
        return true;
    }

    public boolean onInfo(MediaPlayer mp, int what, int extra) {
        switch (what) {
            case 3:
                this.mEventEmitter.receiveEvent(getId(), Events.EVENT_READY_FOR_DISPLAY.toString(), Arguments.createMap());
                break;
            case GenderSelectionFragment.REQUEST_CODE_GENDER /*701*/:
                this.mEventEmitter.receiveEvent(getId(), Events.EVENT_STALLED.toString(), Arguments.createMap());
                break;
            case CommunityCommitmentManager.REQUEST_CODE_ACCEPT_COMMUNITY_COMMITMENT /*702*/:
                this.mEventEmitter.receiveEvent(getId(), Events.EVENT_RESUME.toString(), Arguments.createMap());
                break;
        }
        return false;
    }

    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        this.mVideoBufferedDuration = (int) Math.round(((double) (this.mVideoDuration * percent)) / 100.0d);
    }

    public void seekTo(int msec) {
        if (this.mMediaPlayerValid) {
            WritableMap event = Arguments.createMap();
            event.putDouble(com.airbnb.android.react.ReactVideoView.EVENT_PROP_CURRENT_TIME, ((double) getCurrentPosition()) / 1000.0d);
            event.putDouble(com.airbnb.android.react.ReactVideoView.EVENT_PROP_SEEK_TIME, ((double) msec) / 1000.0d);
            this.mEventEmitter.receiveEvent(getId(), Events.EVENT_SEEK.toString(), event);
            super.seekTo(msec);
            if (this.isCompleted && this.mVideoDuration != 0 && msec < this.mVideoDuration) {
                this.isCompleted = false;
            }
        }
    }

    public int getBufferPercentage() {
        return 0;
    }

    public boolean canPause() {
        return true;
    }

    public boolean canSeekBackward() {
        return true;
    }

    public boolean canSeekForward() {
        return true;
    }

    public int getAudioSessionId() {
        return 0;
    }

    public void onCompletion(MediaPlayer mp) {
        this.isCompleted = true;
        this.mEventEmitter.receiveEvent(getId(), Events.EVENT_END.toString(), null);
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        this.mMediaPlayerValid = false;
        super.onDetachedFromWindow();
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mMainVer > 0) {
            setSrc(this.mSrcUriString, this.mSrcType, this.mSrcIsNetwork, this.mSrcIsAsset, this.mMainVer, this.mPatchVer);
            return;
        }
        setSrc(this.mSrcUriString, this.mSrcType, this.mSrcIsNetwork, this.mSrcIsAsset);
    }

    public void onHostPause() {
        if (this.mMediaPlayer != null && !this.mPlayInBackground) {
            this.mActiveStatePauseStatus = this.mPaused;
            setPausedModifier(true);
        }
    }

    public void onHostResume() {
        if (this.mMediaPlayer != null && !this.mPlayInBackground) {
            new Handler().post(new Runnable() {
                public void run() {
                    ReactVideoView.this.setPausedModifier(ReactVideoView.this.mActiveStatePauseStatus);
                }
            });
        }
    }

    public void onHostDestroy() {
    }
}
