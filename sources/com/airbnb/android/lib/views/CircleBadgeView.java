package com.airbnb.android.lib.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.airbnb.android.airmapview.AirMapInterface;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.utils.ViewLibUtils;

public class CircleBadgeView extends FrameLayout {
    private static final int ANIMATION_DURATION = 1000;
    private final int mBackgroundColor;
    private final int mBadgeBorderSize;
    private final int mBadgeSize = (this.mBadgeSizeWithDropShadow - (((int) TypedValue.applyDimension(1, 1.0f, getResources().getDisplayMetrics())) * 7));
    private final int mBadgeSizeWithDropShadow;
    private final ImageView mBorderCircleImageView;
    private final ImageView mDropShadowCircleImageView;
    private final int mForegroundColor;
    private final AirImageView mIconImageView;
    private final int mInactiveForegroundColor;
    private final ImageView mInnerCircleImageView;
    private final int mInvisibleColor;
    private final ImageView mOuterCircleImageView;

    public CircleBadgeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(context).inflate(C0880R.layout.circle_badge_view, this);
        TypedArray a = context.obtainStyledAttributes(attrs, C0880R.styleable.CircleBadgeView);
        this.mBadgeSizeWithDropShadow = a.getDimensionPixelSize(C0880R.styleable.CircleBadgeView_size, 100);
        this.mBadgeBorderSize = a.getDimensionPixelSize(C0880R.styleable.CircleBadgeView_borderSize, 8);
        this.mForegroundColor = a.getInt(C0880R.styleable.CircleBadgeView_foregroundColor, AirMapInterface.CIRCLE_BORDER_COLOR);
        this.mBackgroundColor = a.getInt(C0880R.styleable.CircleBadgeView_backgroundColor, -1);
        this.mInactiveForegroundColor = a.getInt(C0880R.styleable.CircleBadgeView_inactiveForegroundColor, -7829368);
        this.mInvisibleColor = a.getInt(C0880R.styleable.CircleBadgeView_invisibleColor, -1);
        Drawable mIcon = ViewLibUtils.getDrawableCompat(context, a, C0880R.styleable.CircleBadgeView_iconSrc);
        a.recycle();
        this.mDropShadowCircleImageView = (ImageView) view.findViewById(C0880R.C0882id.cbv_drop_shadow_circle);
        this.mOuterCircleImageView = (ImageView) view.findViewById(C0880R.C0882id.cbv_outer_background_circle);
        this.mBorderCircleImageView = (ImageView) view.findViewById(C0880R.C0882id.cbv_border_circle);
        this.mInnerCircleImageView = (ImageView) view.findViewById(C0880R.C0882id.cbv_inner_background_circle);
        this.mIconImageView = (AirImageView) view.findViewById(C0880R.C0882id.cbv_badge_icon);
        this.mIconImageView.setImageDrawable(mIcon);
    }

    public void initializeAsActiveBadge() {
        this.mDropShadowCircleImageView.setImageDrawable(makeDropShadowCircle());
        this.mOuterCircleImageView.setImageDrawable(makeCircleWithColor(this.mBadgeSize, this.mBackgroundColor));
        this.mBorderCircleImageView.setImageDrawable(makeCircleWithColor(this.mBadgeSize - this.mBadgeBorderSize, this.mForegroundColor));
        this.mInnerCircleImageView.setImageDrawable(makeCircleWithColor(this.mBadgeSize - (this.mBadgeBorderSize * 2), this.mBackgroundColor));
    }

    public void initializeAsInactiveBadge() {
        ShapeDrawable dropShadowCircle = new ShapeDrawable(new OvalShape());
        dropShadowCircle.setIntrinsicHeight(this.mBadgeSizeWithDropShadow);
        dropShadowCircle.setIntrinsicWidth(this.mBadgeSizeWithDropShadow);
        dropShadowCircle.setColorFilter(this.mInvisibleColor, Mode.SRC_ATOP);
        this.mDropShadowCircleImageView.setImageDrawable(dropShadowCircle);
        this.mOuterCircleImageView.setImageDrawable(makeCircleWithColor(this.mBadgeSize, this.mInvisibleColor));
        this.mBorderCircleImageView.setImageDrawable(makeCircleWithColor(this.mBadgeSize - this.mBadgeBorderSize, this.mInactiveForegroundColor));
        this.mInnerCircleImageView.setImageDrawable(makeCircleWithColor(this.mBadgeSize - (this.mBadgeBorderSize * 2), this.mInvisibleColor));
    }

    public void animateActivation(boolean dropShadow) {
        TransitionDrawable outerTransition = makeActivationTransitionDrawable(this.mBadgeSize, this.mInvisibleColor, this.mBackgroundColor);
        this.mOuterCircleImageView.setImageDrawable(outerTransition);
        TransitionDrawable borderTransition = makeActivationTransitionDrawable(this.mBadgeSize - this.mBadgeBorderSize, this.mInactiveForegroundColor, this.mForegroundColor);
        this.mBorderCircleImageView.setImageDrawable(borderTransition);
        TransitionDrawable innerTransition = makeActivationTransitionDrawable(this.mBadgeSize - (this.mBadgeBorderSize * 2), this.mInvisibleColor, this.mBackgroundColor);
        this.mInnerCircleImageView.setImageDrawable(innerTransition);
        if (dropShadow) {
            animateShowDropShadow();
        }
        outerTransition.startTransition(1000);
        borderTransition.startTransition(1000);
        innerTransition.startTransition(1000);
        this.mIconImageView.setColorFilter(this.mForegroundColor);
    }

    private ShapeDrawable makeCircleWithColor(int size, int color) {
        ShapeDrawable shapeDrawable = new ShapeDrawable(new OvalShape());
        shapeDrawable.setIntrinsicHeight(size);
        shapeDrawable.setIntrinsicWidth(size);
        shapeDrawable.setColorFilter(color, Mode.SRC_ATOP);
        return shapeDrawable;
    }

    private GradientDrawable makeDropShadowCircle() {
        GradientDrawable dropShadowCircle = new GradientDrawable(Orientation.BL_TR, new int[]{AirMapInterface.CIRCLE_BORDER_COLOR, 0});
        dropShadowCircle.setGradientType(1);
        dropShadowCircle.setSize(this.mBadgeSizeWithDropShadow, this.mBadgeSizeWithDropShadow);
        dropShadowCircle.setGradientRadius(((float) this.mBadgeSizeWithDropShadow) * 0.5f);
        dropShadowCircle.setGradientCenter(0.5f, 0.5f);
        return dropShadowCircle;
    }

    private TransitionDrawable makeActivationTransitionDrawable(int size, int startColor, int endColor) {
        TransitionDrawable transition = new TransitionDrawable(new Drawable[]{makeCircleWithColor(size, startColor), makeCircleWithColor(size, endColor)});
        transition.setCrossFadeEnabled(false);
        return transition;
    }

    private void animateShowDropShadow() {
        this.mDropShadowCircleImageView.setImageDrawable(makeDropShadowCircle());
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(1000);
        this.mDropShadowCircleImageView.startAnimation(alphaAnimation);
    }
}
