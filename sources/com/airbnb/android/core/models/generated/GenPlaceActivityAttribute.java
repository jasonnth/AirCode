package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenPlaceActivityAttribute implements Parcelable {
    @JsonProperty("airmoji")
    protected String mAirmoji;
    @JsonProperty("text")
    protected String mText;
    @JsonProperty("title")
    protected String mTitle;
    @JsonProperty("type")
    protected String mType;

    protected GenPlaceActivityAttribute(String type, String text, String title, String airmoji) {
        this();
        this.mType = type;
        this.mText = text;
        this.mTitle = title;
        this.mAirmoji = airmoji;
    }

    protected GenPlaceActivityAttribute() {
    }

    public String getType() {
        return this.mType;
    }

    @JsonProperty("type")
    public void setType(String value) {
        this.mType = value;
    }

    public String getText() {
        return this.mText;
    }

    @JsonProperty("text")
    public void setText(String value) {
        this.mText = value;
    }

    public String getTitle() {
        return this.mTitle;
    }

    @JsonProperty("title")
    public void setTitle(String value) {
        this.mTitle = value;
    }

    public String getAirmoji() {
        return this.mAirmoji;
    }

    @JsonProperty("airmoji")
    public void setAirmoji(String value) {
        this.mAirmoji = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mType);
        parcel.writeString(this.mText);
        parcel.writeString(this.mTitle);
        parcel.writeString(this.mAirmoji);
    }

    public void readFromParcel(Parcel source) {
        this.mType = source.readString();
        this.mText = source.readString();
        this.mTitle = source.readString();
        this.mAirmoji = source.readString();
    }
}
