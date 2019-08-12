package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenVerificationGroups;

public class VerificationGroups extends GenVerificationGroups {
    public static final Creator<VerificationGroups> CREATOR = new Creator<VerificationGroups>() {
        public VerificationGroups[] newArray(int size) {
            return new VerificationGroups[size];
        }

        public VerificationGroups createFromParcel(Parcel source) {
            VerificationGroups object = new VerificationGroups();
            object.readFromParcel(source);
            return object;
        }
    };
}
