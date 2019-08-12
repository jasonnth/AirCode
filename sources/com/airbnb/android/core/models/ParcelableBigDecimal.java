package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.math.BigDecimal;

public class ParcelableBigDecimal extends BigDecimal implements Parcelable {
    public static final Creator<ParcelableBigDecimal> CREATOR = new Creator<ParcelableBigDecimal>() {
        public ParcelableBigDecimal createFromParcel(Parcel source) {
            return new ParcelableBigDecimal(source.readString());
        }

        public ParcelableBigDecimal[] newArray(int size) {
            return new ParcelableBigDecimal[size];
        }
    };

    public ParcelableBigDecimal(BigDecimal val) {
        super(val.toPlainString());
    }

    public ParcelableBigDecimal(int val) {
        super(val);
    }

    public ParcelableBigDecimal(double val) {
        super(val);
    }

    public ParcelableBigDecimal(String val) {
        super(val);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(toPlainString());
    }
}
