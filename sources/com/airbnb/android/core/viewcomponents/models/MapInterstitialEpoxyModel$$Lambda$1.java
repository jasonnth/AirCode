package com.airbnb.android.core.viewcomponents.models;

import com.airbnb.android.core.utils.StaticMapUtil;
import com.airbnb.p027n2.primitives.StaticMapView.Listener;

final /* synthetic */ class MapInterstitialEpoxyModel$$Lambda$1 implements Listener {
    private static final MapInterstitialEpoxyModel$$Lambda$1 instance = new MapInterstitialEpoxyModel$$Lambda$1();

    private MapInterstitialEpoxyModel$$Lambda$1() {
    }

    public static Listener lambdaFactory$() {
        return instance;
    }

    public void onImageException(Exception exc) {
        StaticMapUtil.onImageException(exc);
    }
}
