package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.enums.UrgencyMessageType;
import com.airbnb.android.core.models.UrgencyMessage;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenSearchUrgencyCommitment implements Parcelable {
    @JsonProperty("message")
    protected UrgencyMessage mMessage;
    @JsonProperty("message_type")
    protected UrgencyMessageType mMessageType;

    protected GenSearchUrgencyCommitment(UrgencyMessage message, UrgencyMessageType messageType) {
        this();
        this.mMessage = message;
        this.mMessageType = messageType;
    }

    protected GenSearchUrgencyCommitment() {
    }

    public UrgencyMessage getMessage() {
        return this.mMessage;
    }

    @JsonProperty("message")
    public void setMessage(UrgencyMessage value) {
        this.mMessage = value;
    }

    public UrgencyMessageType getMessageType() {
        return this.mMessageType;
    }

    @JsonProperty("message_type")
    public void setMessageType(UrgencyMessageType value) {
        this.mMessageType = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mMessage, 0);
        parcel.writeSerializable(this.mMessageType);
    }

    public void readFromParcel(Parcel source) {
        this.mMessage = (UrgencyMessage) source.readParcelable(UrgencyMessage.class.getClassLoader());
        this.mMessageType = (UrgencyMessageType) source.readSerializable();
    }
}
