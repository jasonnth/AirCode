package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenAirbnbCredit implements Parcelable {
    @JsonProperty("amount_native")
    protected int mAmountNative;
    @JsonProperty("description")
    protected String mDescription;

    protected GenAirbnbCredit(String description, int amountNative) {
        this();
        this.mDescription = description;
        this.mAmountNative = amountNative;
    }

    protected GenAirbnbCredit() {
    }

    public String getDescription() {
        return this.mDescription;
    }

    @JsonProperty("description")
    public void setDescription(String value) {
        this.mDescription = value;
    }

    public int getAmountNative() {
        return this.mAmountNative;
    }

    @JsonProperty("amount_native")
    public void setAmountNative(int value) {
        this.mAmountNative = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mDescription);
        parcel.writeInt(this.mAmountNative);
    }

    public void readFromParcel(Parcel source) {
        this.mDescription = source.readString();
        this.mAmountNative = source.readInt();
    }
}
