package com.devbrackets.android.exomedia.p306ui.widget;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import com.devbrackets.android.exomedia.R;
import com.devbrackets.android.exomedia.p306ui.animation.BottomViewHideShowAnimation;
import com.devbrackets.android.exomedia.p306ui.animation.TopViewHideShowAnimation;
import com.devbrackets.android.exomedia.util.TimeFormatUtil;
import java.util.LinkedList;
import java.util.List;

/* renamed from: com.devbrackets.android.exomedia.ui.widget.VideoControlsMobile */
public class VideoControlsMobile extends VideoControls {
    protected LinearLayout extraViewsContainer;
    protected SeekBar seekBar;
    protected boolean userInteracting = false;

    /* renamed from: com.devbrackets.android.exomedia.ui.widget.VideoControlsMobile$SeekBarChanged */
    protected class SeekBarChanged implements OnSeekBarChangeListener {
        private int seekToTime;

        protected SeekBarChanged() {
        }

        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if (fromUser) {
                this.seekToTime = progress;
                if (VideoControlsMobile.this.currentTime != null) {
                    VideoControlsMobile.this.currentTime.setText(TimeFormatUtil.formatMs((long) this.seekToTime));
                }
            }
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
            VideoControlsMobile.this.userInteracting = true;
            if (VideoControlsMobile.this.seekListener == null || !VideoControlsMobile.this.seekListener.onSeekStarted()) {
                VideoControlsMobile.this.internalListener.onSeekStarted();
            }
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
            VideoControlsMobile.this.userInteracting = false;
            if (VideoControlsMobile.this.seekListener == null || !VideoControlsMobile.this.seekListener.onSeekEnded(this.seekToTime)) {
                VideoControlsMobile.this.internalListener.onSeekEnded(this.seekToTime);
            }
        }
    }

    public VideoControlsMobile(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public int getLayoutResource() {
        return R.layout.exomedia_default_controls_mobile;
    }

    public void setPosition(long position) {
        this.currentTime.setText(TimeFormatUtil.formatMs(position));
        this.seekBar.setProgress((int) position);
    }

    public void setDuration(long duration) {
        if (duration != ((long) this.seekBar.getMax())) {
            this.endTime.setText(TimeFormatUtil.formatMs(duration));
            this.seekBar.setMax((int) duration);
        }
    }

    public void updateProgress(long position, long duration, int bufferPercent) {
        if (!this.userInteracting) {
            this.seekBar.setSecondaryProgress((int) (((float) this.seekBar.getMax()) * (((float) bufferPercent) / 100.0f)));
            this.seekBar.setProgress((int) position);
            this.currentTime.setText(TimeFormatUtil.formatMs(position));
        }
    }

    /* access modifiers changed from: protected */
    public void retrieveViews() {
        super.retrieveViews();
        this.seekBar = (SeekBar) findViewById(R.id.exomedia_controls_video_seek);
        this.extraViewsContainer = (LinearLayout) findViewById(R.id.exomedia_controls_extra_container);
    }

    /* access modifiers changed from: protected */
    public void registerListeners() {
        super.registerListeners();
        this.seekBar.setOnSeekBarChangeListener(new SeekBarChanged());
    }

    public List<View> getExtraViews() {
        int childCount = this.extraViewsContainer.getChildCount();
        if (childCount <= 0) {
            return super.getExtraViews();
        }
        List<View> children = new LinkedList<>();
        for (int i = 0; i < childCount; i++) {
            children.add(this.extraViewsContainer.getChildAt(i));
        }
        return children;
    }

    public void hideDelayed(long delay) {
        this.hideDelay = delay;
        if (delay >= 0 && this.canViewHide && !this.isLoading && !this.userInteracting) {
            this.visibilityHandler.postDelayed(new Runnable() {
                public void run() {
                    VideoControlsMobile.this.animateVisibility(false);
                }
            }, delay);
        }
    }

    /* access modifiers changed from: protected */
    public void animateVisibility(boolean toVisible) {
        if (this.isVisible != toVisible) {
            if (!this.hideEmptyTextContainer || !isTextContainerEmpty()) {
                this.textContainer.startAnimation(new TopViewHideShowAnimation(this.textContainer, toVisible, 300));
            }
            if (!this.isLoading) {
                this.controlsContainer.startAnimation(new BottomViewHideShowAnimation(this.controlsContainer, toVisible, 300));
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
                this.textContainer.startAnimation(new TopViewHideShowAnimation(this.textContainer, false, 300));
            } else if ((!this.hideEmptyTextContainer || !emptyText) && this.textContainer.getVisibility() != 0) {
                this.textContainer.clearAnimation();
                this.textContainer.startAnimation(new TopViewHideShowAnimation(this.textContainer, true, 300));
            }
        }
    }

    public void showLoading(boolean initialLoad) {
        if (!this.isLoading) {
            this.isLoading = true;
            this.controlsContainer.setVisibility(8);
            this.loadingProgress.setVisibility(0);
            show();
        }
    }

    public void finishLoading() {
        boolean z = false;
        if (this.isLoading) {
            this.isLoading = false;
            this.controlsContainer.setVisibility(0);
            this.loadingProgress.setVisibility(8);
            if (this.videoView != null && this.videoView.isPlaying()) {
                z = true;
            }
            updatePlaybackState(z);
        }
    }
}
