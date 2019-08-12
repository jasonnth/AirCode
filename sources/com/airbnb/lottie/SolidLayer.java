package com.airbnb.lottie;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;

class SolidLayer extends BaseLayer {
    private final Layer layerModel;
    private final Paint paint = new Paint();
    private final RectF rect = new RectF();

    SolidLayer(LottieDrawable lottieDrawable, Layer layerModel2) {
        super(lottieDrawable, layerModel2);
        this.layerModel = layerModel2;
        this.paint.setAlpha(0);
        this.paint.setStyle(Style.FILL);
        this.paint.setColor(layerModel2.getSolidColor());
    }

    public void drawLayer(Canvas canvas, Matrix parentMatrix, int parentAlpha) {
        int backgroundAlpha = Color.alpha(this.layerModel.getSolidColor());
        if (backgroundAlpha != 0) {
            int alpha = (int) (((((float) ((Integer) this.transform.getOpacity().getValue()).intValue()) * (((float) backgroundAlpha) / 255.0f)) / 100.0f) * 255.0f);
            this.paint.setAlpha(alpha);
            if (alpha > 0) {
                updateRect(parentMatrix);
                canvas.drawRect(this.rect, this.paint);
            }
        }
    }

    public void getBounds(RectF outBounds, Matrix parentMatrix) {
        super.getBounds(outBounds, parentMatrix);
        updateRect(this.boundsMatrix);
        outBounds.set(this.rect);
    }

    private void updateRect(Matrix matrix) {
        this.rect.set(0.0f, 0.0f, (float) this.layerModel.getSolidWidth(), (float) this.layerModel.getSolidHeight());
        matrix.mapRect(this.rect);
    }

    public void addColorFilter(String layerName, String contentName, ColorFilter colorFilter) {
        this.paint.setColorFilter(colorFilter);
    }
}
