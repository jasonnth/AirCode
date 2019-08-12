package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenSearchPriceHistogram;

public class SearchPriceHistogram extends GenSearchPriceHistogram {
    public static final Creator<SearchPriceHistogram> CREATOR = new Creator<SearchPriceHistogram>() {
        public SearchPriceHistogram[] newArray(int size) {
            return new SearchPriceHistogram[size];
        }

        public SearchPriceHistogram createFromParcel(Parcel source) {
            SearchPriceHistogram object = new SearchPriceHistogram();
            object.readFromParcel(source);
            return object;
        }
    };
}
