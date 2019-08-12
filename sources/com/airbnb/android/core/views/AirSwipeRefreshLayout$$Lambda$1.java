package com.airbnb.android.core.views;

import android.support.p000v4.view.ViewCompat;
import com.airbnb.android.core.views.AirSwipeRefreshLayout.CanChildScrollUpListener;

final /* synthetic */ class AirSwipeRefreshLayout$$Lambda$1 implements CanChildScrollUpListener {
    private final AirSwipeRefreshLayout arg$1;

    private AirSwipeRefreshLayout$$Lambda$1(AirSwipeRefreshLayout airSwipeRefreshLayout) {
        this.arg$1 = airSwipeRefreshLayout;
    }

    public static CanChildScrollUpListener lambdaFactory$(AirSwipeRefreshLayout airSwipeRefreshLayout) {
        return new AirSwipeRefreshLayout$$Lambda$1(airSwipeRefreshLayout);
    }

    public boolean canChildScrollUp() {
        return ViewCompat.canScrollVertically(this.arg$1.mScrollableChild, -1);
    }
}
