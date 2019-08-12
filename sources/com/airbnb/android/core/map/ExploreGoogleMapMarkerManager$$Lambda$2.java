package com.airbnb.android.core.map;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.concurrent.Callable;

final /* synthetic */ class ExploreGoogleMapMarkerManager$$Lambda$2 implements Callable {
    private final ExploreMapMarkerable arg$1;
    private final boolean arg$2;
    private final boolean arg$3;

    private ExploreGoogleMapMarkerManager$$Lambda$2(ExploreMapMarkerable exploreMapMarkerable, boolean z, boolean z2) {
        this.arg$1 = exploreMapMarkerable;
        this.arg$2 = z;
        this.arg$3 = z2;
    }

    public static Callable lambdaFactory$(ExploreMapMarkerable exploreMapMarkerable, boolean z, boolean z2) {
        return new ExploreGoogleMapMarkerManager$$Lambda$2(exploreMapMarkerable, z, z2);
    }

    public Object call() {
        return BitmapDescriptorFactory.fromBitmap(this.arg$1.getBitmap(this.arg$2, this.arg$3));
    }
}
