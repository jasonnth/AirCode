package com.airbnb.p027n2.collections;

import android.view.View;
import android.view.View.OnLayoutChangeListener;

/* renamed from: com.airbnb.n2.collections.ToolbarCoordinator$$Lambda$3 */
final /* synthetic */ class ToolbarCoordinator$$Lambda$3 implements OnLayoutChangeListener {
    private final ToolbarCoordinator arg$1;

    private ToolbarCoordinator$$Lambda$3(ToolbarCoordinator toolbarCoordinator) {
        this.arg$1 = toolbarCoordinator;
    }

    public static OnLayoutChangeListener lambdaFactory$(ToolbarCoordinator toolbarCoordinator) {
        return new ToolbarCoordinator$$Lambda$3(toolbarCoordinator);
    }

    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        this.arg$1.updateOffset(0);
    }
}
