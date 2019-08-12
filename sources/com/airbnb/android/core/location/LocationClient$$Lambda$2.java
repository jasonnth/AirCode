package com.airbnb.android.core.location;

import android.content.Context;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;

final /* synthetic */ class LocationClient$$Lambda$2 implements OnConnectionFailedListener {
    private final LocationClient arg$1;
    private final Context arg$2;

    private LocationClient$$Lambda$2(LocationClient locationClient, Context context) {
        this.arg$1 = locationClient;
        this.arg$2 = context;
    }

    public static OnConnectionFailedListener lambdaFactory$(LocationClient locationClient, Context context) {
        return new LocationClient$$Lambda$2(locationClient, context);
    }

    public void onConnectionFailed(ConnectionResult connectionResult) {
        LocationClient.lambda$new$0(this.arg$1, this.arg$2, connectionResult);
    }
}
