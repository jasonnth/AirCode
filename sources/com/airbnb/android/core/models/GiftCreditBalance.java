package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenGiftCreditBalance;

public class GiftCreditBalance extends GenGiftCreditBalance {
    public static final Creator<GiftCreditBalance> CREATOR = new Creator<GiftCreditBalance>() {
        public GiftCreditBalance[] newArray(int size) {
            return new GiftCreditBalance[size];
        }

        public GiftCreditBalance createFromParcel(Parcel source) {
            GiftCreditBalance object = new GiftCreditBalance();
            object.readFromParcel(source);
            return object;
        }
    };
}
