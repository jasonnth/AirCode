package com.airbnb.android.lib.views;

import android.animation.LayoutTransition;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.core.utils.PercentageUtils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.p027n2.utils.TextUtil;

public class AnnotatedAirProgressBar extends RelativeLayout {
    private static final int ANIMATE_BAR_DURATION = 1000;
    private static final int ANIMATE_START_DELAY = 300;
    private ValueAnimator mBarAnimator;
    private boolean mCollapsed;
    @BindView
    LinearLayout mGoalPopupContainer;
    @BindView
    TextView mGoalPopupText;
    private boolean mPercentageMode;
    @BindView
    AirProgressBar mProgressBar;
    @BindView
    TextView mProgressText;
    private int mThreshold;
    private int mThresholdNotReachedColor;
    private int mThresholdReachedColor;
    @BindView
    TextView mTitle;

    public AnnotatedAirProgressBar(Context context) {
        this(context, null);
    }

    public AnnotatedAirProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AnnotatedAirProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mCollapsed = true;
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(C0880R.layout.air_progress_bar, this, true);
        ButterKnife.bind((View) this);
        setClipToPadding(false);
        setClipChildren(false);
        this.mThresholdReachedColor = getResources().getColor(C0880R.color.c_green);
        this.mThresholdNotReachedColor = getResources().getColor(C0880R.color.c_hof);
        setupLayoutTransition();
    }

    private void setupLayoutTransition() {
        LayoutTransition transition = new LayoutTransition();
        transition.setStartDelay(2, 0);
        setLayoutTransition(transition);
    }

    public void setTitle(int title) {
        this.mTitle.setText(title);
    }

    public void setProgressLevels(int progress, int threshold, boolean percentageMode) {
        int i;
        if (this.mBarAnimator != null && this.mBarAnimator.isStarted()) {
            this.mBarAnimator.cancel();
        }
        this.mThreshold = threshold;
        AirProgressBar airProgressBar = this.mProgressBar;
        if (percentageMode) {
            i = threshold;
        } else {
            i = 100;
        }
        airProgressBar.setThresholdPercentage(i);
        this.mPercentageMode = percentageMode;
        this.mGoalPopupText.setText(TextUtil.makeBold(this.mPercentageMode ? PercentageUtils.localizePercentage(this.mThreshold) : Integer.toString(this.mThreshold)));
        int percentage = this.mPercentageMode ? progress : Math.min((int) (((((float) progress) * 100.0f) / ((float) threshold)) + 1.0E-4f), 100);
        this.mBarAnimator = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.mBarAnimator.setDuration(1000);
        this.mBarAnimator.setStartDelay(300);
        setProgressText(0.0f);
        setTitleColor(false);
        this.mProgressBar.setProgress(0);
        this.mBarAnimator.setInterpolator(new DecelerateInterpolator());
        this.mBarAnimator.addUpdateListener(AnnotatedAirProgressBar$$Lambda$1.lambdaFactory$(this, progress, percentage));
        post(AnnotatedAirProgressBar$$Lambda$2.lambdaFactory$(this));
    }

    static /* synthetic */ void lambda$setProgressLevels$0(AnnotatedAirProgressBar annotatedAirProgressBar, int progress, int percentage, ValueAnimator animation) {
        float interpolation = ((Float) animation.getAnimatedValue()).floatValue();
        int interpolatedProgress = (int) (((float) progress) * interpolation);
        int interpolatedPercent = (int) (((float) percentage) * interpolation);
        annotatedAirProgressBar.setProgressText((float) interpolatedProgress);
        annotatedAirProgressBar.setTitleColor(interpolatedProgress >= annotatedAirProgressBar.mThreshold);
        annotatedAirProgressBar.mProgressBar.setProgress(interpolatedPercent);
    }

    static /* synthetic */ void lambda$setProgressLevels$1(AnnotatedAirProgressBar annotatedAirProgressBar) {
        if (!annotatedAirProgressBar.mBarAnimator.isStarted()) {
            annotatedAirProgressBar.mBarAnimator.start();
        }
    }

    private void setTitleColor(boolean thresholdReached) {
        this.mProgressText.setTextColor(thresholdReached ? this.mThresholdReachedColor : this.mThresholdNotReachedColor);
    }

    private void setProgressText(float progress) {
        this.mProgressText.setText(this.mPercentageMode ? PercentageUtils.localizePercentage((int) progress) : Integer.toString((int) progress));
    }

    private void placeGoalIndicator() {
        int containerWidth = this.mGoalPopupContainer.getMeasuredWidth();
        if (containerWidth <= 0) {
            this.mGoalPopupContainer.measure(0, 0);
            containerWidth = this.mGoalPopupContainer.getMeasuredWidth();
        }
        this.mGoalPopupContainer.setTranslationX((float) (this.mProgressBar.getIndicatorPosition() - (containerWidth / 2)));
    }

    public void toggle() {
        this.mCollapsed = !this.mCollapsed;
        if (!this.mCollapsed) {
            placeGoalIndicator();
        }
        ViewUtils.setGoneIf(this.mGoalPopupContainer, this.mCollapsed);
    }

    public boolean isExpanded() {
        return !this.mCollapsed;
    }
}
