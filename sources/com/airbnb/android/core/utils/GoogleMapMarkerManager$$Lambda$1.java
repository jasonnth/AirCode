package com.airbnb.android.core.utils;

import android.content.Context;
import com.airbnb.android.core.models.Listing;
import java.util.concurrent.Callable;

final /* synthetic */ class GoogleMapMarkerManager$$Lambda$1 implements Callable {
    private final GoogleMapMarkerManager arg$1;
    private final MapMarkerable arg$2;
    private final Context arg$3;
    private final boolean arg$4;
    private final Listing arg$5;

    private GoogleMapMarkerManager$$Lambda$1(GoogleMapMarkerManager googleMapMarkerManager, MapMarkerable mapMarkerable, Context context, boolean z, Listing listing) {
        this.arg$1 = googleMapMarkerManager;
        this.arg$2 = mapMarkerable;
        this.arg$3 = context;
        this.arg$4 = z;
        this.arg$5 = listing;
    }

    public static Callable lambdaFactory$(GoogleMapMarkerManager googleMapMarkerManager, MapMarkerable mapMarkerable, Context context, boolean z, Listing listing) {
        return new GoogleMapMarkerManager$$Lambda$1(googleMapMarkerManager, mapMarkerable, context, z, listing);
    }

    public Object call() {
        return GoogleMapMarkerManager.lambda$createListingMarker$0(this.arg$1, this.arg$2, this.arg$3, this.arg$4, this.arg$5);
    }
}
