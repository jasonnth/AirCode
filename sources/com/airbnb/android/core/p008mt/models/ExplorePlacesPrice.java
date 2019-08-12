package com.airbnb.android.core.p008mt.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

/* renamed from: com.airbnb.android.core.mt.models.ExplorePlacesPrice */
public enum ExplorePlacesPrice implements Parcelable {
    OneDollarSign(1),
    TwoDollarSigns(2),
    ThreeDollarSigns(3),
    FourDollarSigns(4);
    
    public static final Creator<ExplorePlacesPrice> CREATOR = null;
    private final int queryString;

    static {
        CREATOR = new Creator<ExplorePlacesPrice>() {
            public ExplorePlacesPrice createFromParcel(Parcel source) {
                return ExplorePlacesPrice.values()[source.readInt()];
            }

            public ExplorePlacesPrice[] newArray(int size) {
                return new ExplorePlacesPrice[size];
            }
        };
    }

    private ExplorePlacesPrice(int queryString2) {
        this.queryString = queryString2;
    }

    public int getQueryString() {
        return this.queryString;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ordinal());
    }
}
