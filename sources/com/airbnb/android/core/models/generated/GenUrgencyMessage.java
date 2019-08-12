package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenUrgencyMessage implements Parcelable {
    @JsonProperty("body")
    protected String mBody;
    @JsonProperty("contextualMessage")
    protected String mContextualMessage;
    @JsonProperty("headline")
    protected String mHeadline;

    protected GenUrgencyMessage(String headline, String body, String contextualMessage) {
        this();
        this.mHeadline = headline;
        this.mBody = body;
        this.mContextualMessage = contextualMessage;
    }

    protected GenUrgencyMessage() {
    }

    public String getHeadline() {
        return this.mHeadline;
    }

    @JsonProperty("headline")
    public void setHeadline(String value) {
        this.mHeadline = value;
    }

    public String getBody() {
        return this.mBody;
    }

    @JsonProperty("body")
    public void setBody(String value) {
        this.mBody = value;
    }

    public String getContextualMessage() {
        return this.mContextualMessage;
    }

    @JsonProperty("contextualMessage")
    public void setContextualMessage(String value) {
        this.mContextualMessage = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mHeadline);
        parcel.writeString(this.mBody);
        parcel.writeString(this.mContextualMessage);
    }

    public void readFromParcel(Parcel source) {
        this.mHeadline = source.readString();
        this.mBody = source.readString();
        this.mContextualMessage = source.readString();
    }
}
