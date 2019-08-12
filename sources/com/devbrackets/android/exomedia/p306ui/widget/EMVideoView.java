package com.devbrackets.android.exomedia.p306ui.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.devbrackets.android.exomedia.R;
import com.devbrackets.android.exomedia.core.EMListenerMux;
import com.devbrackets.android.exomedia.core.EMListenerMux.EMListenerMuxNotifier;
import com.devbrackets.android.exomedia.core.api.VideoViewApi;
import com.devbrackets.android.exomedia.core.builder.RenderBuilder;
import com.devbrackets.android.exomedia.core.exoplayer.EMExoPlayer;
import com.devbrackets.android.exomedia.core.listener.Id3MetadataListener;
import com.devbrackets.android.exomedia.core.video.scale.ScaleType;
import com.devbrackets.android.exomedia.listener.OnBufferUpdateListener;
import com.devbrackets.android.exomedia.listener.OnCompletionListener;
import com.devbrackets.android.exomedia.listener.OnErrorListener;
import com.devbrackets.android.exomedia.listener.OnPreparedListener;
import com.devbrackets.android.exomedia.listener.OnSeekCompletionListener;
import com.devbrackets.android.exomedia.util.DeviceUtil;
import com.devbrackets.android.exomedia.util.Repeater;
import com.devbrackets.android.exomedia.util.StopWatch;
import com.google.android.exoplayer.MediaFormat;
import java.util.List;
import java.util.Map;

/* renamed from: com.devbrackets.android.exomedia.ui.widget.EMVideoView */
public class EMVideoView extends RelativeLayout {
    private static final String TAG = EMVideoView.class.getSimpleName();
    protected DeviceUtil deviceUtil = new DeviceUtil();
    protected EMListenerMux listenerMux;
    protected MuxNotifier muxNotifier = new MuxNotifier();
    protected int overriddenDuration = -1;
    protected StopWatch overriddenPositionStopWatch = new StopWatch();
    protected boolean overridePosition = false;
    protected Repeater pollRepeater = new Repeater();
    protected int positionOffset = 0;
    protected ImageView previewImageView;
    protected boolean releaseOnDetachFromWindow = true;
    protected VideoControls videoControls;
    protected Uri videoUri;
    protected VideoViewApi videoViewImpl;

    /* renamed from: com.devbrackets.android.exomedia.ui.widget.EMVideoView$MuxNotifier */
    protected class MuxNotifier extends EMListenerMuxNotifier {
        protected MuxNotifier() {
        }

        public boolean shouldNotifyCompletion(long endLeeway) {
            return ((long) EMVideoView.this.getCurrentPosition()) + endLeeway >= ((long) EMVideoView.this.getDuration());
        }

        public void onExoPlayerError(EMExoPlayer emExoPlayer, Exception e) {
            EMVideoView.this.stopPlayback();
            if (emExoPlayer != null) {
                emExoPlayer.forcePrepare();
            }
        }

        public void onMediaPlaybackEnded() {
            EMVideoView.this.setKeepScreenOn(false);
            EMVideoView.this.onPlaybackEnded();
        }

        public void onSeekComplete() {
            if (EMVideoView.this.videoControls != null) {
                EMVideoView.this.videoControls.finishLoading();
            }
        }

        public void onVideoSizeChanged(int width, int height, int unAppliedRotationDegrees, float pixelWidthHeightRatio) {
            EMVideoView.this.videoViewImpl.setVideoRotation(unAppliedRotationDegrees, false);
            EMVideoView.this.videoViewImpl.onVideoSizeChanged(width, height);
        }

        public void onPrepared() {
            if (EMVideoView.this.videoControls != null) {
                EMVideoView.this.videoControls.setDuration((long) EMVideoView.this.getDuration());
                EMVideoView.this.videoControls.finishLoading();
            }
        }

        public void onPreviewImageStateChanged(boolean toVisible) {
            if (EMVideoView.this.previewImageView != null) {
                EMVideoView.this.previewImageView.setVisibility(toVisible ? 0 : 8);
            }
        }
    }

    /* renamed from: com.devbrackets.android.exomedia.ui.widget.EMVideoView$TouchListener */
    protected class TouchListener extends SimpleOnGestureListener implements OnTouchListener {
        protected GestureDetector gestureDetector;

        public TouchListener(Context context) {
            this.gestureDetector = new GestureDetector(context, this);
        }

        public boolean onTouch(View v, MotionEvent event) {
            this.gestureDetector.onTouchEvent(event);
            return true;
        }

        public boolean onSingleTapConfirmed(MotionEvent e) {
            if (EMVideoView.this.videoControls != null) {
                EMVideoView.this.videoControls.show();
                if (EMVideoView.this.isPlaying()) {
                    EMVideoView.this.videoControls.hideDelayed(2000);
                }
            }
            return true;
        }
    }

    public EMVideoView(Context context) {
        super(context);
        setup(context, null);
    }

    public EMVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setup(context, attrs);
    }

    @TargetApi(11)
    public EMVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setup(context, attrs);
    }

    @TargetApi(21)
    public EMVideoView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setup(context, attrs);
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (!isInEditMode() && this.releaseOnDetachFromWindow) {
            release();
        }
    }

    public void setOnTouchListener(OnTouchListener listener) {
        this.videoViewImpl.setOnTouchListener(listener);
        super.setOnTouchListener(listener);
    }

    public void setReleaseOnDetachFromWindow(boolean releaseOnDetach) {
        this.releaseOnDetachFromWindow = releaseOnDetach;
    }

    public void release() {
        this.videoControls = null;
        stopPlayback();
        this.overriddenPositionStopWatch.stop();
        this.videoViewImpl.release();
    }

    public void setPreviewImage(Drawable drawable) {
        if (this.previewImageView != null) {
            this.previewImageView.setImageDrawable(drawable);
        }
    }

    public void setPreviewImage(int resourceId) {
        if (this.previewImageView != null) {
            this.previewImageView.setImageResource(resourceId);
        }
    }

    public void setPreviewImage(Bitmap bitmap) {
        if (this.previewImageView != null) {
            this.previewImageView.setImageBitmap(bitmap);
        }
    }

    public void setPreviewImage(Uri uri) {
        if (this.previewImageView != null) {
            this.previewImageView.setImageURI(uri);
        }
    }

    public ImageView getPreviewImageView() {
        return this.previewImageView;
    }

    public void setControls(VideoControls controls) {
        if (!(this.videoControls == null || this.videoControls == controls)) {
            removeView(this.videoControls);
        }
        if (controls != null) {
            this.videoControls = controls;
            controls.setVideoView(this);
            addView(controls);
        }
        TouchListener listener = new TouchListener(getContext());
        if (this.videoControls == null) {
            listener = null;
        }
        setOnTouchListener(listener);
    }

    public void showControls() {
        if (this.videoControls != null) {
            this.videoControls.show();
            if (isPlaying()) {
                this.videoControls.hideDelayed(2000);
            }
        }
    }

    public VideoControls getVideoControls() {
        return this.videoControls;
    }

    public void setVideoURI(Uri uri) {
        this.videoUri = uri;
        this.videoViewImpl.setVideoUri(uri);
        if (this.videoControls != null) {
            this.videoControls.showLoading(true);
        }
    }

    public void setVideoURI(Uri uri, RenderBuilder renderBuilder) {
        this.videoUri = uri;
        this.videoViewImpl.setVideoUri(uri, renderBuilder);
        if (this.videoControls != null) {
            this.videoControls.showLoading(true);
        }
    }

    public void setVideoPath(String path) {
        setVideoURI(Uri.parse(path));
    }

    public Uri getVideoUri() {
        return this.videoUri;
    }

    public boolean setVolume(float volume) {
        return this.videoViewImpl.setVolume(volume);
    }

    public void reset() {
        stopPlayback();
        setVideoURI(null);
    }

    public void seekTo(int milliSeconds) {
        if (this.videoControls != null) {
            this.videoControls.showLoading(false);
        }
        this.videoViewImpl.seekTo(milliSeconds);
    }

    public boolean isPlaying() {
        return this.videoViewImpl.isPlaying();
    }

    public void start() {
        this.videoViewImpl.start();
        setKeepScreenOn(true);
        if (this.videoControls != null) {
            this.videoControls.updatePlaybackState(true);
        }
    }

    public void pause() {
        this.videoViewImpl.pause();
        setKeepScreenOn(false);
        if (this.videoControls != null) {
            this.videoControls.updatePlaybackState(false);
        }
    }

    public void stopPlayback() {
        this.videoViewImpl.stopPlayback();
        setKeepScreenOn(false);
        if (this.videoControls != null) {
            this.videoControls.updatePlaybackState(false);
        }
    }

    public boolean restart() {
        if (this.videoUri == null || !this.videoViewImpl.restart()) {
            return false;
        }
        if (this.videoControls != null) {
            this.videoControls.showLoading(true);
        }
        return true;
    }

    public void suspend() {
        this.videoViewImpl.suspend();
        setKeepScreenOn(false);
        if (this.videoControls != null) {
            this.videoControls.updatePlaybackState(false);
        }
    }

    public int getDuration() {
        if (this.overriddenDuration >= 0) {
            return this.overriddenDuration;
        }
        return this.videoViewImpl.getDuration();
    }

    public void overrideDuration(int duration) {
        this.overriddenDuration = duration;
    }

    public int getCurrentPosition() {
        if (this.overridePosition) {
            return this.positionOffset + this.overriddenPositionStopWatch.getTimeInt();
        }
        return this.positionOffset + this.videoViewImpl.getCurrentPosition();
    }

    public void setPositionOffset(int offset) {
        this.positionOffset = offset;
    }

    public void restartOverridePosition() {
        this.overriddenPositionStopWatch.reset();
    }

    public void overridePosition(boolean override) {
        if (override) {
            this.overriddenPositionStopWatch.start();
        } else {
            this.overriddenPositionStopWatch.stop();
        }
        this.overridePosition = override;
    }

    public int getBufferPercentage() {
        return this.videoViewImpl.getBufferedPercent();
    }

    public boolean trackSelectionAvailable() {
        return this.videoViewImpl.trackSelectionAvailable();
    }

    public void setTrack(int trackType, int trackIndex) {
        this.videoViewImpl.setTrack(trackType, trackIndex);
    }

    public Map<Integer, List<MediaFormat>> getAvailableTracks() {
        return this.videoViewImpl.getAvailableTracks();
    }

    public void setScaleType(ScaleType scaleType) {
        this.videoViewImpl.setScaleType(scaleType);
    }

    public void setMeasureBasedOnAspectRatioEnabled(boolean measureBasedOnAspectRatioEnabled) {
        this.videoViewImpl.setMeasureBasedOnAspectRatioEnabled(measureBasedOnAspectRatioEnabled);
    }

    public void setVideoRotation(int rotation) {
        this.videoViewImpl.setVideoRotation(rotation, true);
    }

    public void setOnPreparedListener(OnPreparedListener listener) {
        this.listenerMux.setOnPreparedListener(listener);
    }

    public void setOnCompletionListener(OnCompletionListener listener) {
        this.listenerMux.setOnCompletionListener(listener);
    }

    public void setOnBufferUpdateListener(OnBufferUpdateListener listener) {
        this.listenerMux.setOnBufferUpdateListener(listener);
    }

    public void setOnSeekCompletionListener(OnSeekCompletionListener listener) {
        this.listenerMux.setOnSeekCompletionListener(listener);
    }

    public void setOnErrorListener(OnErrorListener listener) {
        this.listenerMux.setOnErrorListener(listener);
    }

    public void setId3MetadataListener(Id3MetadataListener listener) {
        this.listenerMux.setId3MetadataListener(listener);
    }

    /* access modifiers changed from: protected */
    public void setup(Context context, AttributeSet attrs) {
        if (!isInEditMode()) {
            initView(context, attrs);
            readAttributes(context, attrs);
        }
    }

    /* access modifiers changed from: protected */
    public void readAttributes(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.EMVideoView);
            if (typedArray != null) {
                if (typedArray.getBoolean(R.styleable.EMVideoView_useDefaultControls, false)) {
                    setControls(this.deviceUtil.isDeviceTV(getContext()) ? new VideoControlsLeanback(getContext()) : new VideoControlsMobile(getContext()));
                }
                typedArray.recycle();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void initView(Context context, AttributeSet attrs) {
        inflateVideoView(context, attrs);
        this.previewImageView = (ImageView) findViewById(R.id.exomedia_video_preview_image);
        this.videoViewImpl = (VideoViewApi) findViewById(R.id.exomedia_video_view);
        this.muxNotifier = new MuxNotifier();
        this.listenerMux = new EMListenerMux(this.muxNotifier);
        this.videoViewImpl.setListenerMux(this.listenerMux);
    }

    /* access modifiers changed from: protected */
    public void inflateVideoView(Context context, AttributeSet attrs) {
        View.inflate(context, R.layout.exomedia_video_view_layout, this);
        ViewStub videoViewStub = (ViewStub) findViewById(R.id.video_view_api_impl_stub);
        videoViewStub.setLayoutResource(getVideoViewApiImplementation(context, attrs));
        videoViewStub.inflate();
    }

    /* access modifiers changed from: protected */
    public int getVideoViewApiImplementation(Context context, AttributeSet attrs) {
        boolean useLegacy = !this.deviceUtil.supportsExoPlayer(context);
        int defaultVideoViewApiImplRes = useLegacy ? R.layout.exomedia_default_native_video_view : R.layout.exomedia_default_exo_video_view;
        if (attrs == null) {
            return defaultVideoViewApiImplRes;
        }
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.EMVideoView);
        if (typedArray == null) {
            return defaultVideoViewApiImplRes;
        }
        int videoViewApiImplRes = typedArray.getResourceId(useLegacy ? R.styleable.EMVideoView_videoViewApiImplLegacy : R.styleable.EMVideoView_videoViewApiImpl, defaultVideoViewApiImplRes);
        typedArray.recycle();
        return videoViewApiImplRes;
    }

    /* access modifiers changed from: protected */
    public void onPlaybackEnded() {
        stopPlayback();
        this.pollRepeater.stop();
    }
}
