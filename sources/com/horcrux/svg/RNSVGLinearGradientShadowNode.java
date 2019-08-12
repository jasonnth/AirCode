package com.horcrux.svg;

import com.facebook.react.bridge.JavaOnlyArray;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.horcrux.svg.PropHelper.RNSVGBrush.GradientType;

public class RNSVGLinearGradientShadowNode extends RNSVGDefinitionShadowNode {
    private ReadableArray mGradient;
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
    public void setCx(String y1) {
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

    @ReactProp(name = "gradient")
    public void setGradient(ReadableArray gradient) {
        this.mGradient = gradient;
        markUpdated();
    }

    /* access modifiers changed from: protected */
    public void saveDefinition() {
        if (this.mName != null) {
            WritableArray points = new JavaOnlyArray();
            points.pushString(this.mX1);
            points.pushString(this.mY1);
            points.pushString(this.mX2);
            points.pushString(this.mY2);
            getSvgShadowNode().defineBrush(new RNSVGBrush(GradientType.LINEAR_GRADIENT, points, this.mGradient), this.mName);
        }
    }
}
