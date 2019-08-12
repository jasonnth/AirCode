package com.airbnb.android.atlantis;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.IBinder;
import com.airbnb.android.core.location.LocationClientFacade.LocationClientCallbacks;
import com.airbnb.android.core.utils.LocationUtil;

public class AtlantisService extends Service {
    /* access modifiers changed from: private */
    public AtlantisLocationClient locationClient;
    private final LocationClientCallbacks locationClientCallbacks = new LocationClientCallbacks() {
        public void onConnected() {
            AtlantisService.this.locationClient.requestLocationUpdates();
        }

        public void onLocationUpdated(Location location) {
        }
    };

    public IBinder onBind(Intent intent) {
        return null;
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        return 3;
    }

    public void onCreate() {
        Context context = getApplicationContext();
        if (LocationUtil.hasLocationPermission(context)) {
            this.locationClient = new AtlantisLocationClient(context, this.locationClientCallbacks);
            this.locationClient.connect();
            return;
        }
        stopSelf();
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.locationClient != null) {
            this.locationClient.disconnect();
        }
    }
}
