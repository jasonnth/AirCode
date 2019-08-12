package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenPlacesMetaData implements Parcelable {
    @JsonProperty("count")
    protected int mCount;

    protected GenPlacesMetaData(int count) {
        this();
        this.mCount = count;
    }

    protected GenPlacesMetaData() {
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
        parcel.writeInt(this.mCount);
    }

    public void readFromParcel(Parcel source) {
        this.mCount = source.readInt();
    }
}
