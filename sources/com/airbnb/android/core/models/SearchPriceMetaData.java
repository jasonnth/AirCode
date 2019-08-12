package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenSearchPriceMetaData;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SearchPriceMetaData extends GenSearchPriceMetaData {
    public static final Creator<SearchPriceMetaData> CREATOR = new Creator<SearchPriceMetaData>() {
        public SearchPriceMetaData[] newArray(int size) {
            return new SearchPriceMetaData[size];
        }

        public SearchPriceMetaData createFromParcel(Parcel source) {
            SearchPriceMetaData object = new SearchPriceMetaData();
            object.readFromParcel(source);
            return object;
        }
    };

    public enum PriceType {
        Nightly("nightly"),
        Monthly("monthly");
        
        private final String key;

        private PriceType(String key2) {
            this.key = key2;
        }

        /* access modifiers changed from: private */
        public static PriceType fromServerKey(String serverKey) {
            PriceType[] values;
            for (PriceType type : values()) {
                if (type.key.equals(serverKey)) {
                    return type;
                }
            }
            return null;
        }
    }

    public boolean isPriceMonthly() {
        return getPriceType() == PriceType.Monthly;
    }

    @JsonProperty("price_type")
    public void setPriceType(String serverKey) {
        this.mPriceType = PriceType.fromServerKey(serverKey);
    }
}
