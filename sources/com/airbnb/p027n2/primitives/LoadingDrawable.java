package com.airbnb.p027n2.primitives;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;

/* renamed from: com.airbnb.n2.primitives.LoadingDrawable */
public class LoadingDrawable extends Drawable {
    private int maxHeight = Integer.MAX_VALUE;
    private int maxWidth = Integer.MAX_VALUE;
    private final Paint paint = new Paint();

    public LoadingDrawable() {
        init();
    }

    public LoadingDrawable(int maxWidth2, int maxHeight2) {
        init();
        this.maxWidth = maxWidth2;
        this.maxHeight = maxHeight2;
    }

    private void init() {
        this.paint.setStyle(Style.FILL);
        this.paint.setAntiAlias(true);
        setColor(-1);
    }

    public void draw(Canvas canvas) {
        int width = Math.min(this.maxWidth, canvas.getWidth());
        int radius = Math.min(Math.min(this.maxHeight, canvas.getHeight()) / 2, ((int) (((float) width) / 4.8f)) / 2);
        int cy = canvas.getHeight() / 2;
        int spaceBetweenDots = (int) (((float) (radius * 2)) * 0.9f);
        double theta1 = ((double) ((int) (((double) SystemClock.uptimeMillis()) * 0.48d))) * 0.017453292519943295d;
        double theta2 = theta1 - 3.207473196809073d;
        int alpha2 = (int) (((Math.sin(theta2) + 1.0d) / 2.0d) * 255.0d);
        int alpha3 = (int) (((Math.sin(theta2 - 3.207473196809073d) + 1.0d) / 2.0d) * 255.0d);
        int cx = canvas.getWidth() / 2;
        this.paint.setAlpha((int) (((Math.sin(theta1) + 1.0d) / 2.0d) * 255.0d));
        canvas.drawCircle((float) ((cx - (radius * 2)) - spaceBetweenDots), (float) cy, (float) radius, this.paint);
        this.paint.setAlpha(alpha2);
        canvas.drawCircle((float) cx, (float) cy, (float) radius, this.paint);
        this.paint.setAlpha(alpha3);
        canvas.drawCircle((float) ((radius * 2) + cx + spaceBetweenDots), (float) cy, (float) radius, this.paint);
        invalidateSelf();
    }

    public void setColor(int color) {
        this.paint.setColor(color);
        invalidateSelf();
    }

    public int getOpacity() {
        return -3;
    }

    public void setAlpha(int alpha) {
    }

    public void setColorFilter(ColorFilter colorFilter) {
    }
}
