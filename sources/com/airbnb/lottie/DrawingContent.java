package com.airbnb.lottie;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.RectF;

interface DrawingContent extends Content {
    void addColorFilter(String str, String str2, ColorFilter colorFilter);

    void draw(Canvas canvas, Matrix matrix, int i);

    void getBounds(RectF rectF, Matrix matrix);
}
