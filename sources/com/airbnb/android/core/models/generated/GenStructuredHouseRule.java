package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenStructuredHouseRule implements Parcelable {
    @JsonProperty("long_term_text")
    protected String mLongTermText;
    @JsonProperty("text")
    protected String mText;

    protected GenStructuredHouseRule(String text, String longTermText) {
        this();
        this.mText = text;
        this.mLongTermText = longTermText;
    }

    protected GenStructuredHouseRule() {
    }

    public String getText() {
        return this.mText;
    }

    @JsonProperty("text")
    public void setText(String value) {
        this.mText = value;
    }

    public String getLongTermText() {
        return this.mLongTermText;
    }

    @JsonProperty("long_term_text")
    public void setLongTermText(String value) {
        this.mLongTermText = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mText);
        parcel.writeString(this.mLongTermText);
    }

    public void readFromParcel(Parcel source) {
        this.mText = source.readString();
        this.mLongTermText = source.readString();
    }
}
