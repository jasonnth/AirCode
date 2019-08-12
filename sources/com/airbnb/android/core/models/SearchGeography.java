package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.airbnb.android.core.models.generated.GenSearchGeography;

public class SearchGeography extends GenSearchGeography {
    public static final Creator<SearchGeography> CREATOR = new Creator<SearchGeography>() {
        public SearchGeography[] newArray(int size) {
            return new SearchGeography[size];
        }

        public SearchGeography createFromParcel(Parcel source) {
            SearchGeography object = new SearchGeography();
            object.readFromParcel(source);
            return object;
        }
    };

    public boolean isPreciseLocation() {
        String precision = getPrecision();
        if (TextUtils.isEmpty(precision)) {
            return false;
        }
        if (precision.equals("unknown") || precision.equals("street") || precision.equals("address") || precision.equals("building")) {
            return true;
        }
        return false;
    }

    public boolean isPrecisionBroaderThanCity() {
        String precision = getPrecision();
        if (TextUtils.isEmpty(precision)) {
            return true;
        }
        if (precision.equals("city") || precision.equals("street") || precision.equals("address") || precision.equals("building")) {
            return false;
        }
        return true;
    }
}
