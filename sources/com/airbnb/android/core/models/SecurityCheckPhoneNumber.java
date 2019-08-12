package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenSecurityCheckPhoneNumber;

public class SecurityCheckPhoneNumber extends GenSecurityCheckPhoneNumber {
    public static final Creator<SecurityCheckPhoneNumber> CREATOR = new Creator<SecurityCheckPhoneNumber>() {
        public SecurityCheckPhoneNumber[] newArray(int size) {
            return new SecurityCheckPhoneNumber[size];
        }

        public SecurityCheckPhoneNumber createFromParcel(Parcel source) {
            SecurityCheckPhoneNumber object = new SecurityCheckPhoneNumber();
            object.readFromParcel(source);
            return object;
        }
    };
}
