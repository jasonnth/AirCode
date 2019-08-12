package com.airbnb.p027n2.utils;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;

/* renamed from: com.airbnb.n2.utils.CenterDrawableWrapper */
public class CenterDrawableWrapper extends Drawable {
    private final Drawable drawable;

    public CenterDrawableWrapper(Drawable drawable2) {
        this.drawable = drawable2;
    }

    public void draw(Canvas canvas) {
        int canvasWidth = canvas.getWidth();
        int canvasHeight = canvas.getHeight();
        int drawableWidth = this.drawable.getIntrinsicWidth();
        int drawableHeight = this.drawable.getIntrinsicHeight();
        int startX = (canvasWidth / 2) - (drawableWidth / 2);
        int startY = (canvasHeight / 2) - (drawableHeight / 2);
        this.drawable.setBounds(startX, startY, startX + drawableWidth, startY + drawableHeight);
        this.drawable.draw(canvas);
    }

    public void setAlpha(int i) {
        this.drawable.setAlpha(i);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.drawable.setColorFilter(colorFilter);
    }

    public int getOpacity() {
        return this.drawable.getOpacity();
    }
}
