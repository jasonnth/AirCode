package com.airbnb.android.core.location;

import android.content.Context;
import android.location.Location;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.utils.AppLaunchUtils;
import com.airbnb.android.core.utils.LocationUtil;

public interface LocationClientFacade {

    public static final class Factory {
        private Factory() {
        }

        public static LocationClientFacade get(Context context, LocationClientCallbacks callbacks) {
            if (context == null || !LocationUtil.hasLocationPermission(context) || AppLaunchUtils.isUserInKorea() || CoreApplication.instance(context).isTestApplication()) {
                return new NothingLocationClient();
            }
            return new LocationClient(context, callbacks);
        }
    }

    public interface LocationClientCallbacks {
        void onConnected();

        void onLocationUpdated(Location location);
    }

    void connectLocationClient();

    void disconnectLocationClient();

    Location getLastLocation();

    boolean isLocationUpdateRequired(Location location);

    void requestLocationUpdates();
}
