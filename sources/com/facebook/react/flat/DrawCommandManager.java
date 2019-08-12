package com.facebook.react.flat;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewParent;

abstract class DrawCommandManager {
    /* access modifiers changed from: 0000 */
    public abstract NodeRegion anyNodeRegionWithinBounds(float f, float f2);

    /* access modifiers changed from: 0000 */
    public abstract void debugDraw(Canvas canvas);

    /* access modifiers changed from: 0000 */
    public abstract void draw(Canvas canvas);

    /* access modifiers changed from: 0000 */
    public abstract void getClippingRect(Rect rect);

    /* access modifiers changed from: 0000 */
    public abstract SparseArray<View> getDetachedViews();

    /* access modifiers changed from: 0000 */
    public abstract void mountDrawCommands(DrawCommand[] drawCommandArr, SparseIntArray sparseIntArray, float[] fArr, float[] fArr2, boolean z);

    /* access modifiers changed from: 0000 */
    public abstract void mountNodeRegions(NodeRegion[] nodeRegionArr, float[] fArr, float[] fArr2);

    /* access modifiers changed from: 0000 */
    public abstract void mountViews(ViewResolver viewResolver, int[] iArr, int[] iArr2);

    /* access modifiers changed from: 0000 */
    public abstract void onClippedViewDropped(View view);

    /* access modifiers changed from: 0000 */
    public abstract boolean updateClippingRect();

    /* access modifiers changed from: 0000 */
    public abstract NodeRegion virtualNodeRegionWithinBounds(float f, float f2);

    DrawCommandManager() {
    }

    protected static void ensureViewHasNoParent(View view) {
        ViewParent oldParent = view.getParent();
        if (oldParent != null) {
            throw new RuntimeException("Cannot add view " + view + " to DrawCommandManager while it has a parent " + oldParent);
        }
    }

    static DrawCommandManager getVerticalClippingInstance(FlatViewGroup flatViewGroup, DrawCommand[] drawCommands) {
        return new VerticalDrawCommandManager(flatViewGroup, drawCommands);
    }
}
