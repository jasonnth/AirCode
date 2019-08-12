package com.airbnb.android.core.enums;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.common.collect.FluentIterable;

public enum ListYourSpacePricingMode implements Parcelable {
    Undefined(0),
    Fixed(1),
    Smart(2);
    
    public static final Creator<ListYourSpacePricingMode> CREATOR = null;
    private final int serverKey;

    static {
        CREATOR = new Creator<ListYourSpacePricingMode>() {
            public ListYourSpacePricingMode createFromParcel(Parcel source) {
                return ListYourSpacePricingMode.values()[source.readInt()];
            }

            public ListYourSpacePricingMode[] newArray(int size) {
                return new ListYourSpacePricingMode[size];
            }
        };
    }

    private ListYourSpacePricingMode(int serverKey2) {
        this.serverKey = serverKey2;
    }

    public static ListYourSpacePricingMode fromServerKey(int serverKey2) {
        return (ListYourSpacePricingMode) FluentIterable.from((E[]) values()).filter(ListYourSpacePricingMode$$Lambda$1.lambdaFactory$(serverKey2)).first().mo41059or(Undefined);
    }

    public int getServerKey() {
        return this.serverKey;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(ordinal());
    }
}
