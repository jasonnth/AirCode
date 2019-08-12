package com.airbnb.android.core.utils.geocoder.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.utils.geocoder.models.GeocoderResult;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenGeocoderResponse implements Parcelable {
    @JsonProperty("results")
    protected List<GeocoderResult> mResults;
    @JsonProperty("status")
    protected String mStatus;

    protected GenGeocoderResponse(List<GeocoderResult> results, String status) {
        this();
        this.mResults = results;
        this.mStatus = status;
    }

    protected GenGeocoderResponse() {
    }

    public List<GeocoderResult> getResults() {
        return this.mResults;
    }

    @JsonProperty("results")
    public void setResults(List<GeocoderResult> value) {
        this.mResults = value;
    }

    public String getStatus() {
        return this.mStatus;
    }

    @JsonProperty("status")
    public void setStatus(String value) {
        this.mStatus = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeTypedList(this.mResults);
        parcel.writeString(this.mStatus);
    }

    public void readFromParcel(Parcel source) {
        this.mResults = source.createTypedArrayList(GeocoderResult.CREATOR);
        this.mStatus = source.readString();
    }
}
