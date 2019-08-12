package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenPhoneNumberConfirmation;

public class PhoneNumberConfirmation extends GenPhoneNumberConfirmation {
    public static final Creator<PhoneNumberConfirmation> CREATOR = new Creator<PhoneNumberConfirmation>() {
        public PhoneNumberConfirmation[] newArray(int size) {
            return new PhoneNumberConfirmation[size];
        }

        public PhoneNumberConfirmation createFromParcel(Parcel source) {
            PhoneNumberConfirmation object = new PhoneNumberConfirmation();
            object.readFromParcel(source);
            return object;
        }
    };
}
