package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenSelectLayoutType implements Parcelable {
    @JsonProperty("id")
    protected int mId;
    @JsonProperty("name")
    protected String mName;

    protected GenSelectLayoutType(String name, int id) {
        this();
        this.mName = name;
        this.mId = id;
    }

    protected GenSelectLayoutType() {
    }

    public String getName() {
        return this.mName;
    }

    @JsonProperty("name")
    public void setName(String value) {
        this.mName = value;
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
        parcel.writeString(this.mName);
        parcel.writeInt(this.mId);
    }

    public void readFromParcel(Parcel source) {
        this.mName = source.readString();
        this.mId = source.readInt();
    }
}
