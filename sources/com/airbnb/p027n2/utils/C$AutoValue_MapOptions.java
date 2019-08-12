package com.airbnb.p027n2.utils;

import com.airbnb.p027n2.utils.MapOptions.CircleOptions;
import com.airbnb.p027n2.utils.MapOptions.MarkerOptions;

/* renamed from: com.airbnb.n2.utils.$AutoValue_MapOptions reason: invalid class name */
abstract class C$AutoValue_MapOptions extends MapOptions {
    private final LatLng center;
    private final CircleOptions circle;
    private final boolean isUserInChina;
    private final MarkerOptions marker;
    private final boolean useDlsMapType;
    private final int zoom;

    /* renamed from: com.airbnb.n2.utils.$AutoValue_MapOptions$Builder */
    static final class Builder extends com.airbnb.p027n2.utils.MapOptions.Builder {
        private LatLng center;
        private CircleOptions circle;
        private Boolean isUserInChina;
        private MarkerOptions marker;
        private Boolean useDlsMapType;
        private Integer zoom;

        Builder() {
        }

        public com.airbnb.p027n2.utils.MapOptions.Builder center(LatLng center2) {
            if (center2 == null) {
                throw new NullPointerException("Null center");
            }
            this.center = center2;
            return this;
        }

        public com.airbnb.p027n2.utils.MapOptions.Builder zoom(int zoom2) {
            this.zoom = Integer.valueOf(zoom2);
            return this;
        }

        public com.airbnb.p027n2.utils.MapOptions.Builder marker(MarkerOptions marker2) {
            this.marker = marker2;
            return this;
        }

        public com.airbnb.p027n2.utils.MapOptions.Builder circle(CircleOptions circle2) {
            this.circle = circle2;
            return this;
        }

        public com.airbnb.p027n2.utils.MapOptions.Builder useDlsMapType(boolean useDlsMapType2) {
            this.useDlsMapType = Boolean.valueOf(useDlsMapType2);
            return this;
        }

        public com.airbnb.p027n2.utils.MapOptions.Builder isUserInChina(boolean isUserInChina2) {
            this.isUserInChina = Boolean.valueOf(isUserInChina2);
            return this;
        }

        public MapOptions build() {
            String missing = "";
            if (this.center == null) {
                missing = missing + " center";
            }
            if (this.zoom == null) {
                missing = missing + " zoom";
            }
            if (this.useDlsMapType == null) {
                missing = missing + " useDlsMapType";
            }
            if (this.isUserInChina == null) {
                missing = missing + " isUserInChina";
            }
            if (missing.isEmpty()) {
                return new AutoValue_MapOptions(this.center, this.zoom.intValue(), this.marker, this.circle, this.useDlsMapType.booleanValue(), this.isUserInChina.booleanValue());
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_MapOptions(LatLng center2, int zoom2, MarkerOptions marker2, CircleOptions circle2, boolean useDlsMapType2, boolean isUserInChina2) {
        if (center2 == null) {
            throw new NullPointerException("Null center");
        }
        this.center = center2;
        this.zoom = zoom2;
        this.marker = marker2;
        this.circle = circle2;
        this.useDlsMapType = useDlsMapType2;
        this.isUserInChina = isUserInChina2;
    }

    public LatLng center() {
        return this.center;
    }

    public int zoom() {
        return this.zoom;
    }

    public MarkerOptions marker() {
        return this.marker;
    }

    public CircleOptions circle() {
        return this.circle;
    }

    public boolean useDlsMapType() {
        return this.useDlsMapType;
    }

    public boolean isUserInChina() {
        return this.isUserInChina;
    }

    public String toString() {
        return "MapOptions{center=" + this.center + ", zoom=" + this.zoom + ", marker=" + this.marker + ", circle=" + this.circle + ", useDlsMapType=" + this.useDlsMapType + ", isUserInChina=" + this.isUserInChina + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof MapOptions)) {
            return false;
        }
        MapOptions that = (MapOptions) o;
        if (!this.center.equals(that.center()) || this.zoom != that.zoom() || (this.marker != null ? !this.marker.equals(that.marker()) : that.marker() != null) || (this.circle != null ? !this.circle.equals(that.circle()) : that.circle() != null) || this.useDlsMapType != that.useDlsMapType() || this.isUserInChina != that.isUserInChina()) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int i;
        int i2 = 1231;
        int i3 = 0;
        int h = ((((((1 * 1000003) ^ this.center.hashCode()) * 1000003) ^ this.zoom) * 1000003) ^ (this.marker == null ? 0 : this.marker.hashCode())) * 1000003;
        if (this.circle != null) {
            i3 = this.circle.hashCode();
        }
        int h2 = (h ^ i3) * 1000003;
        if (this.useDlsMapType) {
            i = 1231;
        } else {
            i = 1237;
        }
        int h3 = (h2 ^ i) * 1000003;
        if (!this.isUserInChina) {
            i2 = 1237;
        }
        return h3 ^ i2;
    }
}
