package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenSavedSearchMetadata implements Parcelable {
    @JsonProperty("market")
    protected String mMarket;

    protected GenSavedSearchMetadata(String market) {
        this();
        this.mMarket = market;
    }

    protected GenSavedSearchMetadata() {
    }

    public String getMarket() {
        return this.mMarket;
    }

    @JsonProperty("market")
    public void setMarket(String value) {
        this.mMarket = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mMarket);
    }

    public void readFromParcel(Parcel source) {
        this.mMarket = source.readString();
    }
}
