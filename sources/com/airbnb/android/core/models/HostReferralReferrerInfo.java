package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenHostReferralReferrerInfo;

public class HostReferralReferrerInfo extends GenHostReferralReferrerInfo {
    public static final Creator<HostReferralReferrerInfo> CREATOR = new Creator<HostReferralReferrerInfo>() {
        public HostReferralReferrerInfo[] newArray(int size) {
            return new HostReferralReferrerInfo[size];
        }

        public HostReferralReferrerInfo createFromParcel(Parcel source) {
            HostReferralReferrerInfo object = new HostReferralReferrerInfo();
            object.readFromParcel(source);
            return object;
        }
    };
}
