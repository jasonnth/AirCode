package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenInlineSearchFeedMessageItem implements Parcelable {
    @JsonProperty("subtext")
    protected String mSubtext;
    @JsonProperty("text")
    protected String mText;

    protected GenInlineSearchFeedMessageItem(String text, String subtext) {
        this();
        this.mText = text;
        this.mSubtext = subtext;
    }

    protected GenInlineSearchFeedMessageItem() {
    }

    public String getText() {
        return this.mText;
    }

    @JsonProperty("text")
    public void setText(String value) {
        this.mText = value;
    }

    public String getSubtext() {
        return this.mSubtext;
    }

    @JsonProperty("subtext")
    public void setSubtext(String value) {
        this.mSubtext = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mText);
        parcel.writeString(this.mSubtext);
    }

    public void readFromParcel(Parcel source) {
        this.mText = source.readString();
        this.mSubtext = source.readString();
    }
}
