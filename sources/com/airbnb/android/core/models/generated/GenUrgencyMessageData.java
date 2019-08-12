package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.UrgencyMessage;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenUrgencyMessageData implements Parcelable {
    @JsonProperty("message")
    protected UrgencyMessage mMessage;
    @JsonProperty("message_type")
    protected String mMessageType;
    @JsonProperty("view_count")
    protected int mViewCount;

    protected GenUrgencyMessageData(String messageType, UrgencyMessage message, int viewCount) {
        this();
        this.mMessageType = messageType;
        this.mMessage = message;
        this.mViewCount = viewCount;
    }

    protected GenUrgencyMessageData() {
    }

    public String getMessageType() {
        return this.mMessageType;
    }

    @JsonProperty("message_type")
    public void setMessageType(String value) {
        this.mMessageType = value;
    }

    public UrgencyMessage getMessage() {
        return this.mMessage;
    }

    @JsonProperty("message")
    public void setMessage(UrgencyMessage value) {
        this.mMessage = value;
    }

    public int getViewCount() {
        return this.mViewCount;
    }

    @JsonProperty("view_count")
    public void setViewCount(int value) {
        this.mViewCount = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mMessageType);
        parcel.writeParcelable(this.mMessage, 0);
        parcel.writeInt(this.mViewCount);
    }

    public void readFromParcel(Parcel source) {
        this.mMessageType = source.readString();
        this.mMessage = (UrgencyMessage) source.readParcelable(UrgencyMessage.class.getClassLoader());
        this.mViewCount = source.readInt();
    }
}
