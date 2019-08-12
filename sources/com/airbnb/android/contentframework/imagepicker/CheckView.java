package com.airbnb.android.contentframework.imagepicker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Typeface;
import android.support.p000v4.content.ContextCompat;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import butterknife.BindDimen;
import butterknife.ButterKnife;
import com.airbnb.android.contentframework.C5709R;

public class CheckView extends View {
    public static final int UNCHECKED = Integer.MIN_VALUE;
    private Paint backgroundPaint;
    @BindDimen
    float checkViewSizePx;
    private int checkedNum;
    private Paint strokePaint;
    @BindDimen
    float strokeWidthPx;
    private TextPaint textPaint;
    @BindDimen
    int textSize;

    public CheckView(Context context) {
        super(context);
        init();
    }

    public CheckView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CheckView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        ButterKnife.bind((View) this);
        this.strokePaint = new Paint();
        this.strokePaint.setAntiAlias(true);
        this.strokePaint.setStyle(Style.STROKE);
        this.strokePaint.setXfermode(new PorterDuffXfermode(Mode.SRC_OVER));
        this.strokePaint.setStrokeWidth(this.strokeWidthPx);
        this.strokePaint.setColor(-1);
    }

    public void setCheckedNum(int checkedNum2) {
        if (checkedNum2 == Integer.MIN_VALUE || checkedNum2 > 0) {
            this.checkedNum = checkedNum2;
            invalidate();
            return;
        }
        throw new IllegalArgumentException("checked num can't be negative.");
    }

    public void setEnabled(boolean enabled) {
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(this.checkViewSizePx / 2.0f, this.checkViewSizePx / 2.0f, (this.checkViewSizePx - this.strokeWidthPx) / 2.0f, this.strokePaint);
        if (this.checkedNum != Integer.MIN_VALUE) {
            initBackgroundPaint();
            canvas.drawCircle(this.checkViewSizePx / 2.0f, this.checkViewSizePx / 2.0f, (this.checkViewSizePx / 2.0f) - this.strokeWidthPx, this.backgroundPaint);
            initTextPaint();
            String text = String.valueOf(this.checkedNum);
            canvas.drawText(text, (float) (((int) (((float) canvas.getWidth()) - this.textPaint.measureText(text))) / 2), (float) (((int) ((((float) canvas.getHeight()) - this.textPaint.descent()) - this.textPaint.ascent())) / 2), this.textPaint);
        }
    }

    private void initBackgroundPaint() {
        if (this.backgroundPaint == null) {
            this.backgroundPaint = new Paint();
            this.backgroundPaint.setAntiAlias(true);
            this.backgroundPaint.setStyle(Style.FILL);
            this.backgroundPaint.setColor(ContextCompat.getColor(getContext(), C5709R.color.n2_babu));
        }
    }

    private void initTextPaint() {
        if (this.textPaint == null) {
            this.textPaint = new TextPaint();
            this.textPaint.setAntiAlias(true);
            this.textPaint.setColor(-1);
            this.textPaint.setTypeface(Typeface.create(Typeface.DEFAULT, 1));
            this.textPaint.setTextSize((float) this.textSize);
        }
    }
}
