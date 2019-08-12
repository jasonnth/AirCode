package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenAffiliateClick;

public class AffiliateClick extends GenAffiliateClick {
    public static final Creator<AffiliateClick> CREATOR = new Creator<AffiliateClick>() {
        public AffiliateClick[] newArray(int size) {
            return new AffiliateClick[size];
        }

        public AffiliateClick createFromParcel(Parcel source) {
            AffiliateClick object = new AffiliateClick();
            object.readFromParcel(source);
            return object;
        }
    };
}
