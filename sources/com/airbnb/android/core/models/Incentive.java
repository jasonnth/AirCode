package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenIncentive;

public class Incentive extends GenIncentive {
    public static final Creator<Incentive> CREATOR = new Creator<Incentive>() {
        public Incentive[] newArray(int size) {
            return new Incentive[size];
        }

        public Incentive createFromParcel(Parcel source) {
            Incentive object = new Incentive();
            object.readFromParcel(source);
            return object;
        }
    };
    public static final String IB_PROMO = "instant_book_promo";
    public static final String PENALTY_FREE_CANCELLATION_TRIAL = "penalty_free_cancellation";
}
