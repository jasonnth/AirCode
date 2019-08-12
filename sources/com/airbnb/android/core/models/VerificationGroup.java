package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenVerificationGroup;

public class VerificationGroup extends GenVerificationGroup {
    public static final Creator<VerificationGroup> CREATOR = new Creator<VerificationGroup>() {
        public VerificationGroup[] newArray(int size) {
            return new VerificationGroup[size];
        }

        public VerificationGroup createFromParcel(Parcel source) {
            VerificationGroup object = new VerificationGroup();
            object.readFromParcel(source);
            return object;
        }
    };
}
