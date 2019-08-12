package com.airbnb.android.core.utils.geocoder.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenAutocompleteTerm implements Parcelable {
    @JsonProperty("offset")
    protected int mOffset;
    @JsonProperty("value")
    protected String mValue;

    protected GenAutocompleteTerm(String value, int offset) {
        this();
        this.mValue = value;
        this.mOffset = offset;
    }

    protected GenAutocompleteTerm() {
    }

    public String getValue() {
        return this.mValue;
    }

    @JsonProperty("value")
    public void setValue(String value) {
        this.mValue = value;
    }

    public int getOffset() {
        return this.mOffset;
    }

    @JsonProperty("offset")
    public void setOffset(int value) {
        this.mOffset = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mValue);
        parcel.writeInt(this.mOffset);
    }

    public void readFromParcel(Parcel source) {
        this.mValue = source.readString();
        this.mOffset = source.readInt();
    }
}
