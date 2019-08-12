package com.airbnb.p027n2.utils;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.airbnb.n2.utils.AutoValue_MapOptions_MarkerOptions */
final class AutoValue_MapOptions_MarkerOptions extends C$AutoValue_MapOptions_MarkerOptions {
    public static final Creator<AutoValue_MapOptions_MarkerOptions> CREATOR = new Creator<AutoValue_MapOptions_MarkerOptions>() {
        public AutoValue_MapOptions_MarkerOptions createFromParcel(Parcel in) {
            return new AutoValue_MapOptions_MarkerOptions((LatLng) in.readParcelable(LatLng.class.getClassLoader()));
        }

        public AutoValue_MapOptions_MarkerOptions[] newArray(int size) {
            return new AutoValue_MapOptions_MarkerOptions[size];
        }
    };

    AutoValue_MapOptions_MarkerOptions(LatLng latLng) {
        super(latLng);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(latLng(), flags);
    }

    public int describeContents() {
        return 0;
    }
}
