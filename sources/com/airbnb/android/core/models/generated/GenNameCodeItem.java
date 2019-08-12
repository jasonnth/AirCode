package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenNameCodeItem implements Parcelable {
    @JsonProperty("code")
    protected String mCode;
    @JsonProperty("name")
    protected String mName;

    protected GenNameCodeItem(String name, String code) {
        this();
        this.mName = name;
        this.mCode = code;
    }

    protected GenNameCodeItem() {
    }

    public String getName() {
        return this.mName;
    }

    public String getCode() {
        return this.mCode;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mName);
        parcel.writeString(this.mCode);
    }

    public void readFromParcel(Parcel source) {
        this.mName = source.readString();
        this.mCode = source.readString();
    }
}
