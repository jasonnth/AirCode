package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenSecurityCheckRequirements;

public class SecurityCheckRequirements extends GenSecurityCheckRequirements {
    public static final Creator<SecurityCheckRequirements> CREATOR = new Creator<SecurityCheckRequirements>() {
        public SecurityCheckRequirements[] newArray(int size) {
            return new SecurityCheckRequirements[size];
        }

        public SecurityCheckRequirements createFromParcel(Parcel source) {
            SecurityCheckRequirements object = new SecurityCheckRequirements();
            object.readFromParcel(source);
            return object;
        }
    };
}
