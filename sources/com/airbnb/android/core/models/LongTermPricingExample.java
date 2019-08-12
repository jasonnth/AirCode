package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.models.generated.GenLongTermPricingExample;

public class LongTermPricingExample extends GenLongTermPricingExample {
    public static final Creator<LongTermPricingExample> CREATOR = new Creator<LongTermPricingExample>() {
        public LongTermPricingExample[] newArray(int size) {
            return new LongTermPricingExample[size];
        }

        public LongTermPricingExample createFromParcel(Parcel source) {
            LongTermPricingExample object = new LongTermPricingExample();
            object.readFromParcel(source);
            return object;
        }
    };

    public boolean shouldShowPercentageDiscounts() {
        return isAfterLaunchDate();
    }

    public boolean shouldShowPercentageDiscountDialog() {
        return isAfterLaunchDate() && isBefore14DayPeriod();
    }

    private boolean isAfterLaunchDate() {
        return AirDate.today().compareTo(getLaunchDate()) >= 0;
    }

    private boolean isBefore14DayPeriod() {
        return AirDate.today().compareTo(getLaunchDate().plusDays(14)) <= 0;
    }

    public int getTotalBeforeDiscount() {
        int total = 0;
        for (int sample : this.mSamples) {
            total += sample;
        }
        return total;
    }
}
