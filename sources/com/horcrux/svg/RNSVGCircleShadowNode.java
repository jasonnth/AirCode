package com.horcrux.svg;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.Direction;
import com.facebook.react.uimanager.annotations.ReactProp;

public class RNSVGCircleShadowNode extends RNSVGPathShadowNode {
    private String mCx;
    private String mCy;

    /* renamed from: mR */
    private String f1381mR;

    @ReactProp(name = "cx")
    public void setCx(String cx) {
        this.mCx = cx;
        markUpdated();
    }

    @ReactProp(name = "cy")
    public void setCy(String cy) {
        this.mCy = cy;
        markUpdated();
    }

    @ReactProp(name = "r")
    public void setR(String r) {
        this.f1381mR = r;
        markUpdated();
    }

    public void draw(Canvas canvas, Paint paint, float opacity) {
        this.mPath = getPath(canvas, paint);
        super.draw(canvas, paint, opacity);
    }

    /* access modifiers changed from: protected */
    public Path getPath(Canvas canvas, Paint paint) {
        float r;
        Path path = new Path();
        float cx = PropHelper.fromPercentageToFloat(this.mCx, (float) this.mCanvasWidth, 0.0f, this.mScale);
        float cy = PropHelper.fromPercentageToFloat(this.mCy, (float) this.mCanvasHeight, 0.0f, this.mScale);
        if (PropHelper.isPercentage(this.f1381mR)) {
            float r2 = PropHelper.fromPercentageToFloat(this.f1381mR, 1.0f, 0.0f, 1.0f);
            r = ((float) Math.sqrt((double) (((float) Math.pow((double) (((float) this.mCanvasWidth) * r2), 2.0d)) + ((float) Math.pow((double) (((float) this.mCanvasHeight) * r2), 2.0d))))) / ((float) Math.sqrt(2.0d));
        } else {
            r = Float.parseFloat(this.f1381mR) * this.mScale;
        }
        path.addCircle(cx, cy, r, Direction.CW);
        return path;
    }
}
