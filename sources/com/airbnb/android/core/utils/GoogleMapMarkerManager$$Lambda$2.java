package com.airbnb.android.core.utils;

import android.content.Context;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.concurrent.Callable;

final /* synthetic */ class GoogleMapMarkerManager$$Lambda$2 implements Callable {
    private final GoogleMapMarkerManager arg$1;
    private final Context arg$2;
    private final MapMarkerable arg$3;
    private final boolean arg$4;
    private final boolean arg$5;
    private final boolean arg$6;

    private GoogleMapMarkerManager$$Lambda$2(GoogleMapMarkerManager googleMapMarkerManager, Context context, MapMarkerable mapMarkerable, boolean z, boolean z2, boolean z3) {
        this.arg$1 = googleMapMarkerManager;
        this.arg$2 = context;
        this.arg$3 = mapMarkerable;
        this.arg$4 = z;
        this.arg$5 = z2;
        this.arg$6 = z3;
    }

    public static Callable lambdaFactory$(GoogleMapMarkerManager googleMapMarkerManager, Context context, MapMarkerable mapMarkerable, boolean z, boolean z2, boolean z3) {
        return new GoogleMapMarkerManager$$Lambda$2(googleMapMarkerManager, context, mapMarkerable, z, z2, z3);
    }

    public Object call() {
        return BitmapDescriptorFactory.fromBitmap(this.arg$1.mapMarkerGenerator.getPriceMarker(this.arg$2, this.arg$3.getPrice(), this.arg$4, this.arg$5, false, this.arg$6));
    }
}
