package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenReferralStatusForMobile;

public class ReferralStatusForMobile extends GenReferralStatusForMobile {
    public static final Creator<ReferralStatusForMobile> CREATOR = new Creator<ReferralStatusForMobile>() {
        public ReferralStatusForMobile[] newArray(int size) {
            return new ReferralStatusForMobile[size];
        }

        public ReferralStatusForMobile createFromParcel(Parcel source) {
            ReferralStatusForMobile object = new ReferralStatusForMobile();
            object.readFromParcel(source);
            return object;
        }
    };
}
