package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenDynamicPricingExplanation;

public class DynamicPricingExplanation extends GenDynamicPricingExplanation {
    public static final Creator<DynamicPricingExplanation> CREATOR = new Creator<DynamicPricingExplanation>() {
        public DynamicPricingExplanation[] newArray(int size) {
            return new DynamicPricingExplanation[size];
        }

        public DynamicPricingExplanation createFromParcel(Parcel source) {
            DynamicPricingExplanation object = new DynamicPricingExplanation();
            object.readFromParcel(source);
            return object;
        }
    };
}
