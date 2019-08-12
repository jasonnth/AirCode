package com.airbnb.p027n2.collections;

import android.view.View;
import android.view.View.OnLayoutChangeListener;

/* renamed from: com.airbnb.n2.collections.ToolbarCoordinator$$Lambda$5 */
final /* synthetic */ class ToolbarCoordinator$$Lambda$5 implements OnLayoutChangeListener {
    private final ToolbarCoordinator arg$1;

    private ToolbarCoordinator$$Lambda$5(ToolbarCoordinator toolbarCoordinator) {
        this.arg$1 = toolbarCoordinator;
    }

    public static OnLayoutChangeListener lambdaFactory$(ToolbarCoordinator toolbarCoordinator) {
        return new ToolbarCoordinator$$Lambda$5(toolbarCoordinator);
    }

    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        ToolbarCoordinator.lambda$init$2(this.arg$1, view, i, i2, i3, i4, i5, i6, i7, i8);
    }
}
