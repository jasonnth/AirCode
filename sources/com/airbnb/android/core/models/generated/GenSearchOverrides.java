package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenSearchOverrides implements Parcelable {
    @JsonProperty("ib")
    protected boolean mIb;

    protected GenSearchOverrides(boolean ib) {
        this();
        this.mIb = ib;
    }

    protected GenSearchOverrides() {
    }

    public boolean isIb() {
        return this.mIb;
    }

    @JsonProperty("ib")
    public void setIb(boolean value) {
        this.mIb = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeBooleanArray(new boolean[]{this.mIb});
    }

    public void readFromParcel(Parcel source) {
        this.mIb = source.createBooleanArray()[0];
    }
}
