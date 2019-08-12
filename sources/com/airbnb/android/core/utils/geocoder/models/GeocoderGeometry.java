package com.airbnb.android.core.utils.geocoder.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.utils.geocoder.models.generated.GenGeocoderGeometry;

public class GeocoderGeometry extends GenGeocoderGeometry {
    public static final Creator<GeocoderGeometry> CREATOR = new Creator<GeocoderGeometry>() {
        public GeocoderGeometry[] newArray(int size) {
            return new GeocoderGeometry[size];
        }

        public GeocoderGeometry createFromParcel(Parcel source) {
            GeocoderGeometry object = new GeocoderGeometry();
            object.readFromParcel(source);
            return object;
        }
    };
}
