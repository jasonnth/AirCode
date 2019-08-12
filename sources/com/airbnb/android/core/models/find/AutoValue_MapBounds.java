package com.airbnb.android.core.models.find;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.maps.model.LatLng;

final class AutoValue_MapBounds extends C$AutoValue_MapBounds {
    public static final Creator<AutoValue_MapBounds> CREATOR = new Creator<AutoValue_MapBounds>() {
        public AutoValue_MapBounds createFromParcel(Parcel in) {
            return new AutoValue_MapBounds((LatLng) in.readParcelable(LatLng.class.getClassLoader()), (LatLng) in.readParcelable(LatLng.class.getClassLoader()));
        }

        public AutoValue_MapBounds[] newArray(int size) {
            return new AutoValue_MapBounds[size];
        }
    };

    AutoValue_MapBounds(LatLng latLngSW, LatLng latLngNE) {
        super(latLngSW, latLngNE);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(latLngSW(), flags);
        dest.writeParcelable(latLngNE(), flags);
    }

    public int describeContents() {
        return 0;
    }
}
