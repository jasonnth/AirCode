package com.airbnb.android.airmapview;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesUtil;

public class DefaultAirMapViewBuilder {
    private static final String TAG = DefaultAirMapViewBuilder.class.getSimpleName();
    private final Context context;
    private final boolean isNativeMapSupported;

    public DefaultAirMapViewBuilder(Context context2) {
        this(context2, checkNativeMapSupported(context2));
    }

    public DefaultAirMapViewBuilder(Context context2, boolean isNativeMapSupported2) {
        this.isNativeMapSupported = isNativeMapSupported2;
        this.context = context2;
    }

    public AirMapViewBuilder builder() {
        if (this.isNativeMapSupported) {
            return new NativeAirMapViewBuilder();
        }
        return getWebMapViewBuilder();
    }

    public AirMapViewBuilder builder(AirMapViewTypes mapType) {
        switch (mapType) {
            case NATIVE:
                if (this.isNativeMapSupported) {
                    return new NativeAirMapViewBuilder();
                }
                break;
            case WEB:
                return getWebMapViewBuilder();
        }
        throw new UnsupportedOperationException("Requested map type is not supported");
    }

    private AirMapViewBuilder getWebMapViewBuilder() {
        if (this.context != null) {
            try {
                Bundle bundle = this.context.getPackageManager().getApplicationInfo(this.context.getPackageName(), 128).metaData;
                String accessToken = bundle.getString("com.mapbox.ACCESS_TOKEN");
                String mapId = bundle.getString("com.mapbox.MAP_ID");
                if (!TextUtils.isEmpty(accessToken) && !TextUtils.isEmpty(mapId)) {
                    return new MapboxWebMapViewBuilder(accessToken, mapId);
                }
            } catch (NameNotFoundException e) {
                Log.e(TAG, "Failed to load Mapbox access token and map id", e);
            }
        }
        return new WebAirMapViewBuilder();
    }

    private static boolean checkNativeMapSupported(Context context2) {
        return isGooglePlayServicesAvailable(context2);
    }

    private static boolean isGooglePlayServicesAvailable(Context context2) {
        return GooglePlayServicesUtil.isGooglePlayServicesAvailable(context2) == 0;
    }
}
