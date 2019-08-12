package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;

public abstract class GenIconWithTitles implements Parcelable {
    @JsonProperty("icon")
    protected String mIcon;
    @JsonProperty("reason_id")
    protected String mReasonId;
    @JsonProperty("subtitles")
    protected ArrayList<String> mSubtitles;
    @JsonProperty("title")
    protected String mTitle;
    @JsonProperty("type")
    protected String mType;
    @JsonProperty("value")
    protected String mValue;

    protected GenIconWithTitles(ArrayList<String> subtitles, String icon, String reasonId, String title, String type, String value) {
        this();
        this.mSubtitles = subtitles;
        this.mIcon = icon;
        this.mReasonId = reasonId;
        this.mTitle = title;
        this.mType = type;
        this.mValue = value;
    }

    protected GenIconWithTitles() {
    }

    public ArrayList<String> getSubtitles() {
        return this.mSubtitles;
    }

    @JsonProperty("subtitles")
    public void setSubtitles(ArrayList<String> value) {
        this.mSubtitles = value;
    }

    public String getIcon() {
        return this.mIcon;
    }

    @JsonProperty("icon")
    public void setIcon(String value) {
        this.mIcon = value;
    }

    public String getReasonId() {
        return this.mReasonId;
    }

    @JsonProperty("reason_id")
    public void setReasonId(String value) {
        this.mReasonId = value;
    }

    public String getTitle() {
        return this.mTitle;
    }

    @JsonProperty("title")
    public void setTitle(String value) {
        this.mTitle = value;
    }

    public String getType() {
        return this.mType;
    }

    @JsonProperty("type")
    public void setType(String value) {
        this.mType = value;
    }

    public String getValue() {
        return this.mValue;
    }

    @JsonProperty("value")
    public void setValue(String value) {
        this.mValue = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeStringList(this.mSubtitles);
        parcel.writeString(this.mIcon);
        parcel.writeString(this.mReasonId);
        parcel.writeString(this.mTitle);
        parcel.writeString(this.mType);
        parcel.writeString(this.mValue);
    }

    public void readFromParcel(Parcel source) {
        this.mSubtitles = source.createStringArrayList();
        this.mIcon = source.readString();
        this.mReasonId = source.readString();
        this.mTitle = source.readString();
        this.mType = source.readString();
        this.mValue = source.readString();
    }
}
