package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenAccountVerification implements Parcelable {
    @JsonProperty("status")
    protected String mStatus;
    @JsonProperty("type")
    protected String mType;

    protected GenAccountVerification(String status, String type) {
        this();
        this.mStatus = status;
        this.mType = type;
    }

    protected GenAccountVerification() {
    }

    public String getStatus() {
        return this.mStatus;
    }

    @JsonProperty("status")
    public void setStatus(String value) {
        this.mStatus = value;
    }

    public String getType() {
        return this.mType;
    }

    @JsonProperty("type")
    public void setType(String value) {
        this.mType = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mStatus);
        parcel.writeString(this.mType);
    }

    public void readFromParcel(Parcel source) {
        this.mStatus = source.readString();
        this.mType = source.readString();
    }
}
