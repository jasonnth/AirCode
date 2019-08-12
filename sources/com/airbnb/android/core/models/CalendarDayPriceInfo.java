package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenCalendarDayPriceInfo;
import com.airbnb.android.lib.analytics.ManageListingAnalytics;
import com.airbnb.android.utils.CurrencyUtils;
import java.util.HashMap;

public class CalendarDayPriceInfo extends GenCalendarDayPriceInfo {
    public static final Creator<CalendarDayPriceInfo> CREATOR = new Creator<CalendarDayPriceInfo>() {
        public CalendarDayPriceInfo[] newArray(int size) {
            return new CalendarDayPriceInfo[size];
        }

        public CalendarDayPriceInfo createFromParcel(Parcel source) {
            CalendarDayPriceInfo object = new CalendarDayPriceInfo();
            object.readFromParcel(source);
            return object;
        }
    };

    public enum Type {
        Custom("custom"),
        Weekend("weekend"),
        Default("default"),
        DemandBased(ManageListingAnalytics.DEMAND_BASED_PRICING);
        
        private static HashMap<String, Type> map;
        private final String key;

        private Type(String key2) {
            this.key = key2;
        }

        public static Type findForKey(String key2) {
            Type[] values;
            Type[] values2;
            if (map == null) {
                map = new HashMap<>();
                for (Type type : values()) {
                    map.put(type.key, type);
                }
            }
            for (Type type2 : values()) {
                if (type2.key.equals(key2)) {
                    return type2;
                }
            }
            return null;
        }
    }

    public boolean equals(Object o) {
        boolean z = true;
        if (this == o) {
            return true;
        }
        if (!(o instanceof CalendarDayPriceInfo)) {
            return false;
        }
        CalendarDayPriceInfo that = (CalendarDayPriceInfo) o;
        if (this.mDemandBasedPricingOverridden != that.mDemandBasedPricingOverridden || this.mLocalPrice != that.mLocalPrice || this.mNativePrice != that.mNativePrice || this.mNativeDemandBasedPrice != that.mNativeDemandBasedPrice) {
            return false;
        }
        if (this.mLocalCurrency != null) {
            if (!this.mLocalCurrency.equals(that.mLocalCurrency)) {
                return false;
            }
        } else if (that.mLocalCurrency != null) {
            return false;
        }
        if (this.mNativeCurrency != null) {
            if (!this.mNativeCurrency.equals(that.mNativeCurrency)) {
                return false;
            }
        } else if (that.mNativeCurrency != null) {
            return false;
        }
        if (this.mTypeStr != null) {
            z = this.mTypeStr.equals(that.mTypeStr);
        } else if (that.mTypeStr != null) {
            z = false;
        }
        return z;
    }

    public Type getType() {
        return Type.findForKey(this.mTypeStr);
    }

    public String getFormattedNativePrice() {
        return CurrencyUtils.formatCurrencyAmount((double) getNativePrice(), getNativeCurrency());
    }

    public int getRecommendedSmartPrice() {
        if (this.mDemandBasedPricingOverridden) {
            return this.mNativeDemandBasedPrice;
        }
        return this.mNativePrice;
    }
}
