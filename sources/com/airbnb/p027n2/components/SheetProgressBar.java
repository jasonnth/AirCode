package com.airbnb.p027n2.components;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.p000v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import com.airbnb.n2.R;

/* renamed from: com.airbnb.n2.components.SheetProgressBar */
public class SheetProgressBar extends View {
    private float progress;
    private ValueAnimator progressAnimator;
    private Paint progressPaint;
    private Paint transparentPaint;

    public SheetProgressBar(Context context) {
        super(context);
        init(null);
    }

    public SheetProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public SheetProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        setupPaint();
        setupAttributes(attrs);
    }

    private void setupPaint() {
        this.transparentPaint = new Paint();
        this.transparentPaint.setColor(ContextCompat.getColor(getContext(), R.color.n2_translucent_white));
        this.progressPaint = new Paint();
    }

    private void setupAttributes(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.n2_SheetProgressBar);
        int color = a.getColor(R.styleable.n2_SheetProgressBar_n2_color, getResources().getColor(R.color.n2_babu_dark));
        a.recycle();
        setColor(color);
    }

    public void setColor(int color) {
        this.progressPaint.setColor(color);
        invalidate();
    }

    public void setProgress(float progress2) {
        setProgress(progress2, false);
    }

    public void setProgress(float progress2, boolean animate) {
        if (animate) {
            if (this.progressAnimator != null) {
                this.progressAnimator.cancel();
            }
            this.progressAnimator = ValueAnimator.ofFloat(new float[]{this.progress, progress2});
            this.progressAnimator.setInterpolator(new LinearInterpolator());
            this.progressAnimator.setDuration((long) (1000.0f * Math.abs(progress2 - this.progress)));
            this.progressAnimator.addUpdateListener(SheetProgressBar$$Lambda$1.lambdaFactory$(this));
            this.progressAnimator.start();
            return;
        }
        this.progress = progress2;
        invalidate();
    }

    static /* synthetic */ void lambda$setProgress$0(SheetProgressBar sheetProgressBar, ValueAnimator animation) {
        sheetProgressBar.progress = ((Float) animation.getAnimatedValue()).floatValue();
        sheetProgressBar.invalidate();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        int progressEnd = (int) ((((float) getWidth()) * this.progress) / 1.0f);
        canvas.drawRect(0.0f, 0.0f, (float) progressEnd, (float) getHeight(), this.progressPaint);
        canvas.drawRect((float) progressEnd, 0.0f, (float) getWidth(), (float) getHeight(), this.transparentPaint);
    }

    public static void mock(SheetProgressBar view) {
        view.setProgress(0.66f);
    }
}
