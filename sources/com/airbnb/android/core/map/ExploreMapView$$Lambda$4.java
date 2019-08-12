package com.airbnb.android.core.map;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ExploreMapView$$Lambda$4 implements OnClickListener {
    private final ExploreMapView arg$1;

    private ExploreMapView$$Lambda$4(ExploreMapView exploreMapView) {
        this.arg$1 = exploreMapView;
    }

    public static OnClickListener lambdaFactory$(ExploreMapView exploreMapView) {
        return new ExploreMapView$$Lambda$4(exploreMapView);
    }

    public void onClick(View view) {
        this.arg$1.eventCallbacks.onListButtonClicked();
    }
}
