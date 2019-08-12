package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenPhoneNumberConfirmation implements Parcelable {
    @JsonProperty("international_number")
    protected String mInternationalNumber;
    @JsonProperty("national_number")
    protected String mNationalNumber;
    @JsonProperty("status")
    protected String mStatus;
    @JsonProperty("success")
    protected boolean mSuccess;

    protected GenPhoneNumberConfirmation(String internationalNumber, String nationalNumber, String status, boolean success) {
        this();
        this.mInternationalNumber = internationalNumber;
        this.mNationalNumber = nationalNumber;
        this.mStatus = status;
        this.mSuccess = success;
    }

    protected GenPhoneNumberConfirmation() {
    }

    public String getInternationalNumber() {
        return this.mInternationalNumber;
    }

    @JsonProperty("international_number")
    public void setInternationalNumber(String value) {
        this.mInternationalNumber = value;
    }

    public String getNationalNumber() {
        return this.mNationalNumber;
    }

    @JsonProperty("national_number")
    public void setNationalNumber(String value) {
        this.mNationalNumber = value;
    }

    public String getStatus() {
        return this.mStatus;
    }

    @JsonProperty("status")
    public void setStatus(String value) {
        this.mStatus = value;
    }

    public boolean isSuccess() {
        return this.mSuccess;
    }

    @JsonProperty("success")
    public void setSuccess(boolean value) {
        this.mSuccess = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mInternationalNumber);
        parcel.writeString(this.mNationalNumber);
        parcel.writeString(this.mStatus);
        parcel.writeBooleanArray(new boolean[]{this.mSuccess});
    }

    public void readFromParcel(Parcel source) {
        this.mInternationalNumber = source.readString();
        this.mNationalNumber = source.readString();
        this.mStatus = source.readString();
        this.mSuccess = source.createBooleanArray()[0];
    }
}
