package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenSupportPhoneNumber;

public class SupportPhoneNumber extends GenSupportPhoneNumber {
    public static final Creator<SupportPhoneNumber> CREATOR = new Creator<SupportPhoneNumber>() {
        public SupportPhoneNumber[] newArray(int size) {
            return new SupportPhoneNumber[size];
        }

        public SupportPhoneNumber createFromParcel(Parcel source) {
            SupportPhoneNumber object = new SupportPhoneNumber();
            object.readFromParcel(source);
            return object;
        }
    };
}
