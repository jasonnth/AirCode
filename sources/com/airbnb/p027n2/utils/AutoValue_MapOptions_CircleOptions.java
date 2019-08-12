package com.airbnb.p027n2.utils;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.airbnb.n2.utils.AutoValue_MapOptions_CircleOptions */
final class AutoValue_MapOptions_CircleOptions extends C$AutoValue_MapOptions_CircleOptions {
    public static final Creator<AutoValue_MapOptions_CircleOptions> CREATOR = new Creator<AutoValue_MapOptions_CircleOptions>() {
        public AutoValue_MapOptions_CircleOptions createFromParcel(Parcel in) {
            return new AutoValue_MapOptions_CircleOptions((LatLng) in.readParcelable(LatLng.class.getClassLoader()), in.readInt());
        }

        public AutoValue_MapOptions_CircleOptions[] newArray(int size) {
            return new AutoValue_MapOptions_CircleOptions[size];
        }
    };

    AutoValue_MapOptions_CircleOptions(LatLng center, int radiusMeters) {
        super(center, radiusMeters);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(center(), flags);
        dest.writeInt(radiusMeters());
    }

    public int describeContents() {
        return 0;
    }
}
