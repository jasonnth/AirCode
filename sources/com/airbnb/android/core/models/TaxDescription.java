package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenTaxDescription;

public class TaxDescription extends GenTaxDescription {
    public static final Creator<TaxDescription> CREATOR = new Creator<TaxDescription>() {
        public TaxDescription[] newArray(int size) {
            return new TaxDescription[size];
        }

        public TaxDescription createFromParcel(Parcel source) {
            TaxDescription object = new TaxDescription();
            object.readFromParcel(source);
            return object;
        }
    };
}
