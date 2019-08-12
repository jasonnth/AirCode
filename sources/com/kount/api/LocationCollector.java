package com.kount.api;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import java.util.Date;
import java.util.List;
import java.util.Locale;

class LocationCollector extends CollectorTaskBase implements LocationListener {
    private Location currentLocation;
    private boolean foundLocation;
    private final LocationManager locationManager;

    LocationCollector(Object debugHandler, Context ctx) {
        super(debugHandler);
        this.locationManager = (LocationManager) ctx.getSystemService("location");
    }

    /* access modifiers changed from: 0000 */
    public String getName() {
        return "Location Collector";
    }

    /* access modifiers changed from: 0000 */
    public String getInternalName() {
        return internalName();
    }

    static String internalName() {
        return "collector_geo_loc";
    }

    /* access modifiers changed from: 0000 */
    public void collect() {
        if (Looper.myLooper() == null) {
            Looper.prepare();
        }
        List<String> providers = getLocationProviders();
        if (!isLocationServiceEnabled()) {
            debugMessage("No providers available");
            addSoftError(SoftError.SERVICE_UNAVAILABLE.toString());
            callCompletionHandler(Boolean.valueOf(false), null);
            return;
        }
        Date started = new Date();
        String bestProvider = null;
        for (String provider : providers) {
            debugMessage(String.format("Trying provider %s", new Object[]{provider}));
            if (bestProvider == null) {
                bestProvider = provider;
            }
            Location location = getLastKnownLocation(provider);
            if (location != null) {
                debugMessage(String.format(Locale.US, "Got location for %s: %f,%f,%f", new Object[]{provider, Double.valueOf(location.getLongitude()), Double.valueOf(location.getLatitude()), Float.valueOf(location.getAccuracy())}));
                long difference = started.getTime() - location.getTime();
                debugMessage(String.format(Locale.US, "Age: %d", new Object[]{Long.valueOf(difference)}));
                if (difference < 86400000 && isBetterLocation(location, this.currentLocation)) {
                    debugMessage(location.getProvider() + " is better location");
                    this.currentLocation = location;
                    bestProvider = provider;
                    this.foundLocation = true;
                }
            }
        }
        if (!this.foundLocation || bestProvider == null) {
            debugMessage("Making a single request");
            makeLocationRequest(bestProvider);
            return;
        }
        wrapUpLocationCollection();
    }

    /* access modifiers changed from: protected */
    public boolean isLocationServiceEnabled() {
        boolean z;
        boolean gps_enabled = false;
        boolean network_enabled = false;
        try {
            gps_enabled = this.locationManager.isProviderEnabled("gps");
        } catch (Exception e) {
        }
        try {
            network_enabled = this.locationManager.isProviderEnabled("network");
        } catch (Exception e2) {
        }
        if (this.locationManager.getProviders(true).size() > 0) {
            z = true;
        } else {
            z = false;
        }
        Boolean providers = Boolean.valueOf(z);
        if ((gps_enabled || network_enabled) && providers.booleanValue()) {
            return true;
        }
        return false;
    }

    private boolean isBetterLocation(Location location, Location oldLocation) {
        if (oldLocation != null && ((int) (location.getAccuracy() - oldLocation.getAccuracy())) >= 0) {
            return false;
        }
        return true;
    }

    private void wrapUpLocationCollection() {
        stopListeningForLocationUpdates();
        if (this.currentLocation != null) {
            addDataPoint(PostKey.LOCATION_LATITUDE.toString(), Double.toString(this.currentLocation.getLatitude()));
            addDataPoint(PostKey.LOCATION_LONGITUDE.toString(), Double.toString(this.currentLocation.getLongitude()));
            addDataPoint(PostKey.LOCATION_DATE.toString(), Long.toString(this.currentLocation.getTime() / 1000));
            this.foundLocation = true;
        } else {
            debugMessage("No Location found.");
        }
        callCompletionHandler(Boolean.valueOf(true), null);
    }

    private void stopListeningForLocationUpdates() {
        try {
            this.locationManager.removeUpdates(this);
        } catch (SecurityException e) {
            debugMessage(String.format("SecurityException: %s", new Object[]{e.getMessage()}));
        }
    }

    public void onLocationChanged(Location loc) {
        if (isBetterLocation(loc, this.currentLocation)) {
            this.currentLocation = loc;
        }
        stopListeningForLocationUpdates();
        wrapUpLocationCollection();
    }

    public void onProviderDisabled(String provider) {
    }

    public void onProviderEnabled(String provider) {
    }

    public void onStatusChanged(String provider, int status, Bundle bundle) {
    }

    /* access modifiers changed from: protected */
    public List<String> getLocationProviders() {
        return this.locationManager.getProviders(true);
    }

    /* access modifiers changed from: protected */
    public Location getLastKnownLocation(String provider) {
        Location result = null;
        try {
            return this.locationManager.getLastKnownLocation(provider);
        } catch (SecurityException e) {
            debugMessage(String.format("SecurityException: %s", new Object[]{e.getMessage()}));
            addSoftError(SoftError.PERMISSION_DENIED.toString());
            callCompletionHandler(Boolean.valueOf(false), null);
            return result;
        }
    }

    /* access modifiers changed from: protected */
    public void makeLocationRequest(String providerName) {
        try {
            if (providerName.compareTo("passive") == 0) {
                providerName = "gps";
            }
            this.locationManager.requestSingleUpdate(providerName, this, null);
            debugMessage(String.format("Requesting location from %s", new Object[]{providerName}));
        } catch (SecurityException e) {
            debugMessage(String.format("SecurityException: %s", new Object[]{e.getMessage()}));
            addSoftError(SoftError.PERMISSION_DENIED.toString());
            callCompletionHandler(Boolean.valueOf(false), null);
        }
    }
}
