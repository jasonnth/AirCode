package com.airbnb.android.core.views;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcelable;
import android.support.p000v4.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.IcepickWrapper;
import com.airbnb.android.core.enums.UrgencyMessageType;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.android.utils.animation.ExpandAnimation;
import com.airbnb.android.utils.animation.SimpleAnimatorListener;
import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.p027n2.interfaces.DividerView;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.utils.TextUtil;
import icepick.State;

public class UrgencyView extends FrameLayout implements DividerView {
    private static final int EXPAND_ANIMATION_DURATION = 300;
    private static final int TEXT_FADE_IN_DURATION = 250;
    @BindView
    ViewGroup contentContainer;
    /* access modifiers changed from: private */
    public AnimatorSet currentAnimation = null;
    @BindView
    View divider;
    @State
    boolean hasAnimated;
    @BindView
    LottieAnimationView image;
    private boolean showDivider;
    @BindView
    AirTextView text;
    @BindView
    ViewGroup textContainer;

    public UrgencyView(Context context) {
        super(context);
        init(null);
    }

    public UrgencyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public UrgencyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void showChildren(boolean show) {
        ViewUtils.setVisibleIf((View) this.contentContainer, show);
        ViewUtils.setVisibleIf(this.divider, show && this.showDivider);
    }

    private void init(AttributeSet attrs) {
        inflate(getContext(), C0716R.layout.view_urgency, this);
        ButterKnife.bind((View) this);
        setupAttributes(attrs);
        showChildren(false);
    }

    private void setupAttributes(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, C0716R.styleable.UrgencyView);
        showDivider(a.getBoolean(C0716R.styleable.UrgencyView_n2_showDivider, true));
        a.recycle();
    }

    public void setUrgencyData(String title, String subtitle, UrgencyMessageType type) {
        this.image.setAnimation(type.getLottieFile());
        this.text.setText(TextUtil.makeCircularBold(getContext(), title));
        this.text.append(" " + subtitle);
        if (hasAnimated()) {
            showWithoutAnimation();
        }
    }

    public void showDivider(boolean showDivider2) {
        this.showDivider = showDivider2;
        ViewUtils.setVisibleIf(this.divider, showDivider2 && this.contentContainer.getVisibility() == 0);
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        return IcepickWrapper.saveInstanceState(this, super.onSaveInstanceState());
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(IcepickWrapper.restoreInstanceState(this, state));
        if (hasAnimated()) {
            showWithoutAnimation();
        }
    }

    public boolean hasAnimated() {
        return this.hasAnimated;
    }

    public void showWithoutAnimation() {
        cancelCurrentAnimation();
        this.hasAnimated = true;
        this.image.setProgress(1.0f);
        showChildren(true);
    }

    private void cancelCurrentAnimation() {
        if (this.currentAnimation != null) {
            this.currentAnimation.cancel();
            this.currentAnimation = null;
        }
    }

    public void startUrgencyAnimation() {
        Check.state(!this.hasAnimated, "Animation was already started");
        this.hasAnimated = true;
        showChildren(true);
        animateLottieImage();
    }

    private void animateLottieImage() {
        this.textContainer.setAlpha(0.0f);
        ValueAnimator expandAnimator = new ExpandAnimation(this);
        expandAnimator.setDuration(300);
        ObjectAnimator textAnimator = ObjectAnimator.ofFloat(this.textContainer, "alpha", new float[]{1.0f}).setDuration(250);
        textAnimator.setStartDelay(150);
        this.textContainer.setLayerType(2, null);
        this.currentAnimation = new AnimatorSet();
        this.currentAnimation.setInterpolator(new FastOutSlowInInterpolator());
        this.currentAnimation.addListener(new SimpleAnimatorListener() {
            public void onAnimationEnd(Animator animation) {
                UrgencyView.this.textContainer.setLayerType(0, null);
                UrgencyView.this.image.setProgress(1.0f);
                UrgencyView.this.currentAnimation = null;
            }

            public void onAnimationCancel(Animator animation) {
                UrgencyView.this.image.cancelAnimation();
                UrgencyView.this.image.setProgress(1.0f);
                UrgencyView.this.textContainer.setAlpha(1.0f);
            }
        });
        this.currentAnimation.playTogether(new Animator[]{expandAnimator, textAnimator});
        this.currentAnimation.start();
        this.image.setProgress(0.0f);
        this.image.playAnimation();
    }
}
