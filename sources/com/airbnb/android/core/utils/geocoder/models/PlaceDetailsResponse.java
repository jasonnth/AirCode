package com.airbnb.android.core.utils.geocoder.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.utils.geocoder.models.generated.GenPlaceDetailsResponse;

public class PlaceDetailsResponse extends GenPlaceDetailsResponse {
    public static final Creator<PlaceDetailsResponse> CREATOR = new Creator<PlaceDetailsResponse>() {
        public PlaceDetailsResponse[] newArray(int size) {
            return new PlaceDetailsResponse[size];
        }

        public PlaceDetailsResponse createFromParcel(Parcel source) {
            PlaceDetailsResponse object = new PlaceDetailsResponse();
            object.readFromParcel(source);
            return object;
        }
    };

    public GeocoderResult getResult() {
        return super.getResult();
    }
}
