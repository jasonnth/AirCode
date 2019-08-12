package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenHelpThreadDisplayElement implements Parcelable {
    @JsonProperty("dialog_content")
    protected String mDialogContent;
    @JsonProperty("display_type")
    protected String mDisplayType;
    @JsonProperty("id")
    protected long mId;
    @JsonProperty("link_label")
    protected String mLinkLabel;
    @JsonProperty("map_image_url")
    protected String mMapImageUrl;
    @JsonProperty("map_url")
    protected String mMapUrl;
    @JsonProperty("merge")
    protected Boolean mMerge;
    @JsonProperty("message")
    protected String mMessage;

    protected GenHelpThreadDisplayElement(Boolean merge, String displayType, String message, String linkLabel, String dialogContent, String mapUrl, String mapImageUrl, long id) {
        this();
        this.mMerge = merge;
        this.mDisplayType = displayType;
        this.mMessage = message;
        this.mLinkLabel = linkLabel;
        this.mDialogContent = dialogContent;
        this.mMapUrl = mapUrl;
        this.mMapImageUrl = mapImageUrl;
        this.mId = id;
    }

    protected GenHelpThreadDisplayElement() {
    }

    public Boolean isMerge() {
        return this.mMerge;
    }

    @JsonProperty("merge")
    public void setMerge(Boolean value) {
        this.mMerge = value;
    }

    public String getDisplayType() {
        return this.mDisplayType;
    }

    @JsonProperty("display_type")
    public void setDisplayType(String value) {
        this.mDisplayType = value;
    }

    public String getMessage() {
        return this.mMessage;
    }

    @JsonProperty("message")
    public void setMessage(String value) {
        this.mMessage = value;
    }

    public String getLinkLabel() {
        return this.mLinkLabel;
    }

    @JsonProperty("link_label")
    public void setLinkLabel(String value) {
        this.mLinkLabel = value;
    }

    public String getDialogContent() {
        return this.mDialogContent;
    }

    @JsonProperty("dialog_content")
    public void setDialogContent(String value) {
        this.mDialogContent = value;
    }

    public String getMapUrl() {
        return this.mMapUrl;
    }

    @JsonProperty("map_url")
    public void setMapUrl(String value) {
        this.mMapUrl = value;
    }

    public String getMapImageUrl() {
        return this.mMapImageUrl;
    }

    @JsonProperty("map_image_url")
    public void setMapImageUrl(String value) {
        this.mMapImageUrl = value;
    }

    public long getId() {
        return this.mId;
    }

    @JsonProperty("id")
    public void setId(long value) {
        this.mId = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeValue(this.mMerge);
        parcel.writeString(this.mDisplayType);
        parcel.writeString(this.mMessage);
        parcel.writeString(this.mLinkLabel);
        parcel.writeString(this.mDialogContent);
        parcel.writeString(this.mMapUrl);
        parcel.writeString(this.mMapImageUrl);
        parcel.writeLong(this.mId);
    }

    public void readFromParcel(Parcel source) {
        this.mMerge = (Boolean) source.readValue(Boolean.class.getClassLoader());
        this.mDisplayType = source.readString();
        this.mMessage = source.readString();
        this.mLinkLabel = source.readString();
        this.mDialogContent = source.readString();
        this.mMapUrl = source.readString();
        this.mMapImageUrl = source.readString();
        this.mId = source.readLong();
    }
}
