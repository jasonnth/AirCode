package com.airbnb.p027n2.utils;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.airbnb.n2.utils.AutoValue_LatLng */
final class AutoValue_LatLng extends C$AutoValue_LatLng {
    public static final Creator<AutoValue_LatLng> CREATOR = new Creator<AutoValue_LatLng>() {
        public AutoValue_LatLng createFromParcel(Parcel in) {
            return new AutoValue_LatLng(in.readDouble(), in.readDouble());
        }

        public AutoValue_LatLng[] newArray(int size) {
            return new AutoValue_LatLng[size];
        }
    };

    AutoValue_LatLng(double lat, double lng) {
        super(lat, lng);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(lat());
        dest.writeDouble(lng());
    }

    public int describeContents() {
        return 0;
    }
}
