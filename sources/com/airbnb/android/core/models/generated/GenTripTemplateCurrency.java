package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenTripTemplateCurrency implements Parcelable {
    @JsonProperty("currency")
    protected String mCurrency;

    protected GenTripTemplateCurrency(String currency) {
        this();
        this.mCurrency = currency;
    }

    protected GenTripTemplateCurrency() {
    }

    public String getCurrency() {
        return this.mCurrency;
    }

    @JsonProperty("currency")
    public void setCurrency(String value) {
        this.mCurrency = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mCurrency);
    }

    public void readFromParcel(Parcel source) {
        this.mCurrency = source.readString();
    }
}
