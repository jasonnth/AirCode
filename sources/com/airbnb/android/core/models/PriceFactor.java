package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class PriceFactor implements Parcelable {
    public static final Creator<PriceFactor> CREATOR = new Creator<PriceFactor>() {
        public PriceFactor createFromParcel(Parcel in) {
            return new PriceFactor(in);
        }

        public PriceFactor[] newArray(int size) {
            return new PriceFactor[size];
        }
    };
    private Double priceFactor;

    public static PriceFactor forDiscount(String discount) {
        return new PriceFactor(Double.valueOf(1.0d - (Double.valueOf(discount).doubleValue() / 100.0d)));
    }

    public PriceFactor(Double priceFactor2) {
        this.priceFactor = priceFactor2;
    }

    public PriceFactor() {
    }

    public boolean hasDiscount() {
        return this.priceFactor != null && this.priceFactor.doubleValue() < 1.0d;
    }

    public boolean hasSetDiscountsBefore() {
        return this.priceFactor != null;
    }

    public Double getFactorValue() {
        return Double.valueOf(this.priceFactor != null ? this.priceFactor.doubleValue() : 1.0d);
    }

    public int getDiscountPercentage() {
        if (this.priceFactor != null) {
            return 100 - ((int) (this.priceFactor.doubleValue() * 100.0d));
        }
        return 0;
    }

    private PriceFactor(Parcel parcel) {
        this((Double) parcel.readValue(Double.class.getClassLoader()));
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.priceFactor);
    }

    public int describeContents() {
        return 0;
    }
}
