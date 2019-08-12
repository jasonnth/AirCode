package com.airbnb.android.core.viewcomponents;

import com.airbnb.android.core.viewcomponents.DraggableAirEpoxyItemTouchHelperCallback.ViewHolderMovedListener;

final /* synthetic */ class DraggableAirEpoxyAdapter$$Lambda$1 implements ViewHolderMovedListener {
    private final DraggableAirEpoxyAdapter arg$1;

    private DraggableAirEpoxyAdapter$$Lambda$1(DraggableAirEpoxyAdapter draggableAirEpoxyAdapter) {
        this.arg$1 = draggableAirEpoxyAdapter;
    }

    public static ViewHolderMovedListener lambdaFactory$(DraggableAirEpoxyAdapter draggableAirEpoxyAdapter) {
        return new DraggableAirEpoxyAdapter$$Lambda$1(draggableAirEpoxyAdapter);
    }

    public boolean onMove(int i, int i2) {
        return this.arg$1.onMove(i, i2);
    }
}
