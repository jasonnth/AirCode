package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenAttributedTextRange implements Parcelable {
    @JsonProperty("length")
    protected int mLength;
    @JsonProperty("start")
    protected int mStart;

    protected GenAttributedTextRange(int start, int length) {
        this();
        this.mStart = start;
        this.mLength = length;
    }

    protected GenAttributedTextRange() {
    }

    public int getStart() {
        return this.mStart;
    }

    @JsonProperty("start")
    public void setStart(int value) {
        this.mStart = value;
    }

    public int getLength() {
        return this.mLength;
    }

    @JsonProperty("length")
    public void setLength(int value) {
        this.mLength = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(this.mStart);
        parcel.writeInt(this.mLength);
    }

    public void readFromParcel(Parcel source) {
        this.mStart = source.readInt();
        this.mLength = source.readInt();
    }
}
