package com.jumio.sdk.gui;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RadialGradient;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.net.Uri;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;
import com.airbnb.android.airmapview.AirMapInterface;

public class MaterialProgressBar extends ImageView {
    private static final int DEFAULT_CIRCLE_BG_LIGHT = -328966;
    private static final int DEFAULT_CIRCLE_DIAMETER = 56;
    public static final int DEFAULT_TEXT_SIZE = 9;
    private static final int FILL_SHADOW_COLOR = 1023410176;
    private static final int KEY_SHADOW_COLOR = 503316480;
    private static final int SHADOW_ELEVATION = 4;
    private static final float SHADOW_RADIUS = 3.5f;
    private static final int STROKE_WIDTH_LARGE = 3;
    private static final float X_OFFSET = 0.0f;
    private static final float Y_OFFSET = 1.75f;
    private int mArrowHeight;
    private int mArrowWidth;
    private int mBackGroundColor;
    private ShapeDrawable mBgCircle;
    private boolean mCircleBackgroundEnabled;
    private int[] mColors = {-16777216};
    private int mDiameter;
    private boolean mIfDrawText;
    private int mInnerRadius;
    private AnimationListener mListener;
    private int mMax;
    private int mProgress;
    private int mProgressColor;
    private MaterialProgressDrawable mProgressDrawable;
    private int mProgressStokeWidth;
    private int mShadowRadius;
    private boolean mShowArrow;
    private int mTextColor;
    private Paint mTextPaint;
    private int mTextSize;

    private class OvalShadow extends OvalShape {
        private int mCircleDiameter;
        private RadialGradient mRadialGradient;
        private Paint mShadowPaint = new Paint();
        private int mShadowRadius;

        public OvalShadow(int shadowRadius, int circleDiameter) {
            this.mShadowRadius = shadowRadius;
            this.mCircleDiameter = circleDiameter;
            this.mRadialGradient = new RadialGradient(((float) this.mCircleDiameter) / 2.0f, ((float) this.mCircleDiameter) / 2.0f, (float) this.mShadowRadius, new int[]{MaterialProgressBar.FILL_SHADOW_COLOR, 0}, null, TileMode.CLAMP);
            this.mShadowPaint.setShader(this.mRadialGradient);
        }

        public void draw(Canvas canvas, Paint paint) {
            int viewWidth = MaterialProgressBar.this.getWidth();
            int viewHeight = MaterialProgressBar.this.getHeight();
            canvas.drawCircle(((float) viewWidth) / 2.0f, ((float) viewHeight) / 2.0f, (((float) this.mCircleDiameter) / 2.0f) + ((float) this.mShadowRadius), this.mShadowPaint);
            canvas.drawCircle(((float) viewWidth) / 2.0f, ((float) viewHeight) / 2.0f, ((float) this.mCircleDiameter) / 2.0f, paint);
        }
    }

    public MaterialProgressBar(Context context) {
        super(context);
        init();
    }

    public MaterialProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MaterialProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        float density = getContext().getResources().getDisplayMetrics().density;
        this.mBackGroundColor = DEFAULT_CIRCLE_BG_LIGHT;
        this.mProgressColor = DEFAULT_CIRCLE_BG_LIGHT;
        this.mInnerRadius = -1;
        this.mProgressStokeWidth = (int) (3.0f * density);
        this.mArrowWidth = -1;
        this.mArrowHeight = -1;
        this.mTextSize = (int) (9.0f * density);
        this.mTextColor = AirMapInterface.CIRCLE_BORDER_COLOR;
        this.mShowArrow = false;
        this.mCircleBackgroundEnabled = false;
        this.mProgress = 0;
        this.mMax = 100;
        this.mIfDrawText = false;
        this.mTextPaint = new Paint();
        this.mTextPaint.setStyle(Style.FILL);
        this.mTextPaint.setColor(this.mTextColor);
        this.mTextPaint.setTextSize((float) this.mTextSize);
        this.mTextPaint.setAntiAlias(true);
        this.mProgressDrawable = new MaterialProgressDrawable(getContext(), this);
        super.setImageDrawable(this.mProgressDrawable);
    }

    private boolean elevationSupported() {
        return VERSION.SDK_INT >= 21;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        float density = getContext().getResources().getDisplayMetrics().density;
        this.mDiameter = Math.min(getMeasuredWidth(), getMeasuredHeight());
        if (this.mDiameter <= 0) {
            this.mDiameter = ((int) density) * 56;
        }
        if (changed) {
            this.mProgressDrawable.setBackgroundColor(this.mBackGroundColor);
            this.mProgressDrawable.setColorSchemeColors(this.mColors);
            this.mProgressDrawable.setSizeParameters((double) this.mDiameter, (double) this.mDiameter, this.mInnerRadius <= 0 ? ((double) (this.mDiameter - (this.mProgressStokeWidth * 2))) / 2.0d : (double) this.mInnerRadius, (double) this.mProgressStokeWidth, this.mArrowWidth < 0 ? (float) (this.mProgressStokeWidth * 4) : (float) this.mArrowWidth, this.mArrowHeight < 0 ? (float) (this.mProgressStokeWidth * 2) : (float) this.mArrowHeight);
            if (isShowArrow()) {
                this.mProgressDrawable.setArrowScale(1.0f);
                this.mProgressDrawable.showArrow(true);
            }
            super.setImageDrawable(null);
            super.setImageDrawable(this.mProgressDrawable);
            this.mProgressDrawable.setAlpha(255);
            this.mProgressDrawable.start();
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.mIfDrawText) {
            String text = String.format("%s%%", new Object[]{Integer.valueOf(this.mProgress)});
            canvas.drawText(text, (float) ((getWidth() / 2) - ((text.length() * this.mTextSize) / 4)), (float) ((getHeight() / 2) + (this.mTextSize / 4)), this.mTextPaint);
        }
    }

    public final void setImageResource(int resId) {
    }

    public boolean isShowArrow() {
        return this.mShowArrow;
    }

    public void setShowArrow(boolean showArrow) {
        this.mShowArrow = showArrow;
    }

    public final void setImageURI(Uri uri) {
        super.setImageURI(uri);
    }

    public final void setImageDrawable(Drawable drawable) {
    }

    public void setAnimationListener(AnimationListener listener) {
        this.mListener = listener;
    }

    public void onAnimationStart() {
        super.onAnimationStart();
        if (this.mListener != null) {
            this.mListener.onAnimationStart(getAnimation());
        }
    }

    public void onAnimationEnd() {
        super.onAnimationEnd();
        if (this.mListener != null) {
            this.mListener.onAnimationEnd(getAnimation());
        }
    }

    public void setColorSchemeResources(int... colorResIds) {
        Resources res = getResources();
        int[] colorRes = new int[colorResIds.length];
        for (int i = 0; i < colorResIds.length; i++) {
            colorRes[i] = res.getColor(colorResIds[i]);
        }
        setColorSchemeColors(colorRes);
    }

    public void setColorSchemeColors(int... colors) {
        this.mColors = colors;
        if (this.mProgressDrawable != null) {
            this.mProgressDrawable.setColorSchemeColors(colors);
        }
    }

    public void setBackgroundColor(int colorRes) {
        if (getBackground() instanceof ShapeDrawable) {
            ((ShapeDrawable) getBackground()).getPaint().setColor(getResources().getColor(colorRes));
        }
    }

    public boolean isShowProgressText() {
        return this.mIfDrawText;
    }

    public void setShowProgressText(boolean mIfDrawText2) {
        this.mIfDrawText = mIfDrawText2;
    }

    public int getMax() {
        return this.mMax;
    }

    public void setMax(int max) {
        this.mMax = max;
    }

    public int getProgress() {
        return this.mProgress;
    }

    public void setProgress(int progress) {
        if (getMax() > 0) {
            this.mProgress = progress;
        }
    }

    public boolean circleBackgroundEnabled() {
        return this.mCircleBackgroundEnabled;
    }

    public void setCircleBackgroundEnabled(boolean enableCircleBackground) {
        this.mCircleBackgroundEnabled = enableCircleBackground;
    }

    public void setProgressStokeWidth(int progressStokeWidth) {
        this.mProgressStokeWidth = progressStokeWidth;
    }

    public int getVisibility() {
        return super.getVisibility();
    }

    public void setVisibility(int visibility) {
        boolean z;
        super.setVisibility(visibility);
        if (this.mProgressDrawable != null) {
            if (visibility == 0) {
                this.mProgressDrawable.start();
            } else {
                this.mProgressDrawable.stop();
            }
            MaterialProgressDrawable materialProgressDrawable = this.mProgressDrawable;
            if (visibility == 0) {
                z = true;
            } else {
                z = false;
            }
            materialProgressDrawable.setVisible(z, false);
        }
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        boolean z;
        super.onAttachedToWindow();
        if (this.mProgressDrawable != null) {
            this.mProgressDrawable.stop();
            MaterialProgressDrawable materialProgressDrawable = this.mProgressDrawable;
            if (getVisibility() == 0) {
                z = true;
            } else {
                z = false;
            }
            materialProgressDrawable.setVisible(z, false);
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mProgressDrawable != null) {
            this.mProgressDrawable.stop();
            this.mProgressDrawable.setVisible(false, false);
        }
    }
}
