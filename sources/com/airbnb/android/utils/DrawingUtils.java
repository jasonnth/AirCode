package com.airbnb.android.utils;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public final class DrawingUtils {
    private static final Rect textBounds = new Rect();

    private DrawingUtils() {
    }

    public static void drawTextCentred(Canvas canvas, Paint paint, String text, float cx, float cy) {
        paint.getTextBounds(text, 0, text.length(), textBounds);
        canvas.drawText(text, cx - textBounds.exactCenterX(), cy - textBounds.exactCenterY(), paint);
    }

    public static void drawTextVerticallyCentred(Canvas canvas, Paint paint, String text, float x, float cy) {
        paint.getTextBounds(text, 0, text.length(), textBounds);
        canvas.drawText(text, x, cy - textBounds.exactCenterY(), paint);
    }
}
