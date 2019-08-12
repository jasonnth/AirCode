package com.airbnb.android.lib.views;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.primitives.AirTextView;

public class BounceFtueView extends RelativeLayout {
    private final int mDrawableId;
    private final int mHeaderTextId;
    @BindView
    AirTextView mHeaderTextView;
    @BindView
    ImageView mImageView;
    @BindView
    View mOverlay;
    private final int mTextId;
    @BindView
    AirTextView mTextView;

    public BounceFtueView(Context context) {
        this(context, null);
    }

    public BounceFtueView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BounceFtueView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        ButterKnife.bind(this, LayoutInflater.from(context).inflate(C0880R.layout.bounce_ftue, this));
        TypedArray a = context.obtainStyledAttributes(attrs, C0880R.styleable.BounceFtueView);
        this.mDrawableId = a.getResourceId(C0880R.styleable.BounceFtueView_image, 0);
        this.mHeaderTextId = a.getResourceId(C0880R.styleable.BounceFtueView_headerText, 0);
        this.mTextId = a.getResourceId(C0880R.styleable.BounceFtueView_text, 0);
        a.recycle();
        init();
    }

    private void init() {
        this.mImageView.setImageDrawable(getResources().getDrawable(this.mDrawableId));
        this.mHeaderTextView.setText(this.mHeaderTextId);
        this.mTextView.setText(this.mTextId);
    }

    public void animateFtue() {
        this.mImageView.measure(0, 0);
        int imageViewHeight = this.mImageView.getMeasuredHeight();
        ObjectAnimator translationAnimation = ObjectAnimator.ofFloat(this.mImageView, "y", new float[]{(float) (-imageViewHeight), (float) (((int) this.mHeaderTextView.getY()) - imageViewHeight)});
        translationAnimation.setStartDelay(250);
        translationAnimation.setDuration(500);
        translationAnimation.setInterpolator(new OvershootInterpolator(1.0f));
        C72011 r0 = new AnimatorListener() {
            public void onAnimationStart(Animator animator) {
                BounceFtueView.this.mImageView.setVisibility(0);
            }

            public void onAnimationEnd(Animator animator) {
            }

            public void onAnimationCancel(Animator animator) {
            }

            public void onAnimationRepeat(Animator animator) {
            }
        };
        translationAnimation.addListener(r0);
        ObjectAnimator overlayAnimation = ObjectAnimator.ofFloat(this.mOverlay, "alpha", new float[]{0.0f, 0.9f});
        overlayAnimation.setDuration(500);
        ObjectAnimator headerTextAnimation = ObjectAnimator.ofFloat(this.mHeaderTextView, "alpha", new float[]{0.0f, 1.0f});
        headerTextAnimation.setDuration(500);
        ObjectAnimator textAnimation = ObjectAnimator.ofFloat(this.mTextView, "alpha", new float[]{0.0f, 1.0f});
        textAnimation.setDuration(500);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(overlayAnimation).before(translationAnimation).before(headerTextAnimation).before(textAnimation);
        animatorSet.start();
    }

    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
        boolean visible = visibility == 0;
        setFocusableInTouchMode(visible);
        if (visible) {
            requestFocus();
            setOnKeyListener(BounceFtueView$$Lambda$1.lambdaFactory$(this));
        }
    }

    static /* synthetic */ boolean lambda$setVisibility$0(BounceFtueView bounceFtueView, View v, int keyCode, KeyEvent event) {
        if (keyCode != 4) {
            return false;
        }
        bounceFtueView.setVisibility(8);
        return true;
    }
}
