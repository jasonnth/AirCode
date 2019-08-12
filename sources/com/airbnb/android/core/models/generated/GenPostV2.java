package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenPostV2 implements Parcelable {
    @JsonProperty("id")
    protected long mId;
    @JsonProperty("message")
    protected String mMessage;
    @JsonProperty("user_id")
    protected long mUserId;

    protected GenPostV2(String message, long id, long userId) {
        this();
        this.mMessage = message;
        this.mId = id;
        this.mUserId = userId;
    }

    protected GenPostV2() {
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

    public long getUserId() {
        return this.mUserId;
    }

    @JsonProperty("user_id")
    public void setUserId(long value) {
        this.mUserId = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mMessage);
        parcel.writeLong(this.mId);
        parcel.writeLong(this.mUserId);
    }

    public void readFromParcel(Parcel source) {
        this.mMessage = source.readString();
        this.mId = source.readLong();
        this.mUserId = source.readLong();
    }
}
