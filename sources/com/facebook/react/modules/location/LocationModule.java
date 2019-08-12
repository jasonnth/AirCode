package com.facebook.react.modules.location;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import com.airbnb.android.core.requests.UpdateReviewRequest;
import com.airbnb.android.erf.p010db.ErfExperimentsModel;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.SystemClock;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter;

@ReactModule(name = "LocationObserver")
public class LocationModule extends ReactContextBaseJavaModule {
    private static final float RCT_DEFAULT_LOCATION_ACCURACY = 100.0f;
    private final LocationListener mLocationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            ((RCTDeviceEventEmitter) LocationModule.this.getReactApplicationContext().getJSModule(RCTDeviceEventEmitter.class)).emit("geolocationDidChange", LocationModule.locationToMap(location));
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
            if (status == 0) {
                LocationModule.this.emitError(PositionError.POSITION_UNAVAILABLE, "Provider " + provider + " is out of service.");
            } else if (status == 1) {
                LocationModule.this.emitError(PositionError.TIMEOUT, "Provider " + provider + " is temporarily unavailable.");
            }
        }

        public void onProviderEnabled(String provider) {
        }

        public void onProviderDisabled(String provider) {
        }
    };
    private String mWatchedProvider;

    private static class LocationOptions {
        /* access modifiers changed from: private */
        public final float distanceFilter;
        /* access modifiers changed from: private */
        public final boolean highAccuracy;
        /* access modifiers changed from: private */
        public final double maximumAge;
        /* access modifiers changed from: private */
        public final long timeout;

        private LocationOptions(long timeout2, double maximumAge2, boolean highAccuracy2, float distanceFilter2) {
            this.timeout = timeout2;
            this.maximumAge = maximumAge2;
            this.highAccuracy = highAccuracy2;
            this.distanceFilter = distanceFilter2;
        }

        /* access modifiers changed from: private */
        public static LocationOptions fromReactMap(ReadableMap map) {
            return new LocationOptions(map.hasKey("timeout") ? (long) map.getDouble("timeout") : Long.MAX_VALUE, map.hasKey("maximumAge") ? map.getDouble("maximumAge") : Double.POSITIVE_INFINITY, map.hasKey("enableHighAccuracy") && map.getBoolean("enableHighAccuracy"), map.hasKey("distanceFilter") ? (float) map.getDouble("distanceFilter") : LocationModule.RCT_DEFAULT_LOCATION_ACCURACY);
        }
    }

    private static class SingleUpdateRequest {
        /* access modifiers changed from: private */
        public final Callback mError;
        /* access modifiers changed from: private */
        public final Handler mHandler;
        /* access modifiers changed from: private */
        public final LocationListener mLocationListener;
        /* access modifiers changed from: private */
        public final LocationManager mLocationManager;
        private final String mProvider;
        /* access modifiers changed from: private */
        public final Callback mSuccess;
        private final long mTimeout;
        /* access modifiers changed from: private */
        public final Runnable mTimeoutRunnable;
        /* access modifiers changed from: private */
        public boolean mTriggered;

        private SingleUpdateRequest(LocationManager locationManager, String provider, long timeout, Callback success, Callback error) {
            this.mHandler = new Handler();
            this.mTimeoutRunnable = new Runnable() {
                public void run() {
                    synchronized (SingleUpdateRequest.this) {
                        if (!SingleUpdateRequest.this.mTriggered) {
                            SingleUpdateRequest.this.mError.invoke(PositionError.buildError(PositionError.TIMEOUT, "Location request timed out"));
                            SingleUpdateRequest.this.mLocationManager.removeUpdates(SingleUpdateRequest.this.mLocationListener);
                            SingleUpdateRequest.this.mTriggered = true;
                        }
                    }
                }
            };
            this.mLocationListener = new LocationListener() {
                public void onLocationChanged(Location location) {
                    synchronized (SingleUpdateRequest.this) {
                        if (!SingleUpdateRequest.this.mTriggered) {
                            SingleUpdateRequest.this.mSuccess.invoke(LocationModule.locationToMap(location));
                            SingleUpdateRequest.this.mHandler.removeCallbacks(SingleUpdateRequest.this.mTimeoutRunnable);
                            SingleUpdateRequest.this.mTriggered = true;
                        }
                    }
                }

                public void onStatusChanged(String provider, int status, Bundle extras) {
                }

                public void onProviderEnabled(String provider) {
                }

                public void onProviderDisabled(String provider) {
                }
            };
            this.mLocationManager = locationManager;
            this.mProvider = provider;
            this.mTimeout = timeout;
            this.mSuccess = success;
            this.mError = error;
        }

        public void invoke() {
            this.mLocationManager.requestSingleUpdate(this.mProvider, this.mLocationListener, null);
            this.mHandler.postDelayed(this.mTimeoutRunnable, this.mTimeout);
        }
    }

    public LocationModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    public String getName() {
        return "LocationObserver";
    }

    @ReactMethod
    public void getCurrentPosition(ReadableMap options, Callback success, Callback error) {
        LocationOptions locationOptions = LocationOptions.fromReactMap(options);
        try {
            LocationManager locationManager = (LocationManager) getReactApplicationContext().getSystemService("location");
            String provider = getValidProvider(locationManager, locationOptions.highAccuracy);
            if (provider == null) {
                error.invoke("No available location provider.");
                return;
            }
            Location location = locationManager.getLastKnownLocation(provider);
            if (location == null || ((double) (SystemClock.currentTimeMillis() - location.getTime())) >= locationOptions.maximumAge) {
                new SingleUpdateRequest(locationManager, provider, locationOptions.timeout, success, error).invoke();
                return;
            }
            success.invoke(locationToMap(location));
        } catch (SecurityException e) {
            throwLocationPermissionMissing(e);
        }
    }

    @ReactMethod
    public void startObserving(ReadableMap options) {
        if (!"gps".equals(this.mWatchedProvider)) {
            LocationOptions locationOptions = LocationOptions.fromReactMap(options);
            try {
                LocationManager locationManager = (LocationManager) getReactApplicationContext().getSystemService("location");
                String provider = getValidProvider(locationManager, locationOptions.highAccuracy);
                if (provider == null) {
                    emitError(PositionError.PERMISSION_DENIED, "No location provider available.");
                    return;
                }
                if (!provider.equals(this.mWatchedProvider)) {
                    locationManager.removeUpdates(this.mLocationListener);
                    locationManager.requestLocationUpdates(provider, 1000, locationOptions.distanceFilter, this.mLocationListener);
                }
                this.mWatchedProvider = provider;
            } catch (SecurityException e) {
                throwLocationPermissionMissing(e);
            }
        }
    }

    @ReactMethod
    public void stopObserving() {
        ((LocationManager) getReactApplicationContext().getSystemService("location")).removeUpdates(this.mLocationListener);
        this.mWatchedProvider = null;
    }

    private static String getValidProvider(LocationManager locationManager, boolean highAccuracy) {
        String provider = highAccuracy ? "gps" : "network";
        if (!locationManager.isProviderEnabled(provider)) {
            provider = provider.equals("gps") ? "network" : "gps";
            if (!locationManager.isProviderEnabled(provider)) {
                return null;
            }
        }
        return provider;
    }

    /* access modifiers changed from: private */
    public static WritableMap locationToMap(Location location) {
        WritableMap map = Arguments.createMap();
        WritableMap coords = Arguments.createMap();
        coords.putDouble("latitude", location.getLatitude());
        coords.putDouble("longitude", location.getLongitude());
        coords.putDouble("altitude", location.getAltitude());
        coords.putDouble(UpdateReviewRequest.KEY_ACCURACY, (double) location.getAccuracy());
        coords.putDouble("heading", (double) location.getBearing());
        coords.putDouble("speed", (double) location.getSpeed());
        map.putMap("coords", coords);
        map.putDouble(ErfExperimentsModel.TIMESTAMP, (double) location.getTime());
        if (VERSION.SDK_INT >= 18) {
            map.putBoolean("mocked", location.isFromMockProvider());
        }
        return map;
    }

    /* access modifiers changed from: private */
    public void emitError(int code, String message) {
        ((RCTDeviceEventEmitter) getReactApplicationContext().getJSModule(RCTDeviceEventEmitter.class)).emit("geolocationError", PositionError.buildError(code, message));
    }

    private static void throwLocationPermissionMissing(SecurityException e) {
        throw new SecurityException("Looks like the app doesn't have the permission to access location.\nAdd the following line to your app's AndroidManifest.xml:\n<uses-permission android:name=\"android.permission.ACCESS_FINE_LOCATION\" />", e);
    }
}
