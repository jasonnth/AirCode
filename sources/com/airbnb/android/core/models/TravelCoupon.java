package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenTravelCoupon;

public class TravelCoupon extends GenTravelCoupon {
    public static final Creator<TravelCoupon> CREATOR = new Creator<TravelCoupon>() {
        public TravelCoupon[] newArray(int size) {
            return new TravelCoupon[size];
        }

        public TravelCoupon createFromParcel(Parcel source) {
            TravelCoupon object = new TravelCoupon();
            object.readFromParcel(source);
            return object;
        }
    };
}
