package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenReferralOffer;

public class ReferralOffer extends GenReferralOffer {
    public static final Creator<ReferralOffer> CREATOR = new Creator<ReferralOffer>() {
        public ReferralOffer[] newArray(int size) {
            return new ReferralOffer[size];
        }

        public ReferralOffer createFromParcel(Parcel source) {
            ReferralOffer object = new ReferralOffer();
            object.readFromParcel(source);
            return object;
        }
    };
}
