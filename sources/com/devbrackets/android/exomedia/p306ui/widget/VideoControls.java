package com.devbrackets.android.exomedia.p306ui.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.devbrackets.android.exomedia.R;
import com.devbrackets.android.exomedia.listener.VideoControlsButtonListener;
import com.devbrackets.android.exomedia.listener.VideoControlsSeekListener;
import com.devbrackets.android.exomedia.listener.VideoControlsVisibilityListener;
import com.devbrackets.android.exomedia.util.EMResourceUtil;
import com.devbrackets.android.exomedia.util.Repeater;
import com.devbrackets.android.exomedia.util.Repeater.RepeatListener;
import java.util.LinkedList;
import java.util.List;

/* renamed from: com.devbrackets.android.exomedia.ui.widget.VideoControls */
public abstract class VideoControls extends RelativeLayout {
    protected VideoControlsButtonListener buttonsListener;
    protected boolean canViewHide = true;
    protected ViewGroup controlsContainer;
    protected TextView currentTime;
    protected Drawable defaultNextDrawable;
    protected Drawable defaultPauseDrawable;
    protected Drawable defaultPlayDrawable;
    protected Drawable defaultPreviousDrawable;
    protected TextView descriptionView;
    protected TextView endTime;
    protected long hideDelay = -1;
    protected boolean hideEmptyTextContainer = true;
    protected InternalListener internalListener = new InternalListener();
    protected boolean isLoading = false;
    protected boolean isVisible = true;
    protected ProgressBar loadingProgress;
    protected ImageButton nextButton;
    protected int pauseResourceId = 0;
    protected ImageButton playPauseButton;
    protected int playResourceId = 0;
    protected ImageButton previousButton;
    protected Repeater progressPollRepeater = new Repeater();
    protected VideoControlsSeekListener seekListener;
    protected TextView subTitleView;
    protected ViewGroup textContainer;
    protected TextView titleView;
    protected EMVideoView videoView;
    protected Handler visibilityHandler = new Handler();
    protected VideoControlsVisibilityListener visibilityListener;

    /* renamed from: com.devbrackets.android.exomedia.ui.widget.VideoControls$InternalListener */
    protected class InternalListener implements VideoControlsButtonListener, VideoControlsSeekListener {
        protected boolean pausedForSeek = false;

        protected InternalListener() {
        }

        public boolean onPlayPauseClicked() {
            if (VideoControls.this.videoView == null) {
                return false;
            }
            if (VideoControls.this.videoView.isPlaying()) {
                VideoControls.this.videoView.pause();
            } else {
                VideoControls.this.videoView.start();
            }
            return true;
        }

        public boolean onPreviousClicked() {
            return false;
        }

        public boolean onNextClicked() {
            return false;
        }

        public boolean onRewindClicked() {
            return false;
        }

        public boolean onFastForwardClicked() {
            return false;
        }

        public boolean onSeekStarted() {
            if (VideoControls.this.videoView == null) {
                return false;
            }
            if (VideoControls.this.videoView.isPlaying()) {
                this.pausedForSeek = true;
                VideoControls.this.videoView.pause();
            }
            VideoControls.this.show();
            return true;
        }

        public boolean onSeekEnded(int seekTime) {
            if (VideoControls.this.videoView == null) {
                return false;
            }
            VideoControls.this.videoView.seekTo(seekTime);
            if (this.pausedForSeek) {
                this.pausedForSeek = false;
                VideoControls.this.videoView.start();
                VideoControls.this.hideDelayed(VideoControls.this.hideDelay);
            }
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public abstract void animateVisibility(boolean z);

    public abstract void finishLoading();

    /* access modifiers changed from: protected */
    public abstract int getLayoutResource();

    public abstract void setDuration(long j);

    public abstract void setPosition(long j);

    public abstract void showLoading(boolean z);

    public abstract void updateProgress(long j, long j2, int i);

    /* access modifiers changed from: protected */
    public abstract void updateTextContainerVisibility();

    public VideoControls(Context context) {
        super(context);
        setup(context);
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.progressPollRepeater.stop();
        this.progressPollRepeater.setRepeatListener(null);
    }

    public void setVideoView(EMVideoView EMVideoView) {
        this.videoView = EMVideoView;
    }

    public void setSeekListener(VideoControlsSeekListener callbacks) {
        this.seekListener = callbacks;
    }

    public void setButtonListener(VideoControlsButtonListener callback) {
        this.buttonsListener = callback;
    }

    public void setVisibilityListener(VideoControlsVisibilityListener callbacks) {
        this.visibilityListener = callbacks;
    }

    public void updatePlaybackState(boolean isPlaying) {
        updatePlayPauseImage(isPlaying);
        this.progressPollRepeater.start();
        if (isPlaying) {
            hideDelayed(2000);
        } else {
            show();
        }
    }

    public void setTitle(CharSequence title) {
        this.titleView.setText(title);
        updateTextContainerVisibility();
    }

    public void setSubTitle(CharSequence subTitle) {
        this.subTitleView.setText(subTitle);
        updateTextContainerVisibility();
    }

    public void setDescription(CharSequence description) {
        this.descriptionView.setText(description);
        updateTextContainerVisibility();
    }

    public void setPreviousImageResource(int resourceId) {
        if (resourceId != 0) {
            this.previousButton.setImageResource(resourceId);
        } else {
            this.previousButton.setImageDrawable(this.defaultPreviousDrawable);
        }
    }

    public void setNextImageResource(int resourceId) {
        if (resourceId != 0) {
            this.nextButton.setImageResource(resourceId);
        } else {
            this.nextButton.setImageDrawable(this.defaultNextDrawable);
        }
    }

    public void setRewindImageResource(int resourceId) {
    }

    public void setFastForwardImageResource(int resourceId) {
    }

    public void updatePlayPauseImage(boolean isPlaying) {
        if (isPlaying) {
            if (this.pauseResourceId != 0) {
                this.playPauseButton.setImageResource(this.pauseResourceId);
            } else {
                this.playPauseButton.setImageDrawable(this.defaultPauseDrawable);
            }
        } else if (this.playResourceId != 0) {
            this.playPauseButton.setImageResource(this.playResourceId);
        } else {
            this.playPauseButton.setImageDrawable(this.defaultPlayDrawable);
        }
    }

    public void setPreviousButtonEnabled(boolean enabled) {
        this.previousButton.setEnabled(enabled);
    }

    public void setNextButtonEnabled(boolean enabled) {
        this.nextButton.setEnabled(enabled);
    }

    public void setRewindButtonEnabled(boolean enabled) {
    }

    public void setFastForwardButtonEnabled(boolean enabled) {
    }

    public void setPreviousButtonRemoved(boolean removed) {
        this.previousButton.setVisibility(removed ? 8 : 0);
    }

    public void setNextButtonRemoved(boolean removed) {
        this.nextButton.setVisibility(removed ? 8 : 0);
    }

    public void setRewindButtonRemoved(boolean removed) {
    }

    public void setFastForwardButtonRemoved(boolean removed) {
    }

    public List<View> getExtraViews() {
        return new LinkedList();
    }

    public void show() {
        this.visibilityHandler.removeCallbacksAndMessages(null);
        clearAnimation();
        animateVisibility(true);
    }

    public void hideDelayed(long delay) {
        this.hideDelay = delay;
        if (delay >= 0 && this.canViewHide && !this.isLoading) {
            this.visibilityHandler.postDelayed(new Runnable() {
                public void run() {
                    VideoControls.this.animateVisibility(false);
                }
            }, delay);
        }
    }

    public void setCanHide(boolean canHide) {
        this.canViewHide = canHide;
    }

    public void setHideEmptyTextContainer(boolean hide) {
        this.hideEmptyTextContainer = hide;
        updateTextContainerVisibility();
    }

    /* access modifiers changed from: protected */
    public void retrieveViews() {
        this.currentTime = (TextView) findViewById(R.id.exomedia_controls_current_time);
        this.endTime = (TextView) findViewById(R.id.exomedia_controls_end_time);
        this.titleView = (TextView) findViewById(R.id.exomedia_controls_title);
        this.subTitleView = (TextView) findViewById(R.id.exomedia_controls_sub_title);
        this.descriptionView = (TextView) findViewById(R.id.exomedia_controls_description);
        this.playPauseButton = (ImageButton) findViewById(R.id.exomedia_controls_play_pause_btn);
        this.previousButton = (ImageButton) findViewById(R.id.exomedia_controls_previous_btn);
        this.nextButton = (ImageButton) findViewById(R.id.exomedia_controls_next_btn);
        this.loadingProgress = (ProgressBar) findViewById(R.id.exomedia_controls_video_loading);
        this.controlsContainer = (ViewGroup) findViewById(R.id.exomedia_controls_interactive_container);
        this.textContainer = (ViewGroup) findViewById(R.id.exomedia_controls_text_container);
    }

    /* access modifiers changed from: protected */
    public void registerListeners() {
        this.playPauseButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                VideoControls.this.onPlayPauseClick();
            }
        });
        this.previousButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                VideoControls.this.onPreviousClick();
            }
        });
        this.nextButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                VideoControls.this.onNextClick();
            }
        });
    }

    /* access modifiers changed from: protected */
    public void updateButtonDrawables() {
        this.defaultPlayDrawable = EMResourceUtil.tintList(getContext(), R.drawable.exomedia_ic_play_arrow_white, R.color.exomedia_default_controls_button_selector);
        this.defaultPauseDrawable = EMResourceUtil.tintList(getContext(), R.drawable.exomedia_ic_pause_white, R.color.exomedia_default_controls_button_selector);
        this.playPauseButton.setImageDrawable(this.defaultPlayDrawable);
        this.defaultPreviousDrawable = EMResourceUtil.tintList(getContext(), R.drawable.exomedia_ic_skip_previous_white, R.color.exomedia_default_controls_button_selector);
        this.previousButton.setImageDrawable(this.defaultPreviousDrawable);
        this.defaultNextDrawable = EMResourceUtil.tintList(getContext(), R.drawable.exomedia_ic_skip_next_white, R.color.exomedia_default_controls_button_selector);
        this.nextButton.setImageDrawable(this.defaultNextDrawable);
    }

    /* access modifiers changed from: protected */
    public void onPlayPauseClick() {
        if (this.buttonsListener == null || !this.buttonsListener.onPlayPauseClicked()) {
            this.internalListener.onPlayPauseClicked();
        }
    }

    /* access modifiers changed from: protected */
    public void onPreviousClick() {
        if (this.buttonsListener == null || !this.buttonsListener.onPreviousClicked()) {
            this.internalListener.onPreviousClicked();
        }
    }

    /* access modifiers changed from: protected */
    public void onNextClick() {
        if (this.buttonsListener == null || !this.buttonsListener.onNextClicked()) {
            this.internalListener.onNextClicked();
        }
    }

    /* access modifiers changed from: protected */
    public void setup(Context context) {
        View.inflate(context, getLayoutResource(), this);
        retrieveViews();
        registerListeners();
        updateButtonDrawables();
        this.progressPollRepeater.setRepeatListener(new RepeatListener() {
            public void onRepeat() {
                VideoControls.this.updateProgress();
            }
        });
    }

    /* access modifiers changed from: protected */
    public boolean isTextContainerEmpty() {
        if (this.titleView.getText() != null && this.titleView.getText().length() > 0) {
            return false;
        }
        if (this.subTitleView.getText() != null && this.subTitleView.getText().length() > 0) {
            return false;
        }
        if (this.descriptionView.getText() == null || this.descriptionView.getText().length() <= 0) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void onVisibilityChanged() {
        if (this.visibilityListener != null) {
            if (this.isVisible) {
                this.visibilityListener.onControlsShown();
            } else {
                this.visibilityListener.onControlsHidden();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void updateProgress() {
        if (this.videoView != null) {
            updateProgress((long) this.videoView.getCurrentPosition(), (long) this.videoView.getDuration(), this.videoView.getBufferPercentage());
        }
    }
}
