package com.airbnb.android.core.utils.geocoder.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenAutocompleteMatchedSubstring implements Parcelable {
    @JsonProperty("length")
    protected int mLength;
    @JsonProperty("offset")
    protected int mOffset;

    protected GenAutocompleteMatchedSubstring(int length, int offset) {
        this();
        this.mLength = length;
        this.mOffset = offset;
    }

    protected GenAutocompleteMatchedSubstring() {
    }

    public int getLength() {
        return this.mLength;
    }

    @JsonProperty("length")
    public void setLength(int value) {
        this.mLength = value;
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
        parcel.writeInt(this.mLength);
        parcel.writeInt(this.mOffset);
    }

    public void readFromParcel(Parcel source) {
        this.mLength = source.readInt();
        this.mOffset = source.readInt();
    }
}
