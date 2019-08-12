package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenActionCardMonthlyMarketDemand;

public class ActionCardMonthlyMarketDemand extends GenActionCardMonthlyMarketDemand {
    public static final Creator<ActionCardMonthlyMarketDemand> CREATOR = new Creator<ActionCardMonthlyMarketDemand>() {
        public ActionCardMonthlyMarketDemand[] newArray(int size) {
            return new ActionCardMonthlyMarketDemand[size];
        }

        public ActionCardMonthlyMarketDemand createFromParcel(Parcel source) {
            ActionCardMonthlyMarketDemand object = new ActionCardMonthlyMarketDemand();
            object.readFromParcel(source);
            return object;
        }
    };
}
