package com.airbnb.lottie;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.RectF;
import java.util.Collections;

class ShapeLayer extends BaseLayer {
    private final ContentGroup contentGroup;
    private final Matrix matrix = new Matrix();

    ShapeLayer(LottieDrawable lottieDrawable, Layer layerModel) {
        super(lottieDrawable, layerModel);
        this.contentGroup = new ContentGroup(lottieDrawable, this, new ShapeGroup(layerModel.getName(), layerModel.getShapes()));
        this.contentGroup.setContents(Collections.emptyList(), Collections.emptyList());
    }

    /* access modifiers changed from: 0000 */
    public void drawLayer(Canvas canvas, Matrix parentMatrix, int parentAlpha) {
        this.contentGroup.draw(canvas, parentMatrix, parentAlpha);
    }

    public void getBounds(RectF outBounds, Matrix parentMatrix) {
        super.getBounds(outBounds, parentMatrix);
        this.contentGroup.getBounds(outBounds, this.boundsMatrix);
    }

    public void addColorFilter(String layerName, String contentName, ColorFilter colorFilter) {
        this.contentGroup.addColorFilter(layerName, contentName, colorFilter);
    }
}
