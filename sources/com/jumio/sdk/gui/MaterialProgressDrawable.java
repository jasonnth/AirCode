package com.jumio.sdk.gui;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.FillType;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;

public class MaterialProgressDrawable extends Drawable implements Animatable {
    private static final int ANIMATION_DURATION = 1333;
    private static final int ARROW_HEIGHT = 5;
    static final int ARROW_HEIGHT_LARGE = 6;
    private static final float ARROW_OFFSET_ANGLE = 0.0f;
    private static final int ARROW_WIDTH = 10;
    static final int ARROW_WIDTH_LARGE = 12;
    private static final float CENTER_RADIUS = 8.75f;
    private static final float CENTER_RADIUS_LARGE = 12.5f;
    private static final int CIRCLE_DIAMETER = 40;
    private static final int CIRCLE_DIAMETER_LARGE = 56;
    public static final int DEFAULT = 1;
    private static final Interpolator EASE_INTERPOLATOR = new AccelerateDecelerateInterpolator();
    /* access modifiers changed from: private */
    public static final Interpolator END_CURVE_INTERPOLATOR = new EndCurveInterpolator();
    public static final int LARGE = 0;
    private static final Interpolator LINEAR_INTERPOLATOR = new LinearInterpolator();
    private static final float MAX_PROGRESS_ARC = 0.8f;
    private static final float NUM_POINTS = 5.0f;
    /* access modifiers changed from: private */
    public static final Interpolator START_CURVE_INTERPOLATOR = new StartCurveInterpolator();
    private static final float STROKE_WIDTH = 2.5f;
    static final float STROKE_WIDTH_LARGE = 3.0f;
    private final int[] COLORS = {-16777216};
    private View mAnimExcutor;
    private Animation mAnimation;
    private final ArrayList<Animation> mAnimators = new ArrayList<>();
    private final Callback mCallback = new Callback() {
        public void invalidateDrawable(Drawable d) {
            MaterialProgressDrawable.this.invalidateSelf();
        }

        public void scheduleDrawable(Drawable d, Runnable what, long when) {
            MaterialProgressDrawable.this.scheduleSelf(what, when);
        }

        public void unscheduleDrawable(Drawable d, Runnable what) {
            MaterialProgressDrawable.this.unscheduleSelf(what);
        }
    };
    boolean mFinishing;
    private double mHeight;
    private Resources mResources;
    private final Ring mRing;
    private float mRotation;
    /* access modifiers changed from: private */
    public float mRotationCount;
    private double mWidth;

    private static class EndCurveInterpolator extends AccelerateDecelerateInterpolator {
        private EndCurveInterpolator() {
        }

        public float getInterpolation(float input) {
            return super.getInterpolation(Math.max(MaterialProgressDrawable.ARROW_OFFSET_ANGLE, (input - 0.5f) * 2.0f));
        }
    }

    @Retention(RetentionPolicy.CLASS)
    public @interface ProgressDrawableSize {
    }

    private static class Ring {
        private int mAlpha;
        private Path mArrow;
        private int mArrowHeight;
        private final Paint mArrowPaint = new Paint();
        private float mArrowScale;
        private int mArrowWidth;
        private int mBackgroundColor;
        private final Callback mCallback;
        private final Paint mCirclePaint = new Paint();
        private int mColorIndex;
        private int[] mColors;
        private float mEndTrim = MaterialProgressDrawable.ARROW_OFFSET_ANGLE;
        private final Paint mPaint = new Paint();
        private double mRingCenterRadius;
        private float mRotation = MaterialProgressDrawable.ARROW_OFFSET_ANGLE;
        private boolean mShowArrow;
        private float mStartTrim = MaterialProgressDrawable.ARROW_OFFSET_ANGLE;
        private float mStartingEndTrim;
        private float mStartingRotation;
        private float mStartingStartTrim;
        private float mStrokeInset = MaterialProgressDrawable.STROKE_WIDTH;
        private float mStrokeWidth = MaterialProgressDrawable.NUM_POINTS;
        private final RectF mTempBounds = new RectF();

        public Ring(Callback callback) {
            this.mCallback = callback;
            this.mPaint.setStrokeCap(Cap.SQUARE);
            this.mPaint.setAntiAlias(true);
            this.mPaint.setStyle(Style.STROKE);
            this.mArrowPaint.setStyle(Style.FILL);
            this.mArrowPaint.setAntiAlias(true);
        }

        public void setBackgroundColor(int color) {
            this.mBackgroundColor = color;
        }

        public void setArrowDimensions(float width, float height) {
            this.mArrowWidth = (int) width;
            this.mArrowHeight = (int) height;
        }

        public void draw(Canvas c, Rect bounds) {
            RectF arcBounds = this.mTempBounds;
            arcBounds.set(bounds);
            arcBounds.inset(this.mStrokeInset, this.mStrokeInset);
            float startAngle = (this.mStartTrim + this.mRotation) * 360.0f;
            float sweepAngle = ((this.mEndTrim + this.mRotation) * 360.0f) - startAngle;
            this.mPaint.setColor(this.mColors[this.mColorIndex]);
            c.drawArc(arcBounds, startAngle, sweepAngle, false, this.mPaint);
            drawTriangle(c, startAngle, sweepAngle, bounds);
            if (this.mAlpha < 255) {
                this.mCirclePaint.setColor(this.mBackgroundColor);
                this.mCirclePaint.setAlpha(255 - this.mAlpha);
                c.drawCircle(bounds.exactCenterX(), bounds.exactCenterY(), ((float) bounds.width()) / 2.0f, this.mCirclePaint);
            }
        }

        private void drawTriangle(Canvas c, float startAngle, float sweepAngle, Rect bounds) {
            if (this.mShowArrow) {
                if (this.mArrow == null) {
                    this.mArrow = new Path();
                    this.mArrow.setFillType(FillType.EVEN_ODD);
                } else {
                    this.mArrow.reset();
                }
                float x = (float) ((this.mRingCenterRadius * Math.cos(0.0d)) + ((double) bounds.exactCenterX()));
                float y = (float) ((this.mRingCenterRadius * Math.sin(0.0d)) + ((double) bounds.exactCenterY()));
                this.mArrow.moveTo(MaterialProgressDrawable.ARROW_OFFSET_ANGLE, MaterialProgressDrawable.ARROW_OFFSET_ANGLE);
                this.mArrow.lineTo(((float) this.mArrowWidth) * this.mArrowScale, MaterialProgressDrawable.ARROW_OFFSET_ANGLE);
                this.mArrow.lineTo((((float) this.mArrowWidth) * this.mArrowScale) / 2.0f, ((float) this.mArrowHeight) * this.mArrowScale);
                this.mArrow.offset(x - ((((float) this.mArrowWidth) * this.mArrowScale) / 2.0f), y);
                this.mArrow.close();
                this.mArrowPaint.setColor(this.mColors[this.mColorIndex]);
                if (sweepAngle < MaterialProgressDrawable.ARROW_OFFSET_ANGLE) {
                    sweepAngle = 0.0f;
                }
                c.rotate((startAngle + sweepAngle) - MaterialProgressDrawable.ARROW_OFFSET_ANGLE, bounds.exactCenterX(), bounds.exactCenterY());
                c.drawPath(this.mArrow, this.mArrowPaint);
            }
        }

        public void setColors(int[] colors) {
            this.mColors = colors;
            setColorIndex(0);
        }

        public void setColorIndex(int index) {
            this.mColorIndex = index;
        }

        public void goToNextColor() {
            this.mColorIndex = (this.mColorIndex + 1) % this.mColors.length;
        }

        public void setColorFilter(ColorFilter filter) {
            this.mPaint.setColorFilter(filter);
            invalidateSelf();
        }

        public int getAlpha() {
            return this.mAlpha;
        }

        public void setAlpha(int alpha) {
            this.mAlpha = alpha;
        }

        public float getStrokeWidth() {
            return this.mStrokeWidth;
        }

        public void setStrokeWidth(float strokeWidth) {
            this.mStrokeWidth = strokeWidth;
            this.mPaint.setStrokeWidth(strokeWidth);
            invalidateSelf();
        }

        public float getStartTrim() {
            return this.mStartTrim;
        }

        public void setStartTrim(float startTrim) {
            this.mStartTrim = startTrim;
            invalidateSelf();
        }

        public float getStartingStartTrim() {
            return this.mStartingStartTrim;
        }

        public float getStartingEndTrim() {
            return this.mStartingEndTrim;
        }

        public float getEndTrim() {
            return this.mEndTrim;
        }

        public void setEndTrim(float endTrim) {
            this.mEndTrim = endTrim;
            invalidateSelf();
        }

        public float getRotation() {
            return this.mRotation;
        }

        public void setRotation(float rotation) {
            this.mRotation = rotation;
            invalidateSelf();
        }

        public void setInsets(int width, int height) {
            float insets;
            float minEdge = (float) Math.min(width, height);
            if (this.mRingCenterRadius <= 0.0d || minEdge < MaterialProgressDrawable.ARROW_OFFSET_ANGLE) {
                insets = (float) Math.ceil((double) (this.mStrokeWidth / 2.0f));
            } else {
                insets = (float) (((double) (minEdge / 2.0f)) - this.mRingCenterRadius);
            }
            this.mStrokeInset = insets;
        }

        public float getInsets() {
            return this.mStrokeInset;
        }

        public double getCenterRadius() {
            return this.mRingCenterRadius;
        }

        public void setCenterRadius(double centerRadius) {
            this.mRingCenterRadius = centerRadius;
        }

        public void setShowArrow(boolean show) {
            if (this.mShowArrow != show) {
                this.mShowArrow = show;
                invalidateSelf();
            }
        }

        public void setArrowScale(float scale) {
            if (scale != this.mArrowScale) {
                this.mArrowScale = scale;
                invalidateSelf();
            }
        }

        public float getStartingRotation() {
            return this.mStartingRotation;
        }

        public void storeOriginals() {
            this.mStartingStartTrim = this.mStartTrim;
            this.mStartingEndTrim = this.mEndTrim;
            this.mStartingRotation = this.mRotation;
        }

        public void resetOriginals() {
            this.mStartingStartTrim = MaterialProgressDrawable.ARROW_OFFSET_ANGLE;
            this.mStartingEndTrim = MaterialProgressDrawable.ARROW_OFFSET_ANGLE;
            this.mStartingRotation = MaterialProgressDrawable.ARROW_OFFSET_ANGLE;
            setStartTrim(MaterialProgressDrawable.ARROW_OFFSET_ANGLE);
            setEndTrim(MaterialProgressDrawable.ARROW_OFFSET_ANGLE);
            setRotation(MaterialProgressDrawable.ARROW_OFFSET_ANGLE);
        }

        private void invalidateSelf() {
            this.mCallback.invalidateDrawable(null);
        }
    }

    private static class StartCurveInterpolator extends AccelerateDecelerateInterpolator {
        private StartCurveInterpolator() {
        }

        public float getInterpolation(float input) {
            return super.getInterpolation(Math.min(1.0f, 2.0f * input));
        }
    }

    public MaterialProgressDrawable(Context context, View animExcutor) {
        this.mAnimExcutor = animExcutor;
        this.mResources = context.getResources();
        this.mRing = new Ring(this.mCallback);
        this.mRing.setColors(this.COLORS);
        updateSizes(1);
        setupAnimators();
    }

    public void setSizeParameters(double progressCircleWidth, double progressCircleHeight, double centerRadius, double strokeWidth, float arrowWidth, float arrowHeight) {
        Ring ring = this.mRing;
        this.mWidth = progressCircleWidth;
        this.mHeight = progressCircleHeight;
        ring.setStrokeWidth((float) strokeWidth);
        ring.setCenterRadius(centerRadius);
        ring.setColorIndex(0);
        ring.setArrowDimensions(arrowWidth, arrowHeight);
        ring.setInsets((int) this.mWidth, (int) this.mHeight);
    }

    public void updateSizes(@ProgressDrawableSize int size) {
        float screenDensity = this.mResources.getDisplayMetrics().density;
        if (size == 0) {
            setSizeParameters((double) (56.0f * screenDensity), (double) (56.0f * screenDensity), (double) (CENTER_RADIUS_LARGE * screenDensity), (double) (STROKE_WIDTH_LARGE * screenDensity), 12.0f * screenDensity, 6.0f * screenDensity);
            return;
        }
        setSizeParameters((double) (40.0f * screenDensity), (double) (40.0f * screenDensity), (double) (CENTER_RADIUS * screenDensity), (double) (STROKE_WIDTH * screenDensity), 10.0f * screenDensity, NUM_POINTS * screenDensity);
    }

    public void showArrow(boolean show) {
        this.mRing.setShowArrow(show);
    }

    public void setArrowScale(float scale) {
        this.mRing.setArrowScale(scale);
    }

    public void setStartEndTrim(float startAngle, float endAngle) {
        this.mRing.setStartTrim(startAngle);
        this.mRing.setEndTrim(endAngle);
    }

    public void setProgressRotation(float rotation) {
        this.mRing.setRotation(rotation);
    }

    public void setBackgroundColor(int color) {
        this.mRing.setBackgroundColor(color);
    }

    public void setColorSchemeColors(int... colors) {
        this.mRing.setColors(colors);
        this.mRing.setColorIndex(0);
    }

    public int getIntrinsicHeight() {
        return (int) this.mHeight;
    }

    public int getIntrinsicWidth() {
        return (int) this.mWidth;
    }

    public void draw(Canvas c) {
        Rect bounds = getBounds();
        int saveCount = c.save();
        c.rotate(this.mRotation, bounds.exactCenterX(), bounds.exactCenterY());
        this.mRing.draw(c, bounds);
        c.restoreToCount(saveCount);
    }

    public int getAlpha() {
        return this.mRing.getAlpha();
    }

    public void setAlpha(int alpha) {
        this.mRing.setAlpha(alpha);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.mRing.setColorFilter(colorFilter);
    }

    private float getRotation() {
        return this.mRotation;
    }

    /* access modifiers changed from: 0000 */
    public void setRotation(float rotation) {
        this.mRotation = rotation;
        invalidateSelf();
    }

    public int getOpacity() {
        return -3;
    }

    public boolean isRunning() {
        ArrayList<Animation> animators = this.mAnimators;
        int N = animators.size();
        for (int i = 0; i < N; i++) {
            Animation animator = (Animation) animators.get(i);
            if (animator.hasStarted() && !animator.hasEnded()) {
                return true;
            }
        }
        return false;
    }

    public void start() {
        this.mAnimation.reset();
        this.mRing.storeOriginals();
        if (this.mRing.getEndTrim() != this.mRing.getStartTrim()) {
            this.mFinishing = true;
            this.mAnimation.setDuration(666);
            this.mAnimExcutor.startAnimation(this.mAnimation);
            return;
        }
        this.mRing.setColorIndex(0);
        this.mRing.resetOriginals();
        this.mAnimation.setDuration(1333);
        this.mAnimExcutor.startAnimation(this.mAnimation);
    }

    public void stop() {
        this.mAnimExcutor.clearAnimation();
        setRotation(ARROW_OFFSET_ANGLE);
        this.mRing.setShowArrow(false);
        this.mRing.setColorIndex(0);
        this.mRing.resetOriginals();
    }

    /* access modifiers changed from: private */
    public void applyFinishTranslation(float interpolatedTime, Ring ring) {
        float targetRotation = (float) (Math.floor((double) (ring.getStartingRotation() / MAX_PROGRESS_ARC)) + 1.0d);
        ring.setStartTrim(ring.getStartingStartTrim() + ((ring.getStartingEndTrim() - ring.getStartingStartTrim()) * interpolatedTime));
        ring.setRotation(ring.getStartingRotation() + ((targetRotation - ring.getStartingRotation()) * interpolatedTime));
    }

    private void setupAnimators() {
        final Ring ring = this.mRing;
        Animation animation = new Animation() {
            public void applyTransformation(float interpolatedTime, Transformation t) {
                if (MaterialProgressDrawable.this.mFinishing) {
                    MaterialProgressDrawable.this.applyFinishTranslation(interpolatedTime, ring);
                    return;
                }
                float minProgressArc = (float) Math.toRadians(((double) ring.getStrokeWidth()) / (6.283185307179586d * ring.getCenterRadius()));
                float startingEndTrim = ring.getStartingEndTrim();
                float startingTrim = ring.getStartingStartTrim();
                float startingRotation = ring.getStartingRotation();
                float endTrim = startingEndTrim + (MaterialProgressDrawable.START_CURVE_INTERPOLATOR.getInterpolation(interpolatedTime) * (MaterialProgressDrawable.MAX_PROGRESS_ARC - minProgressArc));
                float startTrim = startingTrim + (MaterialProgressDrawable.MAX_PROGRESS_ARC * MaterialProgressDrawable.END_CURVE_INTERPOLATOR.getInterpolation(interpolatedTime));
                if (Math.abs(endTrim - startTrim) >= 1.0f) {
                    endTrim = startTrim + 0.5f;
                }
                ring.setEndTrim(endTrim);
                ring.setStartTrim(startTrim);
                ring.setRotation(startingRotation + (0.25f * interpolatedTime));
                MaterialProgressDrawable.this.setRotation((144.0f * interpolatedTime) + (720.0f * (MaterialProgressDrawable.this.mRotationCount / MaterialProgressDrawable.NUM_POINTS)));
            }
        };
        animation.setRepeatCount(-1);
        animation.setRepeatMode(1);
        animation.setInterpolator(LINEAR_INTERPOLATOR);
        animation.setAnimationListener(new AnimationListener() {
            public void onAnimationStart(Animation animation) {
                MaterialProgressDrawable.this.mRotationCount = MaterialProgressDrawable.ARROW_OFFSET_ANGLE;
            }

            public void onAnimationEnd(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
                ring.storeOriginals();
                ring.goToNextColor();
                ring.setStartTrim(ring.getEndTrim());
                if (MaterialProgressDrawable.this.mFinishing) {
                    MaterialProgressDrawable.this.mFinishing = false;
                    animation.setDuration(1333);
                    ring.setShowArrow(false);
                    return;
                }
                MaterialProgressDrawable.this.mRotationCount = (MaterialProgressDrawable.this.mRotationCount + 1.0f) % MaterialProgressDrawable.NUM_POINTS;
            }
        });
        this.mAnimation = animation;
    }
}
