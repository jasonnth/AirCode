package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenGiftCreditCheckout;

public class GiftCreditCheckout extends GenGiftCreditCheckout {
    public static final Creator<GiftCreditCheckout> CREATOR = new Creator<GiftCreditCheckout>() {
        public GiftCreditCheckout[] newArray(int size) {
            return new GiftCreditCheckout[size];
        }

        public GiftCreditCheckout createFromParcel(Parcel source) {
            GiftCreditCheckout object = new GiftCreditCheckout();
            object.readFromParcel(source);
            return object;
        }
    };
}
