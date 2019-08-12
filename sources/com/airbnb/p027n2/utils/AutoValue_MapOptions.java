package com.airbnb.p027n2.utils;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.p027n2.utils.MapOptions.CircleOptions;
import com.airbnb.p027n2.utils.MapOptions.MarkerOptions;

/* renamed from: com.airbnb.n2.utils.AutoValue_MapOptions */
final class AutoValue_MapOptions extends C$AutoValue_MapOptions {
    public static final Creator<AutoValue_MapOptions> CREATOR = new Creator<AutoValue_MapOptions>() {
        public AutoValue_MapOptions createFromParcel(Parcel in) {
            boolean z;
            boolean z2 = true;
            LatLng latLng = (LatLng) in.readParcelable(LatLng.class.getClassLoader());
            int readInt = in.readInt();
            MarkerOptions markerOptions = (MarkerOptions) in.readParcelable(MarkerOptions.class.getClassLoader());
            CircleOptions circleOptions = (CircleOptions) in.readParcelable(CircleOptions.class.getClassLoader());
            if (in.readInt() == 1) {
                z = true;
            } else {
                z = false;
            }
            if (in.readInt() != 1) {
                z2 = false;
            }
            return new AutoValue_MapOptions(latLng, readInt, markerOptions, circleOptions, z, z2);
        }

        public AutoValue_MapOptions[] newArray(int size) {
            return new AutoValue_MapOptions[size];
        }
    };

    AutoValue_MapOptions(LatLng center, int zoom, MarkerOptions marker, CircleOptions circle, boolean useDlsMapType, boolean isUserInChina) {
        super(center, zoom, marker, circle, useDlsMapType, isUserInChina);
    }

    public void writeToParcel(Parcel dest, int flags) {
        int i;
        int i2 = 1;
        dest.writeParcelable(center(), flags);
        dest.writeInt(zoom());
        dest.writeParcelable(marker(), flags);
        dest.writeParcelable(circle(), flags);
        if (useDlsMapType()) {
            i = 1;
        } else {
            i = 0;
        }
        dest.writeInt(i);
        if (!isUserInChina()) {
            i2 = 0;
        }
        dest.writeInt(i2);
    }

    public int describeContents() {
        return 0;
    }
}
