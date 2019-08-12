package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenAffiliateClick implements Parcelable {
    @JsonProperty("status_code")
    protected int mStatusCode;

    protected GenAffiliateClick(int statusCode) {
        this();
        this.mStatusCode = statusCode;
    }

    protected GenAffiliateClick() {
    }

    public int getStatusCode() {
        return this.mStatusCode;
    }

    @JsonProperty("status_code")
    public void setStatusCode(int value) {
        this.mStatusCode = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(this.mStatusCode);
    }

    public void readFromParcel(Parcel source) {
        this.mStatusCode = source.readInt();
    }
}
