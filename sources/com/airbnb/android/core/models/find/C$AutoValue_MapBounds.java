package com.airbnb.android.core.models.find;

import com.google.android.gms.maps.model.LatLng;

/* renamed from: com.airbnb.android.core.models.find.$AutoValue_MapBounds reason: invalid class name */
abstract class C$AutoValue_MapBounds extends MapBounds {
    private final LatLng latLngNE;
    private final LatLng latLngSW;

    /* renamed from: com.airbnb.android.core.models.find.$AutoValue_MapBounds$Builder */
    static final class Builder extends com.airbnb.android.core.models.find.MapBounds.Builder {
        private LatLng latLngNE;
        private LatLng latLngSW;

        Builder() {
        }

        public com.airbnb.android.core.models.find.MapBounds.Builder latLngSW(LatLng latLngSW2) {
            if (latLngSW2 == null) {
                throw new NullPointerException("Null latLngSW");
            }
            this.latLngSW = latLngSW2;
            return this;
        }

        public com.airbnb.android.core.models.find.MapBounds.Builder latLngNE(LatLng latLngNE2) {
            if (latLngNE2 == null) {
                throw new NullPointerException("Null latLngNE");
            }
            this.latLngNE = latLngNE2;
            return this;
        }

        public MapBounds build() {
            String missing = "";
            if (this.latLngSW == null) {
                missing = missing + " latLngSW";
            }
            if (this.latLngNE == null) {
                missing = missing + " latLngNE";
            }
            if (missing.isEmpty()) {
                return new AutoValue_MapBounds(this.latLngSW, this.latLngNE);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_MapBounds(LatLng latLngSW2, LatLng latLngNE2) {
        if (latLngSW2 == null) {
            throw new NullPointerException("Null latLngSW");
        }
        this.latLngSW = latLngSW2;
        if (latLngNE2 == null) {
            throw new NullPointerException("Null latLngNE");
        }
        this.latLngNE = latLngNE2;
    }

    public LatLng latLngSW() {
        return this.latLngSW;
    }

    public LatLng latLngNE() {
        return this.latLngNE;
    }

    public String toString() {
        return "MapBounds{latLngSW=" + this.latLngSW + ", latLngNE=" + this.latLngNE + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof MapBounds)) {
            return false;
        }
        MapBounds that = (MapBounds) o;
        if (!this.latLngSW.equals(that.latLngSW()) || !this.latLngNE.equals(that.latLngNE())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((1 * 1000003) ^ this.latLngSW.hashCode()) * 1000003) ^ this.latLngNE.hashCode();
    }
}
