package com.horcrux.svg;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.RectF;
import com.facebook.react.uimanager.annotations.ReactProp;

public class RNSVGEllipseShadowNode extends RNSVGPathShadowNode {
    private String mCx;
    private String mCy;
    private String mRx;
    private String mRy;

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

    @ReactProp(name = "rx")
    public void setRx(String rx) {
        this.mRx = rx;
        markUpdated();
    }

    @ReactProp(name = "ry")
    public void setRy(String ry) {
        this.mRy = ry;
        markUpdated();
    }

    public void draw(Canvas canvas, Paint paint, float opacity) {
        this.mPath = getPath(canvas, paint);
        super.draw(canvas, paint, opacity);
    }

    /* access modifiers changed from: protected */
    public Path getPath(Canvas canvas, Paint paint) {
        Path path = new Path();
        float cx = PropHelper.fromPercentageToFloat(this.mCx, (float) this.mCanvasWidth, 0.0f, this.mScale);
        float cy = PropHelper.fromPercentageToFloat(this.mCy, (float) this.mCanvasHeight, 0.0f, this.mScale);
        float rx = PropHelper.fromPercentageToFloat(this.mRx, (float) this.mCanvasWidth, 0.0f, this.mScale);
        float ry = PropHelper.fromPercentageToFloat(this.mRy, (float) this.mCanvasHeight, 0.0f, this.mScale);
        path.addOval(new RectF(cx - rx, cy - ry, cx + rx, cy + ry), Direction.CW);
        return path;
    }
}
