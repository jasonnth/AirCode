package com.horcrux.svg;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.annotations.ReactProp;

public class RNSVGViewBoxShadowNode extends RNSVGGroupShadowNode {
    private String mAlign;
    private String mBoxHeight;
    private String mBoxWidth;
    private boolean mFromSymbol = false;
    private int mMeetOrSlice;
    private String mMinX;
    private String mMinY;
    private String mVbHeight;
    private String mVbWidth;

    @ReactProp(name = "minX")
    public void setMinX(String minX) {
        this.mMinX = minX;
        markUpdated();
    }

    @ReactProp(name = "minY")
    public void setMinY(String minY) {
        this.mMinY = minY;
        markUpdated();
    }

    @ReactProp(name = "vbWidth")
    public void setVbWidth(String vbWidth) {
        this.mVbWidth = vbWidth;
        markUpdated();
    }

    @ReactProp(name = "vbHeight")
    public void setVbHeight(String vbHeight) {
        this.mVbHeight = vbHeight;
        markUpdated();
    }

    @ReactProp(name = "width")
    public void setWidth(String width) {
        this.mBoxWidth = width;
        markUpdated();
    }

    @ReactProp(name = "height")
    public void setHeight(String height) {
        this.mBoxHeight = height;
        markUpdated();
    }

    @ReactProp(name = "align")
    public void setAlign(String align) {
        this.mAlign = align;
        markUpdated();
    }

    @ReactProp(name = "meetOrSlice")
    public void setMeetOrSlice(int meetOrSlice) {
        this.mMeetOrSlice = meetOrSlice;
        markUpdated();
    }

    public void draw(Canvas canvas, Paint paint, float opacity) {
        setupDimensions(canvas);
        this.mMatrix = getTransform();
        super.draw(canvas, paint, opacity);
    }

    public Matrix getTransform() {
        float eHeight;
        float vbX = PropHelper.fromPercentageToFloat(this.mMinX, (float) this.mCanvasWidth, 0.0f, this.mScale);
        float vbY = PropHelper.fromPercentageToFloat(this.mMinY, (float) this.mCanvasHeight, 0.0f, this.mScale);
        float vbWidth = PropHelper.fromPercentageToFloat(this.mVbWidth, (float) this.mCanvasWidth, 0.0f, this.mScale);
        float vbHeight = PropHelper.fromPercentageToFloat(this.mVbHeight, (float) this.mCanvasHeight, 0.0f, this.mScale);
        float eX = (float) this.mCanvasX;
        float eY = (float) this.mCanvasY;
        float eWidth = this.mBoxWidth != null ? PropHelper.fromPercentageToFloat(this.mBoxWidth, (float) this.mCanvasWidth, 0.0f, this.mScale) : (float) this.mCanvasWidth;
        if (this.mBoxHeight != null) {
            eHeight = PropHelper.fromPercentageToFloat(this.mBoxHeight, (float) this.mCanvasHeight, 0.0f, this.mScale);
        } else {
            eHeight = (float) this.mCanvasHeight;
        }
        float scaleX = eWidth / vbWidth;
        float scaleY = eHeight / vbHeight;
        float translateX = vbX - eX;
        float translateY = vbY - eY;
        if (this.mMeetOrSlice == 2) {
            scaleY = Math.min(scaleX, scaleY);
            scaleX = scaleY;
            float scale = scaleY;
            if (scale > 1.0f) {
                translateX -= ((eWidth / scale) - vbWidth) / 2.0f;
                translateY -= ((eHeight / scale) - vbHeight) / 2.0f;
            } else {
                translateX -= (eWidth - (vbWidth * scale)) / 2.0f;
                translateY -= (eHeight - (vbHeight * scale)) / 2.0f;
            }
        } else {
            if (!this.mAlign.equals("none") && this.mMeetOrSlice == 0) {
                scaleY = Math.min(scaleX, scaleY);
                scaleX = scaleY;
            } else if (!this.mAlign.equals("none") && this.mMeetOrSlice == 1) {
                scaleY = Math.max(scaleX, scaleY);
                scaleX = scaleY;
            }
            if (this.mAlign.contains("xMid")) {
                translateX -= ((eWidth / scaleX) - vbWidth) / 2.0f;
            }
            if (this.mAlign.contains("xMax")) {
                translateX -= (eWidth / scaleX) - vbWidth;
            }
            if (this.mAlign.contains("YMid")) {
                translateY -= ((eHeight / scaleY) - vbHeight) / 2.0f;
            }
            if (this.mAlign.contains("YMax")) {
                translateY -= (eHeight / scaleY) - vbHeight;
            }
        }
        Matrix transform = new Matrix();
        transform.postTranslate((-translateX) * (this.mFromSymbol ? scaleX : 1.0f), (this.mFromSymbol ? scaleY : 1.0f) * (-translateY));
        transform.postScale(scaleX, scaleY);
        return transform;
    }

    public void mergeProperties(RNSVGVirtualNode target, ReadableArray mergeList) {
        if (target instanceof RNSVGUseShadowNode) {
            this.mFromSymbol = true;
            this.mBoxWidth = ((RNSVGUseShadowNode) target).getWidth();
            this.mBoxHeight = ((RNSVGUseShadowNode) target).getHeight();
        }
    }

    public void resetProperties() {
        this.mBoxHeight = null;
        this.mBoxWidth = null;
        this.mFromSymbol = false;
    }
}
