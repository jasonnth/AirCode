package com.airbnb.android.atlantis;

import android.content.Context;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;

final /* synthetic */ class AtlantisLocationClient$$Lambda$4 implements OnConnectionFailedListener {
    private final AtlantisLocationClient arg$1;
    private final Context arg$2;

    private AtlantisLocationClient$$Lambda$4(AtlantisLocationClient atlantisLocationClient, Context context) {
        this.arg$1 = atlantisLocationClient;
        this.arg$2 = context;
    }

    public static OnConnectionFailedListener lambdaFactory$(AtlantisLocationClient atlantisLocationClient, Context context) {
        return new AtlantisLocationClient$$Lambda$4(atlantisLocationClient, context);
    }

    public void onConnectionFailed(ConnectionResult connectionResult) {
        AtlantisLocationClient.lambda$new$0(this.arg$1, this.arg$2, connectionResult);
    }
}
