package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenHostCancellationDisclaimer;

public class HostCancellationDisclaimer extends GenHostCancellationDisclaimer {
    public static final Creator<HostCancellationDisclaimer> CREATOR = new Creator<HostCancellationDisclaimer>() {
        public HostCancellationDisclaimer[] newArray(int size) {
            return new HostCancellationDisclaimer[size];
        }

        public HostCancellationDisclaimer createFromParcel(Parcel source) {
            HostCancellationDisclaimer object = new HostCancellationDisclaimer();
            object.readFromParcel(source);
            return object;
        }
    };
}
