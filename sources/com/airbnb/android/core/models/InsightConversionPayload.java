package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenInsightConversionPayload;

public class InsightConversionPayload extends GenInsightConversionPayload {
    public static final Creator<InsightConversionPayload> CREATOR = new Creator<InsightConversionPayload>() {
        public InsightConversionPayload[] newArray(int size) {
            return new InsightConversionPayload[size];
        }

        public InsightConversionPayload createFromParcel(Parcel source) {
            InsightConversionPayload object = new InsightConversionPayload();
            object.readFromParcel(source);
            return object;
        }
    };
}
