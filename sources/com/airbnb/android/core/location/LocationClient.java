package com.airbnb.android.core.location;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.location.LocationClientFacade.LocationClientCallbacks;
import com.airbnb.android.core.utils.BuildHelper;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import java.util.List;

@SuppressLint({"MissingPermission"})
final class LocationClient implements LocationClientFacade {
    private static final int CRITERIA_PRIORITY = 1;
    private static final long LOCATION_TIMEOUT = 30000;
    private static final float REQUIRED_LOCATION_ACCURACY = 1000.0f;
    private static final long REQUIRED_LOCATION_TIME = 900000;
    private final SimpleLocationListener androidLocationListener = new SimpleLocationListener() {
        public void onLocationChanged(Location location) {
            LocationClient.this.handleLocationChanged(location);
        }
    };
    /* access modifiers changed from: private */
    public final LocationClientCallbacks callbacks;
    private final GoogleApiClient googleApiClient;
    private boolean isConnected;
    private LocationManager locationManager;
    private boolean locationUpdated;
    private final LocationListener playServicesLocationListener = LocationClient$$Lambda$1.lambdaFactory$(this);
    private boolean updateClientOnNewLocation;

    LocationClient(Context context, LocationClientCallbacks locationCallbacks) {
        if (locationCallbacks != null) {
            this.callbacks = locationCallbacks;
        } else {
            this.callbacks = new LocationClientCallbacks() {
                public void onConnected() {
                    LocationClient.this.requestLocationUpdates();
                }

                public void onLocationUpdated(Location location) {
                    LocationClient.this.disconnectLocationClient();
                }
            };
        }
        ConnectionCallbacks connectionCallbacks = new ConnectionCallbacks() {
            public void onConnected(Bundle bundle) {
                LocationClient.this.callbacks.onConnected();
            }

            public void onConnectionSuspended(int i) {
                LocationClient.this.disconnectLocationClient();
            }
        };
        this.googleApiClient = new Builder(context).addApi(LocationServices.API).addConnectionCallbacks(connectionCallbacks).addOnConnectionFailedListener(LocationClient$$Lambda$2.lambdaFactory$(this, context)).build();
    }

    static /* synthetic */ void lambda$new$0(LocationClient locationClient, Context context, ConnectionResult connectionResult) {
        if (locationClient.isConnected) {
            locationClient.locationManager = (LocationManager) context.getSystemService("location");
            locationClient.callbacks.onConnected();
        }
    }

    public void connectLocationClient() {
        this.isConnected = true;
        this.googleApiClient.connect();
    }

    public void disconnectLocationClient() {
        this.isConnected = false;
        if (this.googleApiClient.isConnected() || this.googleApiClient.isConnecting()) {
            if (this.googleApiClient.isConnected()) {
                LocationServices.FusedLocationApi.removeLocationUpdates(this.googleApiClient, this.playServicesLocationListener);
            }
            this.googleApiClient.disconnect();
        }
        if (this.locationManager != null) {
            try {
                this.locationManager.removeUpdates(this.androidLocationListener);
            } catch (SecurityException e) {
                handleDeviceHasNoLocationPermissionButClaimItHas();
            }
        }
        this.updateClientOnNewLocation = false;
    }

    public Location getLastLocation() {
        if (this.googleApiClient.isConnected()) {
            try {
                return LocationServices.FusedLocationApi.getLastLocation(this.googleApiClient);
            } catch (SecurityException e) {
                handleDeviceHasNoLocationPermissionButClaimItHas();
            }
        }
        if (this.locationManager != null) {
            try {
                return this.locationManager.getLastKnownLocation("passive");
            } catch (SecurityException e2) {
                handleDeviceHasNoLocationPermissionButClaimItHas();
            }
        }
        return null;
    }

    public void requestLocationUpdates() {
        Location location = null;
        this.locationUpdated = false;
        if (this.googleApiClient.isConnected()) {
            location = requestPlayServicesLocationUpdates();
        } else if (this.locationManager != null) {
            location = requestNativeLocationUpdates();
        }
        if (this.locationUpdated) {
            this.callbacks.onLocationUpdated(location);
        } else {
            this.updateClientOnNewLocation = true;
        }
    }

    private Location requestNativeLocationUpdates() {
        Location location = null;
        try {
            location = this.locationManager.getLastKnownLocation("passive");
            if (isLocationUpdateRequired(location)) {
                Criteria criteria = new Criteria();
                criteria.setAccuracy(1);
                List<String> providers = this.locationManager.getProviders(criteria, true);
                if (providers != null && !providers.isEmpty()) {
                    this.locationManager.requestSingleUpdate(criteria, this.androidLocationListener, null);
                }
                this.locationUpdated = false;
                return location;
            }
            this.locationUpdated = true;
            return location;
        } catch (IllegalArgumentException e) {
            if (BuildHelper.isDevelopmentBuild()) {
                throw e;
            }
        } catch (SecurityException e2) {
            disconnectLocationClient();
        }
    }

    private Location requestPlayServicesLocationUpdates() {
        Location location = null;
        try {
            location = LocationServices.FusedLocationApi.getLastLocation(this.googleApiClient);
        } catch (SecurityException e) {
            handleDeviceHasNoLocationPermissionButClaimItHas();
        }
        if (isLocationUpdateRequired(location)) {
            try {
                LocationServices.FusedLocationApi.requestLocationUpdates(this.googleApiClient, LocationRequest.create().setPriority(102).setExpirationDuration(LOCATION_TIMEOUT).setNumUpdates(1), this.playServicesLocationListener);
            } catch (SecurityException e2) {
                handleDeviceHasNoLocationPermissionButClaimItHas();
            }
            this.locationUpdated = false;
        } else {
            this.locationUpdated = true;
        }
        return location;
    }

    /* access modifiers changed from: private */
    public void handleLocationChanged(Location location) {
        this.locationUpdated = true;
        if (this.updateClientOnNewLocation) {
            this.callbacks.onLocationUpdated(location);
            this.updateClientOnNewLocation = false;
        }
    }

    public boolean isLocationUpdateRequired(Location location) {
        return location == null || location.getAccuracy() > REQUIRED_LOCATION_ACCURACY || System.currentTimeMillis() - location.getTime() > REQUIRED_LOCATION_TIME;
    }

    private void handleDeviceHasNoLocationPermissionButClaimItHas() {
        BugsnagWrapper.throwOrNotify((RuntimeException) new SecurityException("Nothing we can do here, need phone's firmware update to respect the android permission check."));
    }
}
