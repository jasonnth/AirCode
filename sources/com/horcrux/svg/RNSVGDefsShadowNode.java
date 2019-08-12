package com.horcrux.svg;

import android.graphics.Canvas;
import android.graphics.Paint;

public class RNSVGDefsShadowNode extends RNSVGDefinitionShadowNode {
    public void draw(Canvas canvas, Paint paint, float opacity) {
        traverseChildren(new NodeRunnable() {
            public boolean run(RNSVGVirtualNode node) {
                node.saveDefinition();
                return true;
            }
        });
        traverseChildren(new NodeRunnable() {
            public boolean run(RNSVGVirtualNode node) {
                node.markUpdateSeen();
                node.traverseChildren(this);
                return true;
            }
        });
    }
}
