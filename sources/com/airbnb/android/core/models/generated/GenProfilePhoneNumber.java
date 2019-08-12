package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenProfilePhoneNumber implements Parcelable {
    @JsonProperty("country")
    protected String mCountry;
    @JsonProperty("id")
    protected long mId;
    @JsonProperty("number")
    protected String mNumber;
    @JsonProperty("number_formatted")
    protected String mNumberFormatted;
    @JsonProperty("verified")
    protected boolean mVerified;

    protected GenProfilePhoneNumber(String country, String number, String numberFormatted, boolean verified, long id) {
        this();
        this.mCountry = country;
        this.mNumber = number;
        this.mNumberFormatted = numberFormatted;
        this.mVerified = verified;
        this.mId = id;
    }

    protected GenProfilePhoneNumber() {
    }

    public String getCountry() {
        return this.mCountry;
    }

    @JsonProperty("country")
    public void setCountry(String value) {
        this.mCountry = value;
    }

    public String getNumber() {
        return this.mNumber;
    }

    @JsonProperty("number")
    public void setNumber(String value) {
        this.mNumber = value;
    }

    public String getNumberFormatted() {
        return this.mNumberFormatted;
    }

    @JsonProperty("number_formatted")
    public void setNumberFormatted(String value) {
        this.mNumberFormatted = value;
    }

    public boolean isVerified() {
        return this.mVerified;
    }

    @JsonProperty("verified")
    public void setVerified(boolean value) {
        this.mVerified = value;
    }

    public long getId() {
        return this.mId;
    }

    @JsonProperty("id")
    public void setId(long value) {
        this.mId = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mCountry);
        parcel.writeString(this.mNumber);
        parcel.writeString(this.mNumberFormatted);
        parcel.writeBooleanArray(new boolean[]{this.mVerified});
        parcel.writeLong(this.mId);
    }

    public void readFromParcel(Parcel source) {
        this.mCountry = source.readString();
        this.mNumber = source.readString();
        this.mNumberFormatted = source.readString();
        this.mVerified = source.createBooleanArray()[0];
        this.mId = source.readLong();
    }
}
