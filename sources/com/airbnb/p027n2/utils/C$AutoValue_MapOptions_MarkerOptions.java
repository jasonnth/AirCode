package com.airbnb.p027n2.utils;

import com.airbnb.p027n2.utils.MapOptions.MarkerOptions;

/* renamed from: com.airbnb.n2.utils.$AutoValue_MapOptions_MarkerOptions reason: invalid class name */
abstract class C$AutoValue_MapOptions_MarkerOptions extends MarkerOptions {
    private final LatLng latLng;

    C$AutoValue_MapOptions_MarkerOptions(LatLng latLng2) {
        if (latLng2 == null) {
            throw new NullPointerException("Null latLng");
        }
        this.latLng = latLng2;
    }

    public LatLng latLng() {
        return this.latLng;
    }

    public String toString() {
        return "MarkerOptions{latLng=" + this.latLng + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof MarkerOptions)) {
            return false;
        }
        return this.latLng.equals(((MarkerOptions) o).latLng());
    }

    public int hashCode() {
        return (1 * 1000003) ^ this.latLng.hashCode();
    }
}
