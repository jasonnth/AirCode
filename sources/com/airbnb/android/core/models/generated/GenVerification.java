package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenVerification implements Parcelable {
    @JsonProperty("id")
    protected String mId;
    @JsonProperty("status")
    protected String mStatus;

    protected GenVerification(String id, String status) {
        this();
        this.mId = id;
        this.mStatus = status;
    }

    protected GenVerification() {
    }

    public String getId() {
        return this.mId;
    }

    @JsonProperty("id")
    public void setId(String value) {
        this.mId = value;
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
        parcel.writeString(this.mId);
        parcel.writeString(this.mStatus);
    }

    public void readFromParcel(Parcel source) {
        this.mId = source.readString();
        this.mStatus = source.readString();
    }
}
