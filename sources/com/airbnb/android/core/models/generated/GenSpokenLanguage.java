package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenSpokenLanguage implements Parcelable {
    @JsonProperty("id")
    protected int mId;
    @JsonProperty("language")
    protected String mLanguage;
    @JsonProperty("spoken")
    protected boolean mSpoken;

    protected GenSpokenLanguage(String language, boolean spoken, int id) {
        this();
        this.mLanguage = language;
        this.mSpoken = spoken;
        this.mId = id;
    }

    protected GenSpokenLanguage() {
    }

    public String getLanguage() {
        return this.mLanguage;
    }

    @JsonProperty("language")
    public void setLanguage(String value) {
        this.mLanguage = value;
    }

    public boolean isSpoken() {
        return this.mSpoken;
    }

    @JsonProperty("spoken")
    public void setSpoken(boolean value) {
        this.mSpoken = value;
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
        parcel.writeString(this.mLanguage);
        parcel.writeBooleanArray(new boolean[]{this.mSpoken});
        parcel.writeInt(this.mId);
    }

    public void readFromParcel(Parcel source) {
        this.mLanguage = source.readString();
        this.mSpoken = source.createBooleanArray()[0];
        this.mId = source.readInt();
    }
}
