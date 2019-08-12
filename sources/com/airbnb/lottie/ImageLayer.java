package com.airbnb.lottie;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

class ImageLayer extends BaseLayer {
    private final float density;
    private final Rect dst = new Rect();
    private final Paint paint = new Paint(3);
    private final Rect src = new Rect();

    ImageLayer(LottieDrawable lottieDrawable, Layer layerModel, float density2) {
        super(lottieDrawable, layerModel);
        this.density = density2;
    }

    public void drawLayer(Canvas canvas, Matrix parentMatrix, int parentAlpha) {
        Bitmap bitmap = getBitmap();
        if (bitmap != null) {
            this.paint.setAlpha(parentAlpha);
            canvas.save();
            canvas.concat(parentMatrix);
            this.src.set(0, 0, bitmap.getWidth(), bitmap.getHeight());
            this.dst.set(0, 0, (int) (((float) bitmap.getWidth()) * this.density), (int) (((float) bitmap.getHeight()) * this.density));
            canvas.drawBitmap(bitmap, this.src, this.dst, this.paint);
            canvas.restore();
        }
    }

    public void getBounds(RectF outBounds, Matrix parentMatrix) {
        super.getBounds(outBounds, parentMatrix);
        Bitmap bitmap = getBitmap();
        if (bitmap != null) {
            outBounds.set(outBounds.left, outBounds.top, Math.min(outBounds.right, (float) bitmap.getWidth()), Math.min(outBounds.bottom, (float) bitmap.getHeight()));
            this.boundsMatrix.mapRect(outBounds);
        }
    }

    private Bitmap getBitmap() {
        return this.lottieDrawable.getImageAsset(this.layerModel.getRefId());
    }

    public void addColorFilter(String layerName, String contentName, ColorFilter colorFilter) {
        this.paint.setColorFilter(colorFilter);
    }
}
