package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenGiftCardPromotion;

public class GiftCardPromotion extends GenGiftCardPromotion {
    public static final Creator<GiftCardPromotion> CREATOR = new Creator<GiftCardPromotion>() {
        public GiftCardPromotion[] newArray(int size) {
            return new GiftCardPromotion[size];
        }

        public GiftCardPromotion createFromParcel(Parcel source) {
            GiftCardPromotion object = new GiftCardPromotion();
            object.readFromParcel(source);
            return object;
        }
    };
}
