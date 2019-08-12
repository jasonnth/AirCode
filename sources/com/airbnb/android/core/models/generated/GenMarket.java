package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenMarket implements Parcelable {
    @JsonProperty("id")
    protected long mId;
    @JsonProperty("name")
    protected String mName;

    protected GenMarket(String name, long id) {
        this();
        this.mName = name;
        this.mId = id;
    }

    protected GenMarket() {
    }

    public String getName() {
        return this.mName;
    }

    @JsonProperty("name")
    public void setName(String value) {
        this.mName = value;
    }

    public long getId() {
        return this.mId;
    }

    @JsonProperty("id")
    public void setId(long value) {
        this.mId = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mName);
        parcel.writeLong(this.mId);
    }

    public void readFromParcel(Parcel source) {
        this.mName = source.readString();
        this.mId = source.readLong();
    }
}
