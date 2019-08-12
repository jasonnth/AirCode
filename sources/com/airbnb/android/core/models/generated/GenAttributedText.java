package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenAttributedText implements Parcelable {
    @JsonProperty("text")
    protected String mText;
    @JsonProperty("type")
    protected String mType;

    protected GenAttributedText(String text, String type) {
        this();
        this.mText = text;
        this.mType = type;
    }

    protected GenAttributedText() {
    }

    public String getText() {
        return this.mText;
    }

    @JsonProperty("text")
    public void setText(String value) {
        this.mText = value;
    }

    public String getType() {
        return this.mType;
    }

    @JsonProperty("type")
    public void setType(String value) {
        this.mType = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mText);
        parcel.writeString(this.mType);
    }

    public void readFromParcel(Parcel source) {
        this.mText = source.readString();
        this.mType = source.readString();
    }
}
