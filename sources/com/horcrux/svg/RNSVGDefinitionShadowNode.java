package com.horcrux.svg;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import com.facebook.react.bridge.ReadableArray;

public class RNSVGDefinitionShadowNode extends RNSVGVirtualNode {
    public void draw(Canvas canvas, Paint paint, float opacity) {
    }

    public boolean isResponsible() {
        return false;
    }

    /* access modifiers changed from: protected */
    public Path getPath(Canvas canvas, Paint paint) {
        return null;
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
