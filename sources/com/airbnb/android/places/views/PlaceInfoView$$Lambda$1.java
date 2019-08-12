package com.airbnb.android.places.views;

import com.airbnb.android.core.utils.StaticMapUtil;
import com.airbnb.p027n2.primitives.StaticMapView.Listener;

final /* synthetic */ class PlaceInfoView$$Lambda$1 implements Listener {
    private static final PlaceInfoView$$Lambda$1 instance = new PlaceInfoView$$Lambda$1();

    private PlaceInfoView$$Lambda$1() {
    }

    public static Listener lambdaFactory$() {
        return instance;
    }

    public void onImageException(Exception exc) {
        StaticMapUtil.onImageException(exc);
    }
}
