package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenAlipayDetails implements Parcelable {
    @JsonProperty("deeplink_url")
    protected String mDeeplinkUrl;

    protected GenAlipayDetails(String deeplinkUrl) {
        this();
        this.mDeeplinkUrl = deeplinkUrl;
    }

    protected GenAlipayDetails() {
    }

    public String getDeeplinkUrl() {
        return this.mDeeplinkUrl;
    }

    @JsonProperty("deeplink_url")
    public void setDeeplinkUrl(String value) {
        this.mDeeplinkUrl = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mDeeplinkUrl);
    }

    public void readFromParcel(Parcel source) {
        this.mDeeplinkUrl = source.readString();
    }
}
