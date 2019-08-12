package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenTimeType implements Parcelable {
    @JsonProperty("display_text")
    protected String mDisplayText;
    @JsonProperty("value")
    protected String mValue;

    protected GenTimeType(String value, String displayText) {
        this();
        this.mValue = value;
        this.mDisplayText = displayText;
    }

    protected GenTimeType() {
    }

    public String getValue() {
        return this.mValue;
    }

    @JsonProperty("value")
    public void setValue(String value) {
        this.mValue = value;
    }

    public String getDisplayText() {
        return this.mDisplayText;
    }

    @JsonProperty("display_text")
    public void setDisplayText(String value) {
        this.mDisplayText = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mValue);
        parcel.writeString(this.mDisplayText);
    }

    public void readFromParcel(Parcel source) {
        this.mValue = source.readString();
        this.mDisplayText = source.readString();
    }
}
