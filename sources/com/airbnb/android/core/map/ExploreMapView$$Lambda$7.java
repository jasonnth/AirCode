package com.airbnb.android.core.map;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ExploreMapView$$Lambda$7 implements OnClickListener {
    private static final ExploreMapView$$Lambda$7 instance = new ExploreMapView$$Lambda$7();

    private ExploreMapView$$Lambda$7() {
    }

    public static OnClickListener lambdaFactory$() {
        return instance;
    }

    public void onClick(View view) {
        ExploreMapView.lambda$setupNavigationPill$3(view);
    }
}
