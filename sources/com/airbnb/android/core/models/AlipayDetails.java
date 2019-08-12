package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenAlipayDetails;

public class AlipayDetails extends GenAlipayDetails {
    public static final Creator<AlipayDetails> CREATOR = new Creator<AlipayDetails>() {
        public AlipayDetails[] newArray(int size) {
            return new AlipayDetails[size];
        }

        public AlipayDetails createFromParcel(Parcel source) {
            AlipayDetails object = new AlipayDetails();
            object.readFromParcel(source);
            return object;
        }
    };
}
