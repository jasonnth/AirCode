package com.horcrux.svg;

import com.facebook.react.bridge.JavaOnlyArray;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.horcrux.svg.PropHelper.RNSVGBrush.GradientType;

public class RNSVGRadialGradientShadowNode extends RNSVGDefinitionShadowNode {
    private String mCx;
    private String mCy;
    private String mFx;
    private String mFy;
    private ReadableArray mGradient;
    private String mRx;
    private String mRy;

    @ReactProp(name = "fx")
    public void setFX(String fx) {
        this.mFx = fx;
        markUpdated();
    }

    @ReactProp(name = "fy")
    public void setFy(String fy) {
        this.mFy = fy;
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

    @ReactProp(name = "gradient")
    public void setGradient(ReadableArray gradient) {
        this.mGradient = gradient;
        markUpdated();
    }

    /* access modifiers changed from: protected */
    public void saveDefinition() {
        if (this.mName != null) {
            WritableArray points = new JavaOnlyArray();
            points.pushString(this.mFx);
            points.pushString(this.mFy);
            points.pushString(this.mRx);
            points.pushString(this.mRy);
            points.pushString(this.mCx);
            points.pushString(this.mCy);
            getSvgShadowNode().defineBrush(new RNSVGBrush(GradientType.RADIAL_GRADIENT, points, this.mGradient), this.mName);
        }
    }
}
