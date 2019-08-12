package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenCancellationRefundBanner;

public class CancellationRefundBanner extends GenCancellationRefundBanner {
    public static final Creator<CancellationRefundBanner> CREATOR = new Creator<CancellationRefundBanner>() {
        public CancellationRefundBanner[] newArray(int size) {
            return new CancellationRefundBanner[size];
        }

        public CancellationRefundBanner createFromParcel(Parcel source) {
            CancellationRefundBanner object = new CancellationRefundBanner();
            object.readFromParcel(source);
            return object;
        }
    };
}
