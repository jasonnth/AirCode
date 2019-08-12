package com.airbnb.android.lib.activities;

import android.animation.Animator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.core.UnhandledStateException;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.activities.HomeActivity.AccountMode;
import com.airbnb.android.utils.animation.SimpleAnimatorListener;
import com.airbnb.lottie.LottieAnimationView;

public class AppModeTransitionLayout extends FrameLayout {
    private static final int HIDE_DURATION = 800;
    private static final long REVEAL_TIME = 50;
    private static final long Y_TRANSLATION_DELAY = 150;
    private static final long Y_TRANSLATION_TIME = 300;
    @BindView
    LottieAnimationView animationView;
    @BindView
    LinearLayout contentLayout;
    @BindColor
    int guestModeBackgroundColor;
    @BindColor
    int guestTextColor;
    @BindColor
    int hostModeBackgroundColor;
    @BindColor
    int hostTextColor;
    @BindView
    TextView modeSwitchTextView;
    @BindColor
    int tripHostModeBackgroundColor;
    @BindColor
    int tripHostTextColor;

    public AppModeTransitionLayout(Context context) {
        super(context);
        init();
    }

    public AppModeTransitionLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AppModeTransitionLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), C0880R.layout.layout_app_mode_transition, this);
        ButterKnife.bind((View) this);
    }

    public void startModeAnimation(AccountMode mode) {
        setVisibility(0);
        setBackgroundColor(getBackgroundColor(mode));
        setAlpha(0.0f);
        animate().alpha(1.0f).setDuration(REVEAL_TIME);
        this.animationView.setAnimation(getAnimationFile(mode));
        this.modeSwitchTextView.setTextColor(getTextColor(mode));
        this.modeSwitchTextView.setText(getSwitchTextRes(mode));
        this.contentLayout.measure(0, 0);
        this.contentLayout.setTranslationY(((float) (-this.contentLayout.getMeasuredHeight())) / 4.0f);
        this.contentLayout.setAlpha(0.0f);
        this.contentLayout.animate().translationY(0.0f).alpha(1.0f).setDuration(300).setStartDelay(Y_TRANSLATION_DELAY).setListener(new SimpleAnimatorListener() {
            public void onAnimationStart(Animator animation) {
                AppModeTransitionLayout.this.animationView.playAnimation();
            }
        });
        this.animationView.addAnimatorListener(new SimpleAnimatorListener() {
            public void onAnimationEnd(Animator animation) {
                AppModeTransitionLayout.this.animate().alpha(0.0f).setDuration(800);
            }
        });
    }

    private static String getAnimationFile(AccountMode mode) {
        switch (mode) {
            case GUEST:
                return "spinning-belo-foggy.json";
            case HOST:
                return "spinning-belo-white.json";
            case TRIP_HOST:
                return "spinning-belo-rausch.json";
            default:
                throw new UnhandledStateException(mode);
        }
    }

    private int getTextColor(AccountMode mode) {
        switch (mode) {
            case GUEST:
                return this.guestTextColor;
            case HOST:
                return this.hostTextColor;
            case TRIP_HOST:
                return this.tripHostTextColor;
            default:
                throw new UnhandledStateException(mode);
        }
    }

    private int getBackgroundColor(AccountMode mode) {
        switch (mode) {
            case GUEST:
                return this.guestModeBackgroundColor;
            case HOST:
                return this.hostModeBackgroundColor;
            case TRIP_HOST:
                return this.tripHostModeBackgroundColor;
            default:
                throw new UnhandledStateException(mode);
        }
    }

    private static int getSwitchTextRes(AccountMode mode) {
        switch (mode) {
            case GUEST:
                return C0880R.string.switching_to_travel_mode;
            case HOST:
                return C0880R.string.switching_to_host_mode;
            case TRIP_HOST:
                return C0880R.string.switching_to_trip_host_mode;
            default:
                throw new UnhandledStateException(mode);
        }
    }
}
