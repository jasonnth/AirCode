package com.airbnb.android.core.utils.geocoder.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.utils.geocoder.models.generated.GenGeocoderViewport;

public class GeocoderViewport extends GenGeocoderViewport {
    public static final Creator<GeocoderViewport> CREATOR = new Creator<GeocoderViewport>() {
        public GeocoderViewport[] newArray(int size) {
            return new GeocoderViewport[size];
        }

        public GeocoderViewport createFromParcel(Parcel source) {
            GeocoderViewport object = new GeocoderViewport();
            object.readFromParcel(source);
            return object;
        }
    };
}
