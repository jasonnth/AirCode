package com.jumio.sdk.gui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;
import com.jumio.core.C4404R;

public class CircleView extends View {
    private static final int DEFAULT_FILL_COLOR = 0;

    /* renamed from: h */
    float f3552h;
    private int mFillColor;
    private Paint mFillPaint;
    float radius;

    /* renamed from: w */
    float f3553w;

    public CircleView(Context context) {
        super(context);
        init(null, 0);
    }

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public CircleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, C4404R.styleable.CircleView, defStyle, 0);
        this.mFillColor = a.getColor(C4404R.styleable.CircleView_jumio_fillColor, 0);
        a.recycle();
        this.mFillPaint = new Paint();
        this.mFillPaint.setFlags(1);
        this.mFillPaint.setStyle(Style.FILL);
        this.mFillPaint.setColor(this.mFillColor);
    }

    private void invalidatePaints() {
        this.mFillPaint.setColor(this.mFillColor);
        invalidate();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.f3553w = (float) getMeasuredWidth();
        this.f3552h = (float) getMeasuredHeight();
        this.radius = Math.min(this.f3553w, this.f3552h) / 2.0f;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(this.f3553w / 2.0f, this.f3552h / 2.0f, this.radius, this.mFillPaint);
    }

    public void setFillColor(int fillColor) {
        this.mFillColor = fillColor;
        invalidatePaints();
    }
}
