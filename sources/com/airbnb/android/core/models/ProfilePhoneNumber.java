package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenProfilePhoneNumber;

public class ProfilePhoneNumber extends GenProfilePhoneNumber {
    public static final Creator<ProfilePhoneNumber> CREATOR = new Creator<ProfilePhoneNumber>() {
        public ProfilePhoneNumber[] newArray(int size) {
            return new ProfilePhoneNumber[size];
        }

        public ProfilePhoneNumber createFromParcel(Parcel source) {
            ProfilePhoneNumber object = new ProfilePhoneNumber();
            object.readFromParcel(source);
            return object;
        }
    };

    public ProfilePhoneNumber(String country, String number, String numberFormatted, boolean verified, long id) {
        super(country, number, numberFormatted, verified, id);
    }

    public ProfilePhoneNumber() {
    }

    public int hashCode() {
        return ((int) (this.mId ^ (this.mId >>> 32))) + 31;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        if (this.mId != ((ProfilePhoneNumber) obj).mId) {
            return false;
        }
        return true;
    }
}
