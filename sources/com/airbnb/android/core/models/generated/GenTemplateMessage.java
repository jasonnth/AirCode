package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenTemplateMessage implements Parcelable {
    @JsonProperty("id")
    protected long mId;
    @JsonProperty("message")
    protected String mMessage;
    @JsonProperty("title")
    protected String mTitle;

    protected GenTemplateMessage(String title, String message, long id) {
        this();
        this.mTitle = title;
        this.mMessage = message;
        this.mId = id;
    }

    protected GenTemplateMessage() {
    }

    public String getTitle() {
        return this.mTitle;
    }

    @JsonProperty("title")
    public void setTitle(String value) {
        this.mTitle = value;
    }

    public String getMessage() {
        return this.mMessage;
    }

    @JsonProperty("message")
    public void setMessage(String value) {
        this.mMessage = value;
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
        parcel.writeString(this.mTitle);
        parcel.writeString(this.mMessage);
        parcel.writeLong(this.mId);
    }

    public void readFromParcel(Parcel source) {
        this.mTitle = source.readString();
        this.mMessage = source.readString();
        this.mId = source.readLong();
    }
}
