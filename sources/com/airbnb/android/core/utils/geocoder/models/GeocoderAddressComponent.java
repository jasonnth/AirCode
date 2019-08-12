package com.airbnb.android.core.utils.geocoder.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.utils.geocoder.models.generated.GenGeocoderAddressComponent;

public class GeocoderAddressComponent extends GenGeocoderAddressComponent {
    public static final Creator<GeocoderAddressComponent> CREATOR = new Creator<GeocoderAddressComponent>() {
        public GeocoderAddressComponent[] newArray(int size) {
            return new GeocoderAddressComponent[size];
        }

        public GeocoderAddressComponent createFromParcel(Parcel source) {
            GeocoderAddressComponent object = new GeocoderAddressComponent();
            object.readFromParcel(source);
            return object;
        }
    };
}
