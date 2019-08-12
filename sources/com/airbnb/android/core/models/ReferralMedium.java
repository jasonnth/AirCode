package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenReferralMedium;

public class ReferralMedium extends GenReferralMedium {
    public static final Creator<ReferralMedium> CREATOR = new Creator<ReferralMedium>() {
        public ReferralMedium[] newArray(int size) {
            return new ReferralMedium[size];
        }

        public ReferralMedium createFromParcel(Parcel source) {
            ReferralMedium object = new ReferralMedium();
            object.readFromParcel(source);
            return object;
        }
    };
}
