package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenActionCardDataPayload;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

public class ActionCardDataPayload extends GenActionCardDataPayload {
    public static final Creator<ActionCardDataPayload> CREATOR = new Creator<ActionCardDataPayload>() {
        public ActionCardDataPayload[] newArray(int size) {
            return new ActionCardDataPayload[size];
        }

        public ActionCardDataPayload createFromParcel(Parcel source) {
            ActionCardDataPayload object = new ActionCardDataPayload();
            object.readFromParcel(source);
            return object;
        }
    };

    @JsonProperty("monthly_market_demand")
    public void setMonthlyMarketDemand(List<List<ActionCardMonthlyMarketDemandWrapper>> monthlyMarketDemand) {
        if (monthlyMarketDemand != null) {
            this.mMonthlyMarketDemand = new ArrayList();
            for (List<ActionCardMonthlyMarketDemandWrapper> wrappedDemandList : monthlyMarketDemand) {
                for (ActionCardMonthlyMarketDemandWrapper wrappedDemand : wrappedDemandList) {
                    this.mMonthlyMarketDemand.add(wrappedDemand.payload);
                }
            }
        }
    }
}
