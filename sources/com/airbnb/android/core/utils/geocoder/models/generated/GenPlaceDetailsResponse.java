package com.airbnb.android.core.utils.geocoder.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.utils.geocoder.models.GeocoderResult;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenPlaceDetailsResponse implements Parcelable {
    @JsonProperty("result")
    protected GeocoderResult mResult;

    protected GenPlaceDetailsResponse(GeocoderResult result) {
        this();
        this.mResult = result;
    }

    protected GenPlaceDetailsResponse() {
    }

    public GeocoderResult getResult() {
        return this.mResult;
    }

    @JsonProperty("result")
    public void setResult(GeocoderResult value) {
        this.mResult = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mResult, 0);
    }

    public void readFromParcel(Parcel source) {
        this.mResult = (GeocoderResult) source.readParcelable(GeocoderResult.class.getClassLoader());
    }
}
