package com.airbnb.android.lib.views;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.utils.ViewLibUtils;

public class ScrollingBackground extends View {
    private static final int DEFAULT_DRAWABLE = C0880R.C0881drawable.bg_belos;
    private static final int DEFAULT_SCROLL_SPEED = 7;
    private static final long DIRECTION_REVERSAL_ANIMATION_DURATION = 1000;
    private static final int FRAME_RATE = 60;
    private static final int MILLIS_PER_SECOND = 1000;
    private static final int REFRESH_RATE = 16;
    private int mColCount;
    private Drawable mDrawable;
    private int mDrawableHeight;
    private int mDrawableWidth;
    private long mLastScrollCalculation;
    private ValueAnimator mReverseScrollAnimator;
    private int mRowCount;
    private float mScrollPosition;
    /* access modifiers changed from: private */
    public float mScrollSpeedPixelsPerMilli;

    public ScrollingBackground(Context context) {
        super(context);
        setScrollSpeed(7);
        init();
    }

    public ScrollingBackground(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScrollingBackground(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, C0880R.styleable.ScrollingBackground, defStyleAttr, 0);
        Drawable d = ViewLibUtils.getDrawableCompat(context, a, C0880R.styleable.ScrollingBackground_src);
        if (d != null) {
            setDrawable(d);
        }
        setScrollSpeed(a.getInt(C0880R.styleable.ScrollingBackground_scrollSpeed, 7));
        a.recycle();
        init();
    }

    private void init() {
        if (this.mDrawable == null) {
            setDrawable(DEFAULT_DRAWABLE);
        }
        initReverseAnimator();
    }

    public void setScrollSpeed(int dpPerSecond) {
        if (this.mReverseScrollAnimator != null && this.mReverseScrollAnimator.isStarted()) {
            this.mReverseScrollAnimator.cancel();
        }
        this.mScrollSpeedPixelsPerMilli = TypedValue.applyDimension(1, (float) dpPerSecond, getResources().getDisplayMetrics()) / 1000.0f;
    }

    public void setDrawable(int drawableRes) {
        setDrawable(getResources().getDrawable(drawableRes));
    }

    public void setDrawable(Drawable drawable) {
        this.mDrawable = drawable;
        this.mDrawableHeight = this.mDrawable.getIntrinsicHeight();
        this.mDrawableWidth = this.mDrawable.getIntrinsicWidth();
        this.mDrawable.setBounds(0, 0, this.mDrawableWidth, this.mDrawableHeight);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        calculateNewScrollPosition();
        canvas.translate(0.0f, this.mScrollPosition - ((float) this.mDrawableHeight));
        for (int i = 0; i < this.mColCount; i++) {
            canvas.save();
            canvas.translate((float) (this.mDrawableWidth * i), 0.0f);
            for (int j = 0; j < this.mRowCount + 1; j++) {
                this.mDrawable.draw(canvas);
                canvas.translate(0.0f, (float) this.mDrawableHeight);
            }
            canvas.restore();
        }
        scheduleNextDraw();
    }

    private void scheduleNextDraw() {
        postDelayed(ScrollingBackground$$Lambda$1.lambdaFactory$(this), 16);
    }

    private void calculateNewScrollPosition() {
        long currentTime = System.currentTimeMillis();
        this.mScrollPosition += this.mScrollSpeedPixelsPerMilli * ((float) (currentTime - this.mLastScrollCalculation));
        if (this.mScrollPosition > ((float) this.mDrawableHeight)) {
            this.mScrollPosition %= (float) this.mDrawableHeight;
        } else if (this.mScrollPosition < 0.0f) {
            this.mScrollPosition = (this.mScrollPosition % ((float) this.mDrawableHeight)) + ((float) this.mDrawableHeight);
        }
        this.mLastScrollCalculation = currentTime;
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (changed) {
            this.mColCount = (int) Math.ceil(((double) getMeasuredWidth()) / ((double) this.mDrawableWidth));
            this.mRowCount = (int) Math.ceil(((double) getMeasuredHeight()) / ((double) this.mDrawableHeight));
        }
    }

    public void reverseScrollDirection() {
        if (this.mReverseScrollAnimator.isStarted()) {
            this.mReverseScrollAnimator.reverse();
            return;
        }
        this.mReverseScrollAnimator.setFloatValues(new float[]{this.mScrollSpeedPixelsPerMilli, this.mScrollSpeedPixelsPerMilli * -1.0f});
        this.mReverseScrollAnimator.start();
    }

    private void initReverseAnimator() {
        this.mReverseScrollAnimator = ValueAnimator.ofFloat(new float[0]).setDuration(1000);
        this.mReverseScrollAnimator.addUpdateListener(ScrollingBackground$$Lambda$2.lambdaFactory$(this));
    }
}
