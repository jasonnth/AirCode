package com.airbnb.p027n2.utils;

import com.airbnb.p027n2.utils.MapOptions.CircleOptions;

/* renamed from: com.airbnb.n2.utils.$AutoValue_MapOptions_CircleOptions reason: invalid class name */
abstract class C$AutoValue_MapOptions_CircleOptions extends CircleOptions {
    private final LatLng center;
    private final int radiusMeters;

    C$AutoValue_MapOptions_CircleOptions(LatLng center2, int radiusMeters2) {
        if (center2 == null) {
            throw new NullPointerException("Null center");
        }
        this.center = center2;
        this.radiusMeters = radiusMeters2;
    }

    public LatLng center() {
        return this.center;
    }

    public int radiusMeters() {
        return this.radiusMeters;
    }

    public String toString() {
        return "CircleOptions{center=" + this.center + ", radiusMeters=" + this.radiusMeters + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof CircleOptions)) {
            return false;
        }
        CircleOptions that = (CircleOptions) o;
        if (!this.center.equals(that.center()) || this.radiusMeters != that.radiusMeters()) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((1 * 1000003) ^ this.center.hashCode()) * 1000003) ^ this.radiusMeters;
    }
}
