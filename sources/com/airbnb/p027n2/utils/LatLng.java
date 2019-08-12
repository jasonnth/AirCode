package com.airbnb.p027n2.utils;

import android.os.Parcelable;

/* renamed from: com.airbnb.n2.utils.LatLng */
public abstract class LatLng implements Parcelable {

    /* renamed from: com.airbnb.n2.utils.LatLng$Builder */
    public static abstract class Builder {
        public abstract LatLng build();

        public abstract Builder lat(double d);

        public abstract Builder lng(double d);
    }

    public abstract double lat();

    public abstract double lng();

    public static Builder builder() {
        return new Builder();
    }
}
