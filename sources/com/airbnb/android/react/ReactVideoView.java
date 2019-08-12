package com.airbnb.android.react;

import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.webkit.CookieManager;
import android.widget.MediaController;
import com.devbrackets.android.exomedia.core.video.scale.ScaleType;
import com.devbrackets.android.exomedia.listener.OnCompletionListener;
import com.devbrackets.android.exomedia.listener.OnErrorListener;
import com.devbrackets.android.exomedia.listener.OnPreparedListener;
import com.devbrackets.android.exomedia.p306ui.widget.EMVideoView;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.yqritc.scalablevideoview.ScalableType;
import java.util.HashMap;
import java.util.Map;

public class ReactVideoView extends EMVideoView implements OnCompletionListener, OnErrorListener, OnPreparedListener, LifecycleEventListener {
    public static final String EVENT_PROP_CURRENT_TIME = "currentTime";
    public static final String EVENT_PROP_DURATION = "duration";
    public static final String EVENT_PROP_ERROR = "error";
    public static final String EVENT_PROP_FAST_FORWARD = "canPlayFastForward";
    public static final String EVENT_PROP_PLAYABLE_DURATION = "playableDuration";
    public static final String EVENT_PROP_REVERSE = "canPlayReverse";
    public static final String EVENT_PROP_SEEK_TIME = "seekTime";
    public static final String EVENT_PROP_SLOW_FORWARD = "canPlaySlowForward";
    public static final String EVENT_PROP_SLOW_REVERSE = "canPlaySlowReverse";
    public static final String EVENT_PROP_STEP_BACKWARD = "canStepBackward";
    public static final String EVENT_PROP_STEP_FORWARD = "canStepForward";
    /* access modifiers changed from: private */
    public boolean isCompleted = false;
    /* access modifiers changed from: private */
    public RCTEventEmitter mEventEmitter;
    /* access modifiers changed from: private */
    public boolean mMediaPlayerValid = false;
    private boolean mMuted = false;
    /* access modifiers changed from: private */
    public boolean mPaused = false;
    private boolean mPlayInBackground = false;
    /* access modifiers changed from: private */
    public Handler mProgressUpdateHandler = new Handler();
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
    private MediaController mediaController;
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
        this.mProgressUpdateRunnable = new Runnable() {
            public void run() {
                if (ReactVideoView.this.mMediaPlayerValid && !ReactVideoView.this.isCompleted && !ReactVideoView.this.mPaused) {
                    WritableMap event = Arguments.createMap();
                    event.putDouble(ReactVideoView.EVENT_PROP_CURRENT_TIME, ((double) ReactVideoView.this.getCurrentPosition()) / 1000.0d);
                    event.putDouble(ReactVideoView.EVENT_PROP_PLAYABLE_DURATION, ((double) ReactVideoView.this.mVideoBufferedDuration) / 1000.0d);
                    ReactVideoView.this.mEventEmitter.receiveEvent(ReactVideoView.this.getId(), Events.EVENT_PROGRESS.toString(), event);
                }
                ReactVideoView.this.mProgressUpdateHandler.postDelayed(ReactVideoView.this.mProgressUpdateRunnable, 150);
            }
        };
        this.mProgressUpdateHandler.post(this.mProgressUpdateRunnable);
        initializeListeners();
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (this.mUseNativeControls) {
            initializeMediaControllerIfNeeded();
            this.mediaController.show();
        }
        return super.onTouchEvent(event);
    }

    private void initializeListeners() {
        setOnCompletionListener(this);
        setKeepScreenOn(true);
        setOnPreparedListener(this);
        setOnErrorListener(this);
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
        stopPlayback();
        release();
    }

    public void setSrc(String uriString, String type, boolean isNetwork, boolean isAsset) {
        this.mSrcUriString = uriString;
        this.mSrcType = type;
        this.mSrcIsNetwork = isNetwork;
        this.mSrcIsAsset = isAsset;
        this.mMediaPlayerValid = false;
        this.mVideoDuration = 0;
        this.mVideoBufferedDuration = 0;
        if (isNetwork) {
            try {
                CookieManager cookieManager = CookieManager.getInstance();
                Uri parsedUrl = Uri.parse(uriString);
                String cookie = cookieManager.getCookie(parsedUrl.buildUpon().build().toString());
                Map<String, String> headers = new HashMap<>();
                if (cookie != null) {
                    headers.put("Cookie", cookie);
                }
                setVideoURI(parsedUrl);
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        } else if (isAsset) {
            if (uriString.startsWith("content://")) {
                setVideoURI(Uri.parse(uriString));
            } else {
                setVideoPath(uriString);
            }
        }
        WritableMap src = Arguments.createMap();
        src.putString("uri", uriString);
        src.putString("type", type);
        src.putBoolean("isNetwork", isNetwork);
        WritableMap event = Arguments.createMap();
        event.putMap("src", src);
        this.mEventEmitter.receiveEvent(getId(), Events.EVENT_LOAD_START.toString(), event);
        setVolume(0.0f);
    }

    public void setResizeModeModifier(ScalableType resizeMode) {
        this.mResizeMode = resizeMode;
        if (this.mMediaPlayerValid) {
            setScaleType(ScaleType.CENTER_CROP);
            invalidate();
        }
    }

    public void setRepeatModifier(boolean repeat) {
        this.mRepeat = repeat;
    }

    public void setPausedModifier(boolean paused) {
        this.mPaused = paused;
        if (this.mMediaPlayerValid) {
            if (this.mPaused) {
                if (isPlaying()) {
                    pause();
                }
            } else if (!isPlaying()) {
                start();
            }
        }
    }

    public void setMutedModifier(boolean muted) {
        this.mMuted = muted;
        if (this.mMediaPlayerValid) {
            if (this.mMuted) {
                setVolume(0.0f);
            } else {
                setVolume(this.mVolume);
            }
        }
    }

    public void setVolumeModifier(float volume) {
        this.mVolume = volume;
        setMutedModifier(this.mMuted);
    }

    public void setRateModifier(float rate) {
        this.mRate = rate;
        if (this.mMediaPlayerValid) {
            Log.e("RCTVideo", "Setting playback rate is not yet supported on Android");
        }
    }

    public void applyModifiers() {
        setMutedModifier(this.mMuted);
        setResizeModeModifier(this.mResizeMode);
        setRepeatModifier(this.mRepeat);
        setPausedModifier(this.mPaused);
    }

    public void setPlayInBackground(boolean playInBackground) {
        this.mPlayInBackground = playInBackground;
    }

    public void setControls(boolean controls) {
        this.mUseNativeControls = controls;
    }

    public void onPrepared() {
        this.mMediaPlayerValid = true;
        this.mVideoDuration = getDuration();
        WritableMap event = Arguments.createMap();
        event.putDouble("duration", ((double) this.mVideoDuration) / 1000.0d);
        event.putDouble(EVENT_PROP_CURRENT_TIME, ((double) getCurrentPosition()) / 1000.0d);
        event.putBoolean(EVENT_PROP_FAST_FORWARD, true);
        event.putBoolean(EVENT_PROP_SLOW_FORWARD, true);
        event.putBoolean(EVENT_PROP_SLOW_REVERSE, true);
        event.putBoolean(EVENT_PROP_REVERSE, true);
        event.putBoolean(EVENT_PROP_FAST_FORWARD, true);
        event.putBoolean(EVENT_PROP_STEP_BACKWARD, true);
        event.putBoolean(EVENT_PROP_STEP_FORWARD, true);
        this.mEventEmitter.receiveEvent(getId(), Events.EVENT_LOAD.toString(), event);
        applyModifiers();
    }

    /* access modifiers changed from: protected */
    public void onPlaybackEnded() {
        seekTo(0);
        if (this.mPaused && !this.mRepeat && isPlaying()) {
            pause();
        }
        if ((!this.mPaused || this.mRepeat) && !isPlaying()) {
            start();
        }
        this.mEventEmitter.receiveEvent(getId(), Events.EVENT_END.toString(), null);
    }

    public boolean onError() {
        WritableMap error = Arguments.createMap();
        WritableMap event = Arguments.createMap();
        event.putMap("error", error);
        this.mEventEmitter.receiveEvent(getId(), Events.EVENT_ERROR.toString(), event);
        return true;
    }

    public void seekTo(int msec) {
        if (this.mMediaPlayerValid) {
            WritableMap event = Arguments.createMap();
            event.putDouble(EVENT_PROP_CURRENT_TIME, ((double) getCurrentPosition()) / 1000.0d);
            event.putDouble(EVENT_PROP_SEEK_TIME, ((double) msec) / 1000.0d);
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

    public void onCompletion() {
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        this.mMediaPlayerValid = false;
        super.onDetachedFromWindow();
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        setSrc(this.mSrcUriString, this.mSrcType, this.mSrcIsNetwork, this.mSrcIsAsset);
    }

    public void onHostPause() {
        if (!this.mPlayInBackground) {
            pause();
        }
    }

    public void onHostResume() {
    }

    public void onHostDestroy() {
    }
}
