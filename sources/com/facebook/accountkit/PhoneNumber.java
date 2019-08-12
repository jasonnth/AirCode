package com.facebook.accountkit;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.facebook.accountkit.internal.Utility;

public final class PhoneNumber implements Parcelable {
    public static final Creator<PhoneNumber> CREATOR = new Creator<PhoneNumber>() {
        public PhoneNumber createFromParcel(Parcel source) {
            return new PhoneNumber(source);
        }

        public PhoneNumber[] newArray(int size) {
            return new PhoneNumber[size];
        }
    };
    private final String countryCode;
    private final String countryCodeIso;
    private final String phoneNumber;

    public PhoneNumber(String countryCode2, String phoneNumber2, String countryCodeIso2) {
        this.phoneNumber = Utility.cleanPhoneNumberString(phoneNumber2);
        this.countryCode = Utility.cleanPhoneNumberString(countryCode2);
        this.countryCodeIso = countryCodeIso2;
    }

    @Deprecated
    public PhoneNumber(String countryCode2, String phoneNumber2) {
        this(countryCode2, phoneNumber2, null);
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public String getCountryCodeIso() {
        return this.countryCodeIso;
    }

    public String getRawPhoneNumber() {
        return this.countryCode + this.phoneNumber;
    }

    public String toString() {
        return "+" + this.countryCode + this.phoneNumber;
    }

    public int hashCode() {
        return toString().hashCode();
    }

    public boolean equals(Object other) {
        return (other instanceof PhoneNumber) && hashCode() == other.hashCode();
    }

    private PhoneNumber(Parcel parcel) {
        this.countryCode = parcel.readString();
        this.phoneNumber = parcel.readString();
        this.countryCodeIso = parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.countryCode);
        dest.writeString(this.phoneNumber);
        dest.writeString(this.countryCodeIso);
    }
}
