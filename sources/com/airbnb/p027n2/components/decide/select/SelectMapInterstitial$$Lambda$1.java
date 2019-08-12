package com.airbnb.p027n2.components.decide.select;

import com.airbnb.p027n2.primitives.StaticMapView.Listener;

/* renamed from: com.airbnb.n2.components.decide.select.SelectMapInterstitial$$Lambda$1 */
final /* synthetic */ class SelectMapInterstitial$$Lambda$1 implements Listener {
    private static final SelectMapInterstitial$$Lambda$1 instance = new SelectMapInterstitial$$Lambda$1();

    private SelectMapInterstitial$$Lambda$1() {
    }

    public static Listener lambdaFactory$() {
        return instance;
    }

    public void onImageException(Exception exc) {
        SelectMapInterstitial.lambda$setMapOptions$0(exc);
    }
}
