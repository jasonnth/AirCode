package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenHostCancellationDisclaimer implements Parcelable {
    @JsonProperty("body")
    protected String mBody;
    @JsonProperty("title")
    protected String mTitle;

    protected GenHostCancellationDisclaimer(String title, String body) {
        this();
        this.mTitle = title;
        this.mBody = body;
    }

    protected GenHostCancellationDisclaimer() {
    }

    public String getTitle() {
        return this.mTitle;
    }

    @JsonProperty("title")
    public void setTitle(String value) {
        this.mTitle = value;
    }

    public String getBody() {
        return this.mBody;
    }

    @JsonProperty("body")
    public void setBody(String value) {
        this.mBody = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mTitle);
        parcel.writeString(this.mBody);
    }

    public void readFromParcel(Parcel source) {
        this.mTitle = source.readString();
        this.mBody = source.readString();
    }
}
