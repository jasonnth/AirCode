package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenFullRefundUpsellInfo;

public class FullRefundUpsellInfo extends GenFullRefundUpsellInfo {
    public static final Creator<FullRefundUpsellInfo> CREATOR = new Creator<FullRefundUpsellInfo>() {
        public FullRefundUpsellInfo[] newArray(int size) {
            return new FullRefundUpsellInfo[size];
        }

        public FullRefundUpsellInfo createFromParcel(Parcel source) {
            FullRefundUpsellInfo object = new FullRefundUpsellInfo();
            object.readFromParcel(source);
            return object;
        }
    };
}
