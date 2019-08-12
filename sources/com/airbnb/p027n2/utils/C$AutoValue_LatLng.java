package com.airbnb.p027n2.utils;

/* renamed from: com.airbnb.n2.utils.$AutoValue_LatLng reason: invalid class name */
abstract class C$AutoValue_LatLng extends LatLng {
    private final double lat;
    private final double lng;

    /* renamed from: com.airbnb.n2.utils.$AutoValue_LatLng$Builder */
    static final class Builder extends com.airbnb.p027n2.utils.LatLng.Builder {
        private Double lat;
        private Double lng;

        Builder() {
        }

        public com.airbnb.p027n2.utils.LatLng.Builder lat(double lat2) {
            this.lat = Double.valueOf(lat2);
            return this;
        }

        public com.airbnb.p027n2.utils.LatLng.Builder lng(double lng2) {
            this.lng = Double.valueOf(lng2);
            return this;
        }

        public LatLng build() {
            String missing = "";
            if (this.lat == null) {
                missing = missing + " lat";
            }
            if (this.lng == null) {
                missing = missing + " lng";
            }
            if (missing.isEmpty()) {
                return new AutoValue_LatLng(this.lat.doubleValue(), this.lng.doubleValue());
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_LatLng(double lat2, double lng2) {
        this.lat = lat2;
        this.lng = lng2;
    }

    public double lat() {
        return this.lat;
    }

    public double lng() {
        return this.lng;
    }

    public String toString() {
        return "LatLng{lat=" + this.lat + ", lng=" + this.lng + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof LatLng)) {
            return false;
        }
        LatLng that = (LatLng) o;
        if (Double.doubleToLongBits(this.lat) == Double.doubleToLongBits(that.lat()) && Double.doubleToLongBits(this.lng) == Double.doubleToLongBits(that.lng())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (int) (((long) (((int) (((long) (1 * 1000003)) ^ ((Double.doubleToLongBits(this.lat) >>> 32) ^ Double.doubleToLongBits(this.lat)))) * 1000003)) ^ ((Double.doubleToLongBits(this.lng) >>> 32) ^ Double.doubleToLongBits(this.lng)));
    }
}
