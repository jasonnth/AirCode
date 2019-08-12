package com.airbnb.android.atlantis;

import android.location.Location;
import com.google.android.gms.location.LocationListener;

final /* synthetic */ class AtlantisLocationClient$$Lambda$1 implements LocationListener {
    private final AtlantisLocationClient arg$1;

    private AtlantisLocationClient$$Lambda$1(AtlantisLocationClient atlantisLocationClient) {
        this.arg$1 = atlantisLocationClient;
    }

    public static LocationListener lambdaFactory$(AtlantisLocationClient atlantisLocationClient) {
        return new AtlantisLocationClient$$Lambda$1(atlantisLocationClient);
    }

    public void onLocationChanged(Location location) {
        this.arg$1.handleLocationChanged(location);
    }
}
