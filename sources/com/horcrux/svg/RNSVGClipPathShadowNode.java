package com.horcrux.svg;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.ReactConstants;

public class RNSVGClipPathShadowNode extends RNSVGGroupShadowNode {
    public void draw(Canvas canvas, Paint paint, float opacity) {
        FLog.m1847w(ReactConstants.TAG, "RNSVG: ClipPath can't be drawn, it should be defined as a child component for `Defs` ");
    }

    /* access modifiers changed from: protected */
    public void saveDefinition() {
        getSvgShadowNode().defineClipPath(this, this.mName);
    }

    public boolean isResponsible() {
        return false;
    }

    public int hitTest(Point point, Matrix matrix) {
        return -1;
    }

    public void mergeProperties(RNSVGVirtualNode target, ReadableArray mergeList, boolean inherited) {
    }

    public void mergeProperties(RNSVGVirtualNode target, ReadableArray mergeList) {
    }

    public void resetProperties() {
    }
}
