package com.airbnb.android.core.models.find;

import android.location.Location;
import android.os.Parcelable;
import com.google.android.gms.maps.model.LatLng;

public abstract class MapBounds implements Parcelable {

    public static abstract class Builder {
        public abstract MapBounds build();

        public abstract Builder latLngNE(LatLng latLng);

        public abstract Builder latLngSW(LatLng latLng);
    }

    public abstract LatLng latLngNE();

    public abstract LatLng latLngSW();

    public static Builder builder() {
        return new Builder();
    }

    public float diagonalDistanceInMeters() {
        float[] distanceBetween = new float[1];
        Location.distanceBetween(latLngNE().latitude, latLngNE().longitude, latLngSW().latitude, latLngSW().longitude, distanceBetween);
        return distanceBetween[0];
    }

    public boolean isBroaderThanCity() {
        return diagonalDistanceInMeters() > 50000.0f;
    }
}
