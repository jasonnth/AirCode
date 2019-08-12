package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.airbnb.android.core.models.generated.GenHostStandard;

public class HostStandard extends GenHostStandard {
    public static final Creator<HostStandard> CREATOR = new Creator<HostStandard>() {
        public HostStandard[] newArray(int size) {
            return new HostStandard[size];
        }

        public HostStandard createFromParcel(Parcel source) {
            HostStandard object = new HostStandard();
            object.readFromParcel(source);
            return object;
        }
    };

    @Deprecated
    public boolean isSuspended() {
        return !TextUtils.equals(this.mSuspensionType, InternalLogger.EVENT_PARAM_EXTRAS_FALSE);
    }
}
