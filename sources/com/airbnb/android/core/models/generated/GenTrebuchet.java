package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenTrebuchet implements Parcelable {
    @JsonProperty("id")
    protected String mId;
    @JsonProperty("launch")
    protected boolean mLaunch;

    protected GenTrebuchet(String id, boolean launch) {
        this();
        this.mId = id;
        this.mLaunch = launch;
    }

    protected GenTrebuchet() {
    }

    public String getId() {
        return this.mId;
    }

    @JsonProperty("id")
    public void setId(String value) {
        this.mId = value;
    }

    public boolean isLaunch() {
        return this.mLaunch;
    }

    @JsonProperty("launch")
    public void setLaunch(boolean value) {
        this.mLaunch = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mId);
        parcel.writeBooleanArray(new boolean[]{this.mLaunch});
    }

    public void readFromParcel(Parcel source) {
        this.mId = source.readString();
        this.mLaunch = source.createBooleanArray()[0];
    }
}
