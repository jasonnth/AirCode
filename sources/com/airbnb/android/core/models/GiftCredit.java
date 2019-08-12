package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenGiftCredit;

public class GiftCredit extends GenGiftCredit {
    public static final Creator<GiftCredit> CREATOR = new Creator<GiftCredit>() {
        public GiftCredit[] newArray(int size) {
            return new GiftCredit[size];
        }

        public GiftCredit createFromParcel(Parcel source) {
            GiftCredit object = new GiftCredit();
            object.readFromParcel(source);
            return object;
        }
    };
}
