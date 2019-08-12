package com.airbnb.android.lib.views;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.View.MeasureSpec;
import com.airbnb.android.lib.C0880R;

public class AirProgressBar extends View {
    private static final int DEFAULT_BAR_THICKNESS_DP = 4;
    private static final int DEFAULT_THRESHOLD_HEIGHT_DP = 17;
    private static final int DEFAULT_THRESHOLD_THICKNESS_DP = 4;
    private int mBarThicknessPx;
    private int mGray;
    private int mGreen;
    private int mHof;
    private int mIndicatorHeightPx;
    private int mIndicatorThicknessPx;
    private int mProgress;
    private Paint mProgressPaint;
    private int mThreshold;
    private boolean mThresholdReached;

    public AirProgressBar(Context context) {
        this(context, null);
    }

    public AirProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AirProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        Resources r = getResources();
        DisplayMetrics dm = r.getDisplayMetrics();
        this.mBarThicknessPx = (int) TypedValue.applyDimension(1, 4.0f, dm);
        this.mIndicatorThicknessPx = (int) TypedValue.applyDimension(1, 4.0f, dm);
        this.mIndicatorHeightPx = (int) TypedValue.applyDimension(1, 17.0f, dm);
        this.mProgressPaint = new Paint(1);
        this.mProgressPaint.setStyle(Style.STROKE);
        this.mGreen = r.getColor(C0880R.color.c_green);
        this.mGray = r.getColor(C0880R.color.c_gray_3);
        this.mHof = r.getColor(C0880R.color.c_hof);
        if (isInEditMode()) {
            setProgress(60);
            setThresholdPercentage(80);
        }
    }

    public void setProgress(int progress) {
        this.mProgress = Math.min(Math.max(progress, 0), 100);
        checkForThresholdReached();
        invalidate();
    }

    public void setThresholdPercentage(int threshold) {
        this.mThreshold = Math.min(Math.max(threshold, 0), 100);
        checkForThresholdReached();
        invalidate();
    }

    private void checkForThresholdReached() {
        this.mThresholdReached = this.mProgress >= this.mThreshold;
    }

    public int getIndicatorPosition() {
        return Math.max(Math.min((int) (((float) (getWidth() * this.mThreshold)) / 100.0f), getWidth() - (this.mIndicatorThicknessPx / 2)), this.mIndicatorThicknessPx / 2);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        int halfHeight = getHeight() / 2;
        int progressEndX = (int) (((float) (getWidth() * this.mProgress)) / 100.0f);
        this.mProgressPaint.setStrokeWidth((float) this.mBarThicknessPx);
        this.mProgressPaint.setColor(this.mThresholdReached ? this.mGreen : this.mHof);
        canvas.drawLine(0.0f, (float) halfHeight, (float) progressEndX, (float) halfHeight, this.mProgressPaint);
        this.mProgressPaint.setColor(this.mGray);
        canvas.drawLine((float) progressEndX, (float) halfHeight, (float) getWidth(), (float) halfHeight, this.mProgressPaint);
        int indicatorPosition = getIndicatorPosition();
        this.mProgressPaint.setColor(this.mGreen);
        this.mProgressPaint.setStrokeWidth((float) this.mIndicatorThicknessPx);
        canvas.drawLine((float) indicatorPosition, (float) (halfHeight - (this.mIndicatorHeightPx / 2)), (float) indicatorPosition, (float) ((this.mIndicatorHeightPx / 2) + halfHeight), this.mProgressPaint);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        float height;
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        float heightSize = (float) MeasureSpec.getSize(heightMeasureSpec);
        float width = (float) MeasureSpec.getSize(widthMeasureSpec);
        switch (MeasureSpec.getMode(heightMeasureSpec)) {
            case Integer.MIN_VALUE:
                height = Math.min((float) this.mIndicatorHeightPx, heightSize);
                break;
            case 1073741824:
                height = heightSize;
                break;
            default:
                height = (float) this.mIndicatorHeightPx;
                break;
        }
        setMeasuredDimension((int) width, (int) height);
    }
}
