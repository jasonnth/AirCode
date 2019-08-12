package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenTranslatedMessage implements Parcelable {
    @JsonProperty("id")
    protected long mId;
    @JsonProperty("translated_message")
    protected String mTranslatedMessage;

    protected GenTranslatedMessage(String translatedMessage, long id) {
        this();
        this.mTranslatedMessage = translatedMessage;
        this.mId = id;
    }

    protected GenTranslatedMessage() {
    }

    public String getTranslatedMessage() {
        return this.mTranslatedMessage;
    }

    @JsonProperty("translated_message")
    public void setTranslatedMessage(String value) {
        this.mTranslatedMessage = value;
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
        parcel.writeString(this.mTranslatedMessage);
        parcel.writeLong(this.mId);
    }

    public void readFromParcel(Parcel source) {
        this.mTranslatedMessage = source.readString();
        this.mId = source.readLong();
    }
}
