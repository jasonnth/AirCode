package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenSearchFacetWithIntKey implements Parcelable {
    @JsonProperty("count")
    protected int mCount;
    @JsonProperty("key")
    protected int mKey;

    protected GenSearchFacetWithIntKey(int count, int key) {
        this();
        this.mCount = count;
        this.mKey = key;
    }

    protected GenSearchFacetWithIntKey() {
    }

    public int getCount() {
        return this.mCount;
    }

    @JsonProperty("count")
    public void setCount(int value) {
        this.mCount = value;
    }

    public int getKey() {
        return this.mKey;
    }

    @JsonProperty("key")
    public void setKey(int value) {
        this.mKey = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(this.mCount);
        parcel.writeInt(this.mKey);
    }

    public void readFromParcel(Parcel source) {
        this.mCount = source.readInt();
        this.mKey = source.readInt();
    }
}
