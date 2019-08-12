package com.airbnb.android.atlantis;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.location.LocationClientFacade.LocationClientCallbacks;
import com.airbnb.android.core.location.SimpleLocationListener;
import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.utils.ListUtils;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import java.util.Collection;

@SuppressLint({"MissingPermission"})
public class AtlantisLocationClient {
    private static final int CRITERIA_PRIORITY = 1;
    private static final long LOCATION_REQUEST_INTERVAL = 900000;
    private static final float REQUIRED_LOCATION_ACCURACY = 1000.0f;
    private final SimpleLocationListener androidLocationListener = new SimpleLocationListener() {
        public void onLocationChanged(Location location) {
            AtlantisLocationClient.this.handleLocationChanged(location);
        }
    };
    /* access modifiers changed from: private */
    public final LocationClientCallbacks callbacks;
    private final GoogleApiClient googleApiClient;
    private LocationManager locationManager;
    private final LocationListener playServicesLocationListener = AtlantisLocationClient$$Lambda$1.lambdaFactory$(this);

    AtlantisLocationClient(Context context, LocationClientCallbacks locationCallbacks) {
        this.callbacks = locationCallbacks;
        ConnectionCallbacks connectionCallbacks = new ConnectionCallbacks() {
            public void onConnected(Bundle bundle) {
                AtlantisLocationClient.this.callbacks.onConnected();
            }

            public void onConnectionSuspended(int i) {
                AtlantisLocationClient.this.disconnect();
            }
        };
        this.googleApiClient = new Builder(context).addApi(LocationServices.API).addConnectionCallbacks(connectionCallbacks).addOnConnectionFailedListener(AtlantisLocationClient$$Lambda$4.lambdaFactory$(this, context)).build();
    }

    static /* synthetic */ void lambda$new$0(AtlantisLocationClient atlantisLocationClient, Context context, ConnectionResult connectionResult) {
        if (atlantisLocationClient.locationManager == null) {
            atlantisLocationClient.locationManager = (LocationManager) context.getSystemService("location");
            atlantisLocationClient.callbacks.onConnected();
        }
    }

    public void connect() {
        this.googleApiClient.connect();
    }

    public void disconnect() {
        try {
            if (this.googleApiClient.isConnected() || this.googleApiClient.isConnecting()) {
                if (this.googleApiClient.isConnected()) {
                    LocationServices.FusedLocationApi.removeLocationUpdates(this.googleApiClient, this.playServicesLocationListener);
                }
                this.googleApiClient.disconnect();
            }
            if (this.locationManager != null) {
                this.locationManager.removeUpdates(this.androidLocationListener);
            }
        } catch (SecurityException e) {
            BugsnagWrapper.notify((Throwable) new SecurityException("Nothing we can do here, need phone to respect the android permission check."));
        }
    }

    public void requestLocationUpdates() {
        try {
            if (this.googleApiClient.isConnected()) {
                requestPlayServicesLocationUpdates();
            } else if (this.locationManager != null) {
                requestNativeLocationUpdates();
            }
        } catch (SecurityException e) {
            disconnect();
        } catch (IllegalArgumentException e2) {
            if (BuildHelper.isDevelopmentBuild()) {
                throw e2;
            }
        }
    }

    private void requestNativeLocationUpdates() {
        Criteria criteria = new Criteria();
        criteria.setAccuracy(1);
        if (ListUtils.isEmpty((Collection<?>) this.locationManager.getProviders(criteria, true))) {
            this.locationManager.requestLocationUpdates("gps", LOCATION_REQUEST_INTERVAL, REQUIRED_LOCATION_ACCURACY, this.androidLocationListener);
        }
    }

    private void requestPlayServicesLocationUpdates() {
        LocationServices.FusedLocationApi.requestLocationUpdates(this.googleApiClient, LocationRequest.create().setPriority(100).setInterval(LOCATION_REQUEST_INTERVAL), this.playServicesLocationListener);
    }

    /* access modifiers changed from: private */
    public void handleLocationChanged(Location location) {
        this.callbacks.onLocationUpdated(location);
    }
}
