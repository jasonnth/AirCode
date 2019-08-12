package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenAutoPricing;

public class AutoPricing extends GenAutoPricing {
    public static final Creator<AutoPricing> CREATOR = new Creator<AutoPricing>() {
        public AutoPricing[] newArray(int size) {
            return new AutoPricing[size];
        }

        public AutoPricing createFromParcel(Parcel source) {
            AutoPricing object = new AutoPricing();
            object.readFromParcel(source);
            return object;
        }
    };
}
