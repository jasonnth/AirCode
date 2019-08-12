package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenRejectionTip implements Parcelable {
    @JsonProperty("key")
    protected String mKey;
    @JsonProperty("subtitle")
    protected String mSubtitle;
    @JsonProperty("title")
    protected String mTitle;

    protected GenRejectionTip(String key, String title, String subtitle) {
        this();
        this.mKey = key;
        this.mTitle = title;
        this.mSubtitle = subtitle;
    }

    protected GenRejectionTip() {
    }

    public String getKey() {
        return this.mKey;
    }

    @JsonProperty("key")
    public void setKey(String value) {
        this.mKey = value;
    }

    public String getTitle() {
        return this.mTitle;
    }

    @JsonProperty("title")
    public void setTitle(String value) {
        this.mTitle = value;
    }

    public String getSubtitle() {
        return this.mSubtitle;
    }

    @JsonProperty("subtitle")
    public void setSubtitle(String value) {
        this.mSubtitle = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mKey);
        parcel.writeString(this.mTitle);
        parcel.writeString(this.mSubtitle);
    }

    public void readFromParcel(Parcel source) {
        this.mKey = source.readString();
        this.mTitle = source.readString();
        this.mSubtitle = source.readString();
    }
}
