package com.airbnb.p027n2.primitives;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.p000v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import com.airbnb.n2.R;

/* renamed from: com.airbnb.n2.primitives.StandardsBarContent */
public class StandardsBarContent extends View {
    private int filledSectionColor;
    private Paint progressPaint;
    private float progressPercentage;
    private RectF progressRect;
    private boolean showThresholdIndicator = true;
    private int thresholdIndicatorLineColor;
    private float thresholdIndicatorThicknessPx;
    private float thresholdPercentage;
    private int unfilledSectionColor;

    public StandardsBarContent(Context context) {
        super(context);
        init();
    }

    public StandardsBarContent(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public StandardsBarContent(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        this.progressPaint = new Paint();
        this.progressRect = new RectF();
        this.unfilledSectionColor = ContextCompat.getColor(getContext(), R.color.n2_background_gray);
        this.thresholdIndicatorLineColor = ContextCompat.getColor(getContext(), R.color.n2_hof);
        this.thresholdIndicatorThicknessPx = TypedValue.applyDimension(1, 2.0f, getResources().getDisplayMetrics());
    }

    public void setValue(float percentage) {
        if (percentage > 1.0f || percentage < 0.0f) {
            throw new IllegalArgumentException("value percentage must be between 0 and 1");
        }
        this.progressPercentage = percentage;
        invalidate();
    }

    public void setThreshold(float percentage) {
        if (percentage > 1.0f || percentage < 0.0f) {
            throw new IllegalArgumentException("value percentage must be between 0 and 1");
        }
        this.thresholdPercentage = percentage;
        invalidate();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        this.progressPaint.setColor(this.unfilledSectionColor);
        this.progressRect.set(0.0f, 0.0f, (float) getWidth(), (float) getHeight());
        canvas.drawRoundRect(this.progressRect, 5.0f, 5.0f, this.progressPaint);
        this.progressPaint.setColor(this.filledSectionColor);
        this.progressRect.set(0.0f, 0.0f, (float) ((int) (((float) getWidth()) * this.progressPercentage)), (float) getHeight());
        canvas.drawRoundRect(this.progressRect, 5.0f, 5.0f, this.progressPaint);
        if (this.showThresholdIndicator) {
            int indicatorPosition = getIndicatorPosition();
            this.progressPaint.setColor(this.thresholdIndicatorLineColor);
            this.progressPaint.setStrokeWidth(this.thresholdIndicatorThicknessPx);
            canvas.drawLine((float) indicatorPosition, 0.0f, (float) indicatorPosition, (float) getHeight(), this.progressPaint);
        }
    }

    private int getIndicatorPosition() {
        return (int) Math.max((float) ((int) Math.min((float) ((int) (((float) getWidth()) * this.thresholdPercentage)), ((float) getWidth()) - (this.thresholdIndicatorThicknessPx / 2.0f))), this.thresholdIndicatorThicknessPx / 2.0f);
    }

    public void setFilledSectionColor(int colorRes) {
        this.filledSectionColor = ContextCompat.getColor(getContext(), colorRes);
        invalidate();
    }

    public void setThresholdIndicatorVisible(boolean showThresholdIndicator2) {
        this.showThresholdIndicator = showThresholdIndicator2;
        invalidate();
    }
}
