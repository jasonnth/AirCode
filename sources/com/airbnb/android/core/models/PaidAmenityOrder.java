package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenPaidAmenityOrder;
import com.airbnb.android.core.paidamenities.enums.PaidAmenityOrderStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PaidAmenityOrder extends GenPaidAmenityOrder {
    public static final Creator<PaidAmenityOrder> CREATOR = new Creator<PaidAmenityOrder>() {
        public PaidAmenityOrder[] newArray(int size) {
            return new PaidAmenityOrder[size];
        }

        public PaidAmenityOrder createFromParcel(Parcel source) {
            PaidAmenityOrder object = new PaidAmenityOrder();
            object.readFromParcel(source);
            return object;
        }
    };

    @JsonProperty("status_text")
    public void setStatus(PaidAmenityOrderStatus orderStatus) {
        this.mStatus = orderStatus;
    }
}
