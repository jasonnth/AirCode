package com.airbnb.p027n2.utils;

import android.support.p002v7.widget.LinearSnapHelper;
import android.support.p002v7.widget.OrientationHelper;
import android.support.p002v7.widget.RecyclerView.LayoutManager;
import android.view.View;

/* renamed from: com.airbnb.n2.utils.LinearLeftSnapHelper */
public class LinearLeftSnapHelper extends LinearSnapHelper {
    public int[] calculateDistanceToFinalSnap(LayoutManager layoutManager, View targetView) {
        return new int[]{distanceToLeft(layoutManager, targetView), 0};
    }

    private int distanceToLeft(LayoutManager layoutManager, View targetView) {
        OrientationHelper helper = OrientationHelper.createHorizontalHelper(layoutManager);
        return helper.getDecoratedStart(targetView) - helper.getStartAfterPadding();
    }
}
