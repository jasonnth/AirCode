package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenHostReferralReward;

public class HostReferralReward extends GenHostReferralReward {
    public static final Creator<HostReferralReward> CREATOR = new Creator<HostReferralReward>() {
        public HostReferralReward[] newArray(int size) {
            return new HostReferralReward[size];
        }

        public HostReferralReward createFromParcel(Parcel source) {
            HostReferralReward object = new HostReferralReward();
            object.readFromParcel(source);
            return object;
        }
    };
}
