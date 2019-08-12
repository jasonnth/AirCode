package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenReferralStatus;

public class ReferralStatus extends GenReferralStatus {
    public static final Creator<ReferralStatus> CREATOR = new Creator<ReferralStatus>() {
        public ReferralStatus[] newArray(int size) {
            return new ReferralStatus[size];
        }

        public ReferralStatus createFromParcel(Parcel source) {
            ReferralStatus object = new ReferralStatus();
            object.readFromParcel(source);
            return object;
        }
    };
}
