package com.devbrackets.android.exomedia.p306ui.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import com.devbrackets.android.exomedia.R;
import com.devbrackets.android.exomedia.p306ui.animation.BottomViewHideShowAnimation;
import com.devbrackets.android.exomedia.util.EMResourceUtil;
import com.devbrackets.android.exomedia.util.TimeFormatUtil;
import org.spongycastle.asn1.eac.CertificateBody;
import org.spongycastle.asn1.eac.EACTags;

@TargetApi(21)
/* renamed from: com.devbrackets.android.exomedia.ui.widget.VideoControlsLeanback */
public class VideoControlsLeanback extends VideoControls {
    protected ButtonFocusChangeListener buttonFocusChangeListener = new ButtonFocusChangeListener();
    protected ViewGroup controlsParent;
    protected View currentFocus;
    protected Drawable defaultFastForwardDrawable;
    protected Drawable defaultRewindDrawable;
    protected ImageButton fastForwardButton;
    protected ProgressBar progressBar;
    protected ImageButton rewindButton;
    protected ImageView rippleIndicator;

    /* renamed from: com.devbrackets.android.exomedia.ui.widget.VideoControlsLeanback$ButtonFocusChangeListener */
    protected class ButtonFocusChangeListener implements OnFocusChangeListener {
        protected ButtonFocusChangeListener() {
        }

        public void onFocusChange(View view, boolean hasFocus) {
            if (hasFocus) {
                VideoControlsLeanback.this.rippleIndicator.startAnimation(new RippleTranslateAnimation(getHorizontalDelta(view)));
            }
        }

        /* access modifiers changed from: protected */
        public int getHorizontalDelta(View selectedView) {
            int[] position = new int[2];
            selectedView.getLocationOnScreen(position);
            int viewX = position[0];
            VideoControlsLeanback.this.rippleIndicator.getLocationOnScreen(position);
            return (viewX - ((VideoControlsLeanback.this.rippleIndicator.getWidth() - selectedView.getWidth()) / 2)) - position[0];
        }
    }

    /* renamed from: com.devbrackets.android.exomedia.ui.widget.VideoControlsLeanback$LeanbackInternalListener */
    protected class LeanbackInternalListener extends InternalListener {
        protected LeanbackInternalListener() {
            super();
        }

        public boolean onFastForwardClicked() {
            if (VideoControlsLeanback.this.videoView == null) {
                return false;
            }
            int newPosition = VideoControlsLeanback.this.videoView.getCurrentPosition() - 10000;
            if (newPosition < 0) {
                newPosition = 0;
            }
            VideoControlsLeanback.this.performSeek(newPosition);
            return true;
        }

        public boolean onRewindClicked() {
            if (VideoControlsLeanback.this.videoView == null) {
                return false;
            }
            int newPosition = VideoControlsLeanback.this.videoView.getCurrentPosition() + 10000;
            if (newPosition > VideoControlsLeanback.this.progressBar.getMax()) {
                newPosition = VideoControlsLeanback.this.progressBar.getMax();
            }
            VideoControlsLeanback.this.performSeek(newPosition);
            return true;
        }
    }

    /* renamed from: com.devbrackets.android.exomedia.ui.widget.VideoControlsLeanback$RemoteKeyListener */
    protected class RemoteKeyListener implements OnKeyListener {
        protected RemoteKeyListener() {
        }

        public boolean onKey(View view, int keyCode, KeyEvent event) {
            if (event.getAction() != 0) {
                return false;
            }
            switch (keyCode) {
                case 4:
                    if (!VideoControlsLeanback.this.isVisible) {
                        return false;
                    }
                    VideoControlsLeanback.this.hideDelayed(0);
                    return true;
                case 19:
                    VideoControlsLeanback.this.showTemporary();
                    return true;
                case 20:
                    VideoControlsLeanback.this.hideDelayed(0);
                    return true;
                case 21:
                    VideoControlsLeanback.this.showTemporary();
                    VideoControlsLeanback.this.focusPrevious(VideoControlsLeanback.this.currentFocus);
                    return true;
                case 22:
                    VideoControlsLeanback.this.showTemporary();
                    VideoControlsLeanback.this.focusNext(VideoControlsLeanback.this.currentFocus);
                    return true;
                case 23:
                    VideoControlsLeanback.this.showTemporary();
                    VideoControlsLeanback.this.currentFocus.callOnClick();
                    return true;
                case 85:
                    VideoControlsLeanback.this.onPlayPauseClick();
                    return true;
                case 87:
                    VideoControlsLeanback.this.onNextClick();
                    return true;
                case 88:
                    VideoControlsLeanback.this.onPreviousClick();
                    return true;
                case 89:
                    VideoControlsLeanback.this.onRewindClick();
                    return true;
                case 90:
                    VideoControlsLeanback.this.onFastForwardClick();
                    return true;
                case EACTags.NON_INTERINDUSTRY_DATA_OBJECT_NESTING_TEMPLATE /*126*/:
                    if (VideoControlsLeanback.this.videoView == null || VideoControlsLeanback.this.videoView.isPlaying()) {
                        return false;
                    }
                    VideoControlsLeanback.this.videoView.start();
                    return true;
                case CertificateBody.profileType /*127*/:
                    if (VideoControlsLeanback.this.videoView == null || !VideoControlsLeanback.this.videoView.isPlaying()) {
                        return false;
                    }
                    VideoControlsLeanback.this.videoView.pause();
                    return true;
                default:
                    return false;
            }
        }
    }

    /* renamed from: com.devbrackets.android.exomedia.ui.widget.VideoControlsLeanback$RippleTranslateAnimation */
    protected class RippleTranslateAnimation extends TranslateAnimation implements AnimationListener {
        protected int xDelta;

        public RippleTranslateAnimation(int xDelta2) {
            super(0.0f, (float) xDelta2, 0.0f, 0.0f);
            this.xDelta = xDelta2;
            setDuration(250);
            setAnimationListener(this);
        }

        public void onAnimationStart(Animation animation) {
        }

        public void onAnimationEnd(Animation animation) {
            VideoControlsLeanback.this.rippleIndicator.setX(VideoControlsLeanback.this.rippleIndicator.getX() + ((float) this.xDelta));
            VideoControlsLeanback.this.rippleIndicator.clearAnimation();
        }

        public void onAnimationRepeat(Animation animation) {
        }
    }

    public VideoControlsLeanback(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public void setup(Context context) {
        super.setup(context);
        this.internalListener = new LeanbackInternalListener();
        registerForInput();
        setFocusable(true);
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.playPauseButton.requestFocus();
        this.currentFocus = this.playPauseButton;
    }

    /* access modifiers changed from: protected */
    public int getLayoutResource() {
        return R.layout.exomedia_default_controls_leanback;
    }

    public void setPosition(long position) {
        this.currentTime.setText(TimeFormatUtil.formatMs(position));
        this.progressBar.setProgress((int) position);
    }

    public void setDuration(long duration) {
        if (duration != ((long) this.progressBar.getMax())) {
            this.endTime.setText(TimeFormatUtil.formatMs(duration));
            this.progressBar.setMax((int) duration);
        }
    }

    public void updateProgress(long position, long duration, int bufferPercent) {
        this.progressBar.setSecondaryProgress((int) (((float) this.progressBar.getMax()) * (((float) bufferPercent) / 100.0f)));
        this.progressBar.setProgress((int) position);
        this.currentTime.setText(TimeFormatUtil.formatMs(position));
    }

    public void setRewindImageResource(int resourceId) {
        if (this.rewindButton != null) {
            if (resourceId != 0) {
                this.rewindButton.setImageResource(resourceId);
            } else {
                this.rewindButton.setImageDrawable(this.defaultRewindDrawable);
            }
        }
    }

    public void setFastForwardImageResource(int resourceId) {
        if (this.fastForwardButton != null) {
            if (resourceId != 0) {
                this.fastForwardButton.setImageResource(resourceId);
            } else {
                this.fastForwardButton.setImageDrawable(this.defaultFastForwardDrawable);
            }
        }
    }

    public void setRewindButtonEnabled(boolean enabled) {
        if (this.rewindButton != null) {
            this.rewindButton.setEnabled(enabled);
        }
    }

    public void setFastForwardButtonEnabled(boolean enabled) {
        if (this.fastForwardButton != null) {
            this.fastForwardButton.setEnabled(enabled);
        }
    }

    public void setRewindButtonRemoved(boolean removed) {
        if (this.rewindButton != null) {
            this.rewindButton.setVisibility(removed ? 8 : 0);
        }
    }

    public void setFastForwardButtonRemoved(boolean removed) {
        if (this.fastForwardButton != null) {
            this.fastForwardButton.setVisibility(removed ? 8 : 0);
        }
    }

    /* access modifiers changed from: protected */
    public void retrieveViews() {
        super.retrieveViews();
        this.progressBar = (ProgressBar) findViewById(R.id.exomedia_controls_video_progress);
        this.rewindButton = (ImageButton) findViewById(R.id.exomedia_controls_rewind_btn);
        this.fastForwardButton = (ImageButton) findViewById(R.id.exomedia_controls_fast_forward_btn);
        this.rippleIndicator = (ImageView) findViewById(R.id.exomedia_controls_leanback_ripple);
        this.controlsParent = (ViewGroup) findViewById(R.id.exomedia_controls_parent);
    }

    /* access modifiers changed from: protected */
    public void registerListeners() {
        super.registerListeners();
        this.rewindButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                VideoControlsLeanback.this.onRewindClick();
            }
        });
        this.fastForwardButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                VideoControlsLeanback.this.onFastForwardClick();
            }
        });
        this.previousButton.setOnFocusChangeListener(this.buttonFocusChangeListener);
        this.rewindButton.setOnFocusChangeListener(this.buttonFocusChangeListener);
        this.playPauseButton.setOnFocusChangeListener(this.buttonFocusChangeListener);
        this.fastForwardButton.setOnFocusChangeListener(this.buttonFocusChangeListener);
        this.nextButton.setOnFocusChangeListener(this.buttonFocusChangeListener);
    }

    /* access modifiers changed from: protected */
    public void updateButtonDrawables() {
        super.updateButtonDrawables();
        this.defaultRewindDrawable = EMResourceUtil.tintList(getContext(), R.drawable.exomedia_ic_rewind_white, R.color.exomedia_default_controls_button_selector);
        this.rewindButton.setImageDrawable(this.defaultRewindDrawable);
        this.defaultFastForwardDrawable = EMResourceUtil.tintList(getContext(), R.drawable.exomedia_ic_fast_forward_white, R.color.exomedia_default_controls_button_selector);
        this.fastForwardButton.setImageDrawable(this.defaultFastForwardDrawable);
    }

    /* access modifiers changed from: protected */
    public void animateVisibility(boolean toVisible) {
        if (this.isVisible != toVisible) {
            if (!this.isLoading) {
                this.controlsParent.startAnimation(new BottomViewHideShowAnimation(this.controlsParent, toVisible, 300));
            }
            this.isVisible = toVisible;
            onVisibilityChanged();
        }
    }

    /* access modifiers changed from: protected */
    public void updateTextContainerVisibility() {
        if (this.isVisible) {
            boolean emptyText = isTextContainerEmpty();
            if (this.hideEmptyTextContainer && emptyText && this.textContainer.getVisibility() == 0) {
                this.textContainer.clearAnimation();
                this.textContainer.startAnimation(new BottomViewHideShowAnimation(this.textContainer, false, 300));
            } else if ((!this.hideEmptyTextContainer || !emptyText) && this.textContainer.getVisibility() != 0) {
                this.textContainer.clearAnimation();
                this.textContainer.startAnimation(new BottomViewHideShowAnimation(this.textContainer, true, 300));
            }
        }
    }

    public void showLoading(boolean initialLoad) {
        if (!this.isLoading) {
            this.isLoading = true;
            this.controlsContainer.setVisibility(8);
            this.rippleIndicator.setVisibility(8);
            this.loadingProgress.setVisibility(0);
            show();
        }
    }

    public void finishLoading() {
        boolean z = false;
        if (this.isLoading) {
            this.isLoading = false;
            this.controlsContainer.setVisibility(0);
            this.rippleIndicator.setVisibility(0);
            this.loadingProgress.setVisibility(8);
            if (this.videoView != null && this.videoView.isPlaying()) {
                z = true;
            }
            updatePlaybackState(z);
        }
    }

    /* access modifiers changed from: protected */
    public void onRewindClick() {
        if (this.buttonsListener == null || !this.buttonsListener.onRewindClicked()) {
            this.internalListener.onRewindClicked();
        }
    }

    /* access modifiers changed from: protected */
    public void onFastForwardClick() {
        if (this.buttonsListener == null || !this.buttonsListener.onFastForwardClicked()) {
            this.internalListener.onFastForwardClicked();
        }
    }

    /* access modifiers changed from: protected */
    public void performSeek(int seekToTime) {
        if (this.seekListener == null || !this.seekListener.onSeekEnded(seekToTime)) {
            this.internalListener.onSeekEnded(seekToTime);
        }
    }

    /* access modifiers changed from: protected */
    public void showTemporary() {
        show();
        if (this.videoView != null && this.videoView.isPlaying()) {
            hideDelayed(2000);
        }
    }

    /* access modifiers changed from: protected */
    public void registerForInput() {
        RemoteKeyListener remoteKeyListener = new RemoteKeyListener();
        setOnKeyListener(remoteKeyListener);
        this.playPauseButton.setOnKeyListener(remoteKeyListener);
        this.previousButton.setOnKeyListener(remoteKeyListener);
        this.nextButton.setOnKeyListener(remoteKeyListener);
        this.rewindButton.setOnKeyListener(remoteKeyListener);
        this.fastForwardButton.setOnKeyListener(remoteKeyListener);
    }

    /* access modifiers changed from: protected */
    public void focusNext(View view) {
        int nextId = view.getNextFocusRightId();
        if (nextId != -1) {
            View nextView = findViewById(nextId);
            if (nextView.getVisibility() != 0) {
                focusNext(nextView);
                return;
            }
            nextView.requestFocus();
            this.currentFocus = nextView;
            this.buttonFocusChangeListener.onFocusChange(nextView, true);
        }
    }

    /* access modifiers changed from: protected */
    public void focusPrevious(View view) {
        int previousId = view.getNextFocusLeftId();
        if (previousId != -1) {
            View previousView = findViewById(previousId);
            if (previousView.getVisibility() != 0) {
                focusPrevious(previousView);
                return;
            }
            previousView.requestFocus();
            this.currentFocus = previousView;
            this.buttonFocusChangeListener.onFocusChange(previousView, true);
        }
    }
}
