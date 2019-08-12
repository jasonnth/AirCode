package com.airbnb.android.core.views;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.content.Context;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.airbnb.android.core.IcepickWrapper;
import com.airbnb.lottie.LottieAnimationView;
import icepick.State;

public class LoaderFrame extends FrameLayout {
    public static final long FADE_TIME = 300;
    @State
    boolean animating;
    private LottieAnimationView loader;

    public interface LoaderFrameDisplay {
        void displayLoaderFrame(boolean z);
    }

    public LoaderFrame(Context context, AttributeSet attrs) {
        super(context, attrs);
        setClickable(true);
    }

    public void finish() {
        if (this.animating) {
            this.animating = false;
            if (getVisibility() == 0) {
                animate().alpha(0.0f).setDuration(300).setListener(new AnimatorListener() {
                    public void onAnimationStart(Animator animation) {
                    }

                    public void onAnimationEnd(Animator animation) {
                        if (!LoaderFrame.this.animating) {
                            LoaderFrame.this.setVisibility(8);
                        }
                    }

                    public void onAnimationCancel(Animator animation) {
                    }

                    public void onAnimationRepeat(Animator animation) {
                    }
                });
            }
            this.loader.cancelAnimation();
        }
    }

    public void finishImmediate() {
        setVisibility(8);
        finish();
    }

    public void startAnimation() {
        setVisibility(0);
        setAlpha(1.0f);
        initializeLoaderAnimationIfNeeded();
        this.loader.playAnimation();
        this.animating = true;
    }

    private void initializeLoaderAnimationIfNeeded() {
        if (this.loader == null) {
            this.loader = new AnimatedLoadingOverlay(getContext());
            LayoutParams params = new LayoutParams(-2, -2);
            params.gravity = 17;
            addView(this.loader, params);
        }
    }

    public boolean isAnimating() {
        return this.animating;
    }

    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
        setClickable(visibility == 0);
    }

    public Parcelable onSaveInstanceState() {
        return IcepickWrapper.saveInstanceState(this, super.onSaveInstanceState());
    }

    public void onRestoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(IcepickWrapper.restoreInstanceState(this, state));
        if (this.animating) {
            startAnimation();
        } else {
            setVisibility(8);
        }
    }
}
