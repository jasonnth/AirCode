package com.horcrux.svg;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.ReactShadowNode;

public class RNSVGGroupShadowNode extends RNSVGPathShadowNode {
    public void draw(Canvas canvas, Paint paint, float opacity) {
        final RNSVGSvgViewShadowNode svg = getSvgShadowNode();
        if (opacity > 0.01f) {
            int count = saveAndSetupCanvas(canvas);
            clip(canvas, paint);
            final Canvas canvas2 = canvas;
            final Paint paint2 = paint;
            final float f = opacity;
            traverseChildren(new NodeRunnable() {
                public boolean run(RNSVGVirtualNode node) {
                    node.setupDimensions(canvas2);
                    node.mergeProperties(this, RNSVGGroupShadowNode.this.mOwnedPropList, true);
                    node.draw(canvas2, paint2, f * RNSVGGroupShadowNode.this.mOpacity);
                    node.markUpdateSeen();
                    if (node.isResponsible()) {
                        svg.enableTouchEvents();
                    }
                    return true;
                }
            });
            restoreCanvas(canvas, count);
        }
    }

    /* access modifiers changed from: protected */
    public Path getPath(final Canvas canvas, final Paint paint) {
        final Path path = new Path();
        traverseChildren(new NodeRunnable() {
            public boolean run(RNSVGVirtualNode node) {
                node.setupDimensions(canvas);
                path.addPath(node.getPath(canvas, paint));
                return true;
            }
        });
        return path;
    }

    public int hitTest(Point point, Matrix matrix) {
        Matrix combinedMatrix = new Matrix();
        if (matrix != null) {
            combinedMatrix.postConcat(matrix);
        }
        combinedMatrix.postConcat(this.mMatrix);
        for (int i = getChildCount() - 1; i >= 0; i--) {
            ReactShadowNode child = getChildAt(i);
            if (child instanceof RNSVGVirtualNode) {
                RNSVGVirtualNode node = (RNSVGVirtualNode) child;
                int viewTag = node.hitTest(point, combinedMatrix);
                if (viewTag != -1) {
                    if (node.isResponsible() || viewTag != child.getReactTag()) {
                        return viewTag;
                    }
                    return getReactTag();
                }
            }
        }
        return -1;
    }

    /* access modifiers changed from: protected */
    public void saveDefinition() {
        if (this.mName != null) {
            getSvgShadowNode().defineTemplate(this, this.mName);
        }
        traverseChildren(new NodeRunnable() {
            public boolean run(RNSVGVirtualNode node) {
                node.saveDefinition();
                return true;
            }
        });
    }

    public void mergeProperties(final RNSVGVirtualNode target, final ReadableArray mergeList) {
        traverseChildren(new NodeRunnable() {
            public boolean run(RNSVGVirtualNode node) {
                node.mergeProperties(target, mergeList);
                return true;
            }
        });
    }

    public void resetProperties() {
        traverseChildren(new NodeRunnable() {
            public boolean run(RNSVGVirtualNode node) {
                node.resetProperties();
                return true;
            }
        });
    }
}
