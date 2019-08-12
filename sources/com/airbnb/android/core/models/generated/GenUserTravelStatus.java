package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.p008mt.models.UserLocationType;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenUserTravelStatus implements Parcelable {
    @JsonProperty("market")
    protected String mMarket;
    @JsonProperty("type")
    protected UserLocationType mType;

    protected GenUserTravelStatus(String market, UserLocationType type) {
        this();
        this.mMarket = market;
        this.mType = type;
    }

    protected GenUserTravelStatus() {
    }

    public String getMarket() {
        return this.mMarket;
    }

    @JsonProperty("market")
    public void setMarket(String value) {
        this.mMarket = value;
    }

    public UserLocationType getType() {
        return this.mType;
    }

    @JsonProperty("type")
    public void setType(UserLocationType value) {
        this.mType = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mMarket);
        parcel.writeParcelable(this.mType, 0);
    }

    public void readFromParcel(Parcel source) {
        this.mMarket = source.readString();
        this.mType = (UserLocationType) source.readParcelable(UserLocationType.class.getClassLoader());
    }
}
