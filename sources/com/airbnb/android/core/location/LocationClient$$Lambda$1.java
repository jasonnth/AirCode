package com.airbnb.android.core.location;

import android.location.Location;
import com.google.android.gms.location.LocationListener;

final /* synthetic */ class LocationClient$$Lambda$1 implements LocationListener {
    private final LocationClient arg$1;

    private LocationClient$$Lambda$1(LocationClient locationClient) {
        this.arg$1 = locationClient;
    }

    public static LocationListener lambdaFactory$(LocationClient locationClient) {
        return new LocationClient$$Lambda$1(locationClient);
    }

    public void onLocationChanged(Location location) {
        this.arg$1.handleLocationChanged(location);
    }
}
