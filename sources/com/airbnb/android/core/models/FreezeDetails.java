package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenFreezeDetails;

public class FreezeDetails extends GenFreezeDetails {
    public static final Creator<FreezeDetails> CREATOR = new Creator<FreezeDetails>() {
        public FreezeDetails[] newArray(int size) {
            return new FreezeDetails[size];
        }

        public FreezeDetails createFromParcel(Parcel source) {
            FreezeDetails object = new FreezeDetails();
            object.readFromParcel(source);
            return object;
        }
    };
    public static final String HOST_REQUIRED = "host_required";

    public boolean requiredByHost() {
        return isShouldBeFrozen() && HOST_REQUIRED.equals(getReason());
    }
}
