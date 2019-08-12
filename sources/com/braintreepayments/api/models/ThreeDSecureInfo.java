package com.braintreepayments.api.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import org.json.JSONObject;

public class ThreeDSecureInfo implements Parcelable {
    public static final Creator<ThreeDSecureInfo> CREATOR = new Creator<ThreeDSecureInfo>() {
        public ThreeDSecureInfo createFromParcel(Parcel source) {
            return new ThreeDSecureInfo(source);
        }

        public ThreeDSecureInfo[] newArray(int size) {
            return new ThreeDSecureInfo[size];
        }
    };
    private static final String LIABILITY_SHIFTED_KEY = "liabilityShifted";
    private static final String LIABILITY_SHIFT_POSSIBLE_KEY = "liabilityShiftPossible";
    private boolean mLiabilityShiftPossible;
    private boolean mLiabilityShifted;

    protected static ThreeDSecureInfo fromJson(JSONObject json) {
        if (json == null) {
            json = new JSONObject();
        }
        ThreeDSecureInfo threeDSecureInfo = new ThreeDSecureInfo();
        threeDSecureInfo.mLiabilityShifted = json.optBoolean(LIABILITY_SHIFTED_KEY);
        threeDSecureInfo.mLiabilityShiftPossible = json.optBoolean(LIABILITY_SHIFT_POSSIBLE_KEY);
        return threeDSecureInfo;
    }

    public boolean isLiabilityShifted() {
        return this.mLiabilityShifted;
    }

    public boolean isLiabilityShiftPossible() {
        return this.mLiabilityShiftPossible;
    }

    public ThreeDSecureInfo() {
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        byte b;
        byte b2 = 1;
        if (this.mLiabilityShifted) {
            b = 1;
        } else {
            b = 0;
        }
        dest.writeByte(b);
        if (!this.mLiabilityShiftPossible) {
            b2 = 0;
        }
        dest.writeByte(b2);
    }

    private ThreeDSecureInfo(Parcel in) {
        boolean z;
        boolean z2 = true;
        if (in.readByte() != 0) {
            z = true;
        } else {
            z = false;
        }
        this.mLiabilityShifted = z;
        if (in.readByte() == 0) {
            z2 = false;
        }
        this.mLiabilityShiftPossible = z2;
    }
}
