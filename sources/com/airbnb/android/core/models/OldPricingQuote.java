package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenOldPricingQuote;

@Deprecated
public class OldPricingQuote extends GenOldPricingQuote {
    public static final Creator<OldPricingQuote> CREATOR = new Creator<OldPricingQuote>() {
        public OldPricingQuote[] newArray(int size) {
            return new OldPricingQuote[size];
        }

        public OldPricingQuote createFromParcel(Parcel source) {
            OldPricingQuote object = new OldPricingQuote();
            object.readFromParcel(source);
            return object;
        }
    };
}
