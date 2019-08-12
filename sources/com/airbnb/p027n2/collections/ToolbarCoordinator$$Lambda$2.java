package com.airbnb.p027n2.collections;

import com.airbnb.p027n2.interfaces.Scrollable.ScrollViewOnScrollListener;

/* renamed from: com.airbnb.n2.collections.ToolbarCoordinator$$Lambda$2 */
final /* synthetic */ class ToolbarCoordinator$$Lambda$2 implements ScrollViewOnScrollListener {
    private final ToolbarCoordinator arg$1;

    private ToolbarCoordinator$$Lambda$2(ToolbarCoordinator toolbarCoordinator) {
        this.arg$1 = toolbarCoordinator;
    }

    public static ScrollViewOnScrollListener lambdaFactory$(ToolbarCoordinator toolbarCoordinator) {
        return new ToolbarCoordinator$$Lambda$2(toolbarCoordinator);
    }

    public void onScroll(int i, int i2, int i3, int i4) {
        this.arg$1.updateOffset(i2 - i4);
    }
}
