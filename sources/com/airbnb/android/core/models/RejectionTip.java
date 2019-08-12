package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenRejectionTip;

public class RejectionTip extends GenRejectionTip {
    public static final Creator<RejectionTip> CREATOR = new Creator<RejectionTip>() {
        public RejectionTip[] newArray(int size) {
            return new RejectionTip[size];
        }

        public RejectionTip createFromParcel(Parcel source) {
            RejectionTip object = new RejectionTip();
            object.readFromParcel(source);
            return object;
        }
    };
}
