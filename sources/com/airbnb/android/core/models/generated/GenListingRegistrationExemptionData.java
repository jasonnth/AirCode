package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenListingRegistrationExemptionData implements Parcelable {
    @JsonProperty("expiration_date")
    protected String mExpirationDate;
    @JsonProperty("permit_number")
    protected String mPermitNumber;
    @JsonProperty("zipcode")
    protected String mZipcode;

    protected GenListingRegistrationExemptionData(String expirationDate, String permitNumber, String zipcode) {
        this();
        this.mExpirationDate = expirationDate;
        this.mPermitNumber = permitNumber;
        this.mZipcode = zipcode;
    }

    protected GenListingRegistrationExemptionData() {
    }

    public String getExpirationDate() {
        return this.mExpirationDate;
    }

    @JsonProperty("expiration_date")
    public void setExpirationDate(String value) {
        this.mExpirationDate = value;
    }

    public String getPermitNumber() {
        return this.mPermitNumber;
    }

    @JsonProperty("permit_number")
    public void setPermitNumber(String value) {
        this.mPermitNumber = value;
    }

    public String getZipcode() {
        return this.mZipcode;
    }

    @JsonProperty("zipcode")
    public void setZipcode(String value) {
        this.mZipcode = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mExpirationDate);
        parcel.writeString(this.mPermitNumber);
        parcel.writeString(this.mZipcode);
    }

    public void readFromParcel(Parcel source) {
        this.mExpirationDate = source.readString();
        this.mPermitNumber = source.readString();
        this.mZipcode = source.readString();
    }
}
