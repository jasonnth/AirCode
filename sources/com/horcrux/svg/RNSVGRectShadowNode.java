package com.horcrux.svg;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.RectF;
import com.facebook.react.uimanager.annotations.ReactProp;

public class RNSVGRectShadowNode extends RNSVGPathShadowNode {

    /* renamed from: mH */
    private String f1387mH;
    private String mRx;
    private String mRy;

    /* renamed from: mW */
    private String f1388mW;

    /* renamed from: mX */
    private String f1389mX;

    /* renamed from: mY */
    private String f1390mY;

    @ReactProp(name = "x")
    public void setX(String x) {
        this.f1389mX = x;
        markUpdated();
    }

    @ReactProp(name = "y")
    public void setY(String y) {
        this.f1390mY = y;
        markUpdated();
    }

    @ReactProp(name = "width")
    public void setWidth(String width) {
        this.f1388mW = width;
        markUpdated();
    }

    @ReactProp(name = "height")
    public void setHeight(String height) {
        this.f1387mH = height;
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
        float x = PropHelper.fromPercentageToFloat(this.f1389mX, (float) this.mCanvasWidth, 0.0f, this.mScale);
        float y = PropHelper.fromPercentageToFloat(this.f1390mY, (float) this.mCanvasHeight, 0.0f, this.mScale);
        float w = PropHelper.fromPercentageToFloat(this.f1388mW, (float) this.mCanvasWidth, 0.0f, this.mScale);
        float h = PropHelper.fromPercentageToFloat(this.f1387mH, (float) this.mCanvasHeight, 0.0f, this.mScale);
        float rx = PropHelper.fromPercentageToFloat(this.mRx, (float) this.mCanvasWidth, 0.0f, this.mScale);
        float ry = PropHelper.fromPercentageToFloat(this.mRy, (float) this.mCanvasHeight, 0.0f, this.mScale);
        if (rx == 0.0f && ry == 0.0f) {
            path.addRect(x, y, x + w, y + h, Direction.CW);
        } else {
            if (rx == 0.0f) {
                rx = ry;
            } else if (ry == 0.0f) {
                ry = rx;
            }
            if (rx > w / 2.0f) {
                rx = w / 2.0f;
            }
            if (ry > h / 2.0f) {
                ry = h / 2.0f;
            }
            path.addRoundRect(new RectF(x, y, x + w, y + h), rx, ry, Direction.CW);
        }
        return path;
    }
}
