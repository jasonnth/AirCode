package com.airbnb.android.core.utils.geocoder.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.utils.geocoder.models.generated.GenGeocoderLatLng;
import com.google.android.gms.maps.model.LatLng;

public class GeocoderLatLng extends GenGeocoderLatLng {
    public static final Creator<GeocoderLatLng> CREATOR = new Creator<GeocoderLatLng>() {
        public GeocoderLatLng[] newArray(int size) {
            return new GeocoderLatLng[size];
        }

        public GeocoderLatLng createFromParcel(Parcel source) {
            GeocoderLatLng object = new GeocoderLatLng();
            object.readFromParcel(source);
            return object;
        }
    };

    public LatLng toLatLng() {
        return new LatLng(this.mLat, this.mLng);
    }
}
