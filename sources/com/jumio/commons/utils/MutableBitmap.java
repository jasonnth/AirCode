package com.jumio.commons.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import com.airbnb.android.airmapview.AirMapInterface;

public class MutableBitmap {
    public static Bitmap copy(Bitmap source) {
        return source.copy(source.getConfig(), true);
    }

    public static Bitmap createScaledBitmap(Bitmap source, int width, int height) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, source.getConfig());
        Canvas canvas = new Canvas(bitmap);
        Matrix matrix = new Matrix();
        matrix.setScale(((float) width) / ((float) source.getWidth()), ((float) height) / ((float) source.getHeight()));
        canvas.drawBitmap(source, matrix, null);
        return bitmap;
    }

    public static void delete(Bitmap bitmap) {
        if (bitmap != null) {
            if (bitmap.isMutable()) {
                bitmap.eraseColor(AirMapInterface.CIRCLE_BORDER_COLOR);
            }
            bitmap.recycle();
        }
    }
}
