package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenReservationPaymentRedirect implements Parcelable {
    @JsonProperty("redirect_needed")
    protected boolean mRedirectNeeded;
    @JsonProperty("url")
    protected String mUrl;

    protected GenReservationPaymentRedirect(String url, boolean redirectNeeded) {
        this();
        this.mUrl = url;
        this.mRedirectNeeded = redirectNeeded;
    }

    protected GenReservationPaymentRedirect() {
    }

    public String getUrl() {
        return this.mUrl;
    }

    @JsonProperty("url")
    public void setUrl(String value) {
        this.mUrl = value;
    }

    public boolean isRedirectNeeded() {
        return this.mRedirectNeeded;
    }

    @JsonProperty("redirect_needed")
    public void setRedirectNeeded(boolean value) {
        this.mRedirectNeeded = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mUrl);
        parcel.writeBooleanArray(new boolean[]{this.mRedirectNeeded});
    }

    public void readFromParcel(Parcel source) {
        this.mUrl = source.readString();
        this.mRedirectNeeded = source.createBooleanArray()[0];
    }
}
