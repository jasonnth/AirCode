package com.airbnb.p027n2.utils;

import android.os.Parcelable;

/* renamed from: com.airbnb.n2.utils.MapOptions */
public abstract class MapOptions implements Parcelable {
    private static final int MAP_DEFAULT_ZOOM_LEVEL = 12;

    /* renamed from: com.airbnb.n2.utils.MapOptions$Builder */
    public static abstract class Builder {
        public abstract MapOptions build();

        public abstract Builder center(LatLng latLng);

        public abstract Builder circle(CircleOptions circleOptions);

        public abstract Builder isUserInChina(boolean z);

        public abstract Builder marker(MarkerOptions markerOptions);

        public abstract Builder useDlsMapType(boolean z);

        public abstract Builder zoom(int i);
    }

    /* renamed from: com.airbnb.n2.utils.MapOptions$CircleOptions */
    public static abstract class CircleOptions implements Parcelable {
        public static final int DEFAULT_CIRCLE_RADIUS_METERS = 800;

        public abstract LatLng center();

        public abstract int radiusMeters();

        public static CircleOptions create(LatLng center) {
            return new AutoValue_MapOptions_CircleOptions(center, 800);
        }

        public static CircleOptions create(LatLng center, int radiusMeters) {
            return new AutoValue_MapOptions_CircleOptions(center, radiusMeters);
        }
    }

    /* renamed from: com.airbnb.n2.utils.MapOptions$MarkerOptions */
    public static abstract class MarkerOptions implements Parcelable {
        public abstract LatLng latLng();

        public static MarkerOptions create(LatLng latLng) {
            return new AutoValue_MapOptions_MarkerOptions(latLng);
        }
    }

    public abstract LatLng center();

    public abstract CircleOptions circle();

    public abstract boolean isUserInChina();

    public abstract MarkerOptions marker();

    public abstract boolean useDlsMapType();

    public abstract int zoom();

    public static Builder builder(boolean isUserInChina) {
        return new Builder().useDlsMapType(false).zoom(12).isUserInChina(isUserInChina);
    }

    public static Builder builder(MapOptions mapOptions) {
        return builder(mapOptions.isUserInChina()).center(mapOptions.center()).zoom(mapOptions.zoom()).isUserInChina(mapOptions.isUserInChina()).marker(mapOptions.marker()).circle(mapOptions.circle()).useDlsMapType(mapOptions.useDlsMapType());
    }
}
