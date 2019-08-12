package com.airbnb.p027n2.components;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.FillType;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

/* renamed from: com.airbnb.n2.components.BubbleDrawable */
public class BubbleDrawable extends Drawable {
    private int boxHeight;
    private Rect boxPadding = new Rect();
    private int boxWidth;
    private float cornerRadius;
    private Paint paint = new Paint();
    private int pointerCenter;
    private int pointerHeight;
    private int pointerWidth;

    public BubbleDrawable() {
        this.paint.setAntiAlias(true);
    }

    public void setFillColor(int fillColor) {
        this.paint.setColor(fillColor);
    }

    public void setPadding(int left, int top, int right, int bottom) {
        this.boxPadding.set(left, top, right, bottom);
    }

    public void setCornerRadius(float cornerRadius2) {
        this.cornerRadius = cornerRadius2;
    }

    public void setPointerWidth(int pointerWidth2) {
        this.pointerWidth = pointerWidth2;
    }

    public void setPointerHeight(int pointerHeight2) {
        this.pointerHeight = pointerHeight2;
    }

    public void setPointerCenter(int pointerCenter2) {
        this.pointerCenter = pointerCenter2;
    }

    public void draw(Canvas canvas) {
        canvas.drawRoundRect(new RectF(0.0f, 0.0f, (float) this.boxWidth, (float) this.boxHeight), this.cornerRadius, this.cornerRadius, this.paint);
        Path path = new Path();
        path.setFillType(FillType.EVEN_ODD);
        path.moveTo((float) (this.pointerCenter - (this.pointerWidth / 2)), (float) this.boxHeight);
        path.rLineTo((float) this.pointerWidth, 0.0f);
        path.rLineTo((float) (-(this.pointerWidth / 2)), (float) this.pointerHeight);
        path.rLineTo((float) (-(this.pointerWidth / 2)), (float) (-this.pointerHeight));
        path.close();
        canvas.drawPath(path, this.paint);
    }

    public void setAlpha(int alpha) {
    }

    public void setColorFilter(ColorFilter colorFilter) {
    }

    public int getOpacity() {
        return -1;
    }

    public boolean getPadding(Rect padding) {
        padding.set(this.boxPadding);
        padding.bottom += this.pointerHeight;
        return true;
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect bounds) {
        this.boxWidth = bounds.width();
        this.boxHeight = bounds.height() - this.pointerHeight;
        super.onBoundsChange(bounds);
    }
}
