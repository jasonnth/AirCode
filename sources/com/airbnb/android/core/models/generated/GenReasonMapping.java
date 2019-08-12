package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenReasonMapping implements Parcelable {
    @JsonProperty("id")
    protected int mId;
    @JsonProperty("value")
    protected String mValue;

    protected GenReasonMapping(String value, int id) {
        this();
        this.mValue = value;
        this.mId = id;
    }

    protected GenReasonMapping() {
    }

    public String getValue() {
        return this.mValue;
    }

    @JsonProperty("value")
    public void setValue(String value) {
        this.mValue = value;
    }

    public int getId() {
        return this.mId;
    }

    @JsonProperty("id")
    public void setId(int value) {
        this.mId = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mValue);
        parcel.writeInt(this.mId);
    }

    public void readFromParcel(Parcel source) {
        this.mValue = source.readString();
        this.mId = source.readInt();
    }
}
