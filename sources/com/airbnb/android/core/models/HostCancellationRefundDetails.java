package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenHostCancellationRefundDetails;

public class HostCancellationRefundDetails extends GenHostCancellationRefundDetails {
    public static final Creator<HostCancellationRefundDetails> CREATOR = new Creator<HostCancellationRefundDetails>() {
        public HostCancellationRefundDetails[] newArray(int size) {
            return new HostCancellationRefundDetails[size];
        }

        public HostCancellationRefundDetails createFromParcel(Parcel source) {
            HostCancellationRefundDetails object = new HostCancellationRefundDetails();
            object.readFromParcel(source);
            return object;
        }
    };
}
