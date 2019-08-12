package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenCurrency;

public class Currency extends GenCurrency {
    public static final Creator<Currency> CREATOR = new Creator<Currency>() {
        public Currency[] newArray(int size) {
            return new Currency[size];
        }

        public Currency createFromParcel(Parcel source) {
            Currency object = new Currency();
            object.readFromParcel(source);
            return object;
        }
    };

    public int hashCode() {
        return (this.mCode == null ? 0 : this.mCode.hashCode()) + 31;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Currency other = (Currency) obj;
        if (this.mCode == null) {
            if (other.mCode != null) {
                return false;
            }
            return true;
        } else if (!this.mCode.equals(other.mCode)) {
            return false;
        } else {
            return true;
        }
    }
}
