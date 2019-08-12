package com.horcrux.svg;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import com.facebook.react.uimanager.annotations.ReactProp;

public class RNSVGLineShadowNode extends RNSVGPathShadowNode {
    private String mX1;
    private String mX2;
    private String mY1;
    private String mY2;

    @ReactProp(name = "x1")
    public void setX1(String x1) {
        this.mX1 = x1;
        markUpdated();
    }

    @ReactProp(name = "y1")
    public void setY1(String y1) {
        this.mY1 = y1;
        markUpdated();
    }

    @ReactProp(name = "x2")
    public void setX2(String x2) {
        this.mX2 = x2;
        markUpdated();
    }

    @ReactProp(name = "y2")
    public void setY2(String y2) {
        this.mY2 = y2;
        markUpdated();
    }

    public void draw(Canvas canvas, Paint paint, float opacity) {
        this.mPath = getPath(canvas, paint);
        super.draw(canvas, paint, opacity);
    }

    /* access modifiers changed from: protected */
    public Path getPath(Canvas canvas, Paint paint) {
        Path path = new Path();
        float x1 = PropHelper.fromPercentageToFloat(this.mX1, (float) this.mCanvasWidth, 0.0f, this.mScale);
        float y1 = PropHelper.fromPercentageToFloat(this.mY1, (float) this.mCanvasHeight, 0.0f, this.mScale);
        float x2 = PropHelper.fromPercentageToFloat(this.mX2, (float) this.mCanvasWidth, 0.0f, this.mScale);
        float y2 = PropHelper.fromPercentageToFloat(this.mY2, (float) this.mCanvasHeight, 0.0f, this.mScale);
        path.moveTo(x1, y1);
        path.lineTo(x2, y2);
        return path;
    }
}
