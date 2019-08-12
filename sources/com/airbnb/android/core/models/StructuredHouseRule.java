package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenStructuredHouseRule;

public class StructuredHouseRule extends GenStructuredHouseRule {
    public static final Creator<StructuredHouseRule> CREATOR = new Creator<StructuredHouseRule>() {
        public StructuredHouseRule[] newArray(int size) {
            return new StructuredHouseRule[size];
        }

        public StructuredHouseRule createFromParcel(Parcel source) {
            StructuredHouseRule object = new StructuredHouseRule();
            object.readFromParcel(source);
            return object;
        }
    };
}
