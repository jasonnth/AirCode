package com.airbnb.android.core.location;

import android.location.Location;

final class NothingLocationClient implements LocationClientFacade {
    NothingLocationClient() {
    }

    public void connectLocationClient() {
    }

    public void disconnectLocationClient() {
    }

    public void requestLocationUpdates() {
    }

    public boolean isLocationUpdateRequired(Location location) {
        return false;
    }

    public Location getLastLocation() {
        return null;
    }
}
