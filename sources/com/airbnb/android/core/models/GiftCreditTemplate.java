package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenGiftCreditTemplate;

public class GiftCreditTemplate extends GenGiftCreditTemplate {
    public static final Creator<GiftCreditTemplate> CREATOR = new Creator<GiftCreditTemplate>() {
        public GiftCreditTemplate[] newArray(int size) {
            return new GiftCreditTemplate[size];
        }

        public GiftCreditTemplate createFromParcel(Parcel source) {
            GiftCreditTemplate object = new GiftCreditTemplate();
            object.readFromParcel(source);
            return object;
        }
    };
}
