package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenSearchFacetWithStringKey implements Parcelable {
    @JsonProperty("count")
    protected int mCount;
    @JsonProperty("key")
    protected String mKey;

    protected GenSearchFacetWithStringKey(String key, int count) {
        this();
        this.mKey = key;
        this.mCount = count;
    }

    protected GenSearchFacetWithStringKey() {
    }

    public String getKey() {
        return this.mKey;
    }

    @JsonProperty("key")
    public void setKey(String value) {
        this.mKey = value;
    }

    public int getCount() {
        return this.mCount;
    }

    @JsonProperty("count")
    public void setCount(int value) {
        this.mCount = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mKey);
        parcel.writeInt(this.mCount);
    }

    public void readFromParcel(Parcel source) {
        this.mKey = source.readString();
        this.mCount = source.readInt();
    }
}
