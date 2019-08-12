package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenPriceQuote;

public class PriceQuote extends GenPriceQuote {
    public static final Creator<PriceQuote> CREATOR = new Creator<PriceQuote>() {
        public PriceQuote[] newArray(int size) {
            return new PriceQuote[size];
        }

        public PriceQuote createFromParcel(Parcel source) {
            PriceQuote object = new PriceQuote();
            object.readFromParcel(source);
            return object;
        }
    };
}
