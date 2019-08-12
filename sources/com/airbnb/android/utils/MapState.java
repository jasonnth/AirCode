package com.airbnb.android.utils;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.gms.maps.model.LatLng;

public class MapState implements Parcelable {
    public static final Creator<MapState> CREATOR = new Creator<MapState>() {
        public MapState createFromParcel(Parcel source) {
            return new MapState(source);
        }

        public MapState[] newArray(int size) {
            return new MapState[size];
        }
    };
    public final LatLng latLng;
    public final int zoom;

    public MapState(LatLng latLng2, int zoom2) {
        this.latLng = latLng2;
        this.zoom = zoom2;
    }

    public MapState(Parcel source) {
        this((LatLng) source.readParcelable(LatLng.class.getClassLoader()), source.readInt());
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MapState mapState = (MapState) o;
        if (this.zoom == mapState.zoom) {
            return this.latLng.equals(mapState.latLng);
        }
        return false;
    }

    public int hashCode() {
        return (this.latLng.hashCode() * 31) + this.zoom;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.latLng, 0);
        dest.writeInt(this.zoom);
    }
}
