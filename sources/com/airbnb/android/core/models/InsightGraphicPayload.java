package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenInsightGraphicPayload;

public class InsightGraphicPayload extends GenInsightGraphicPayload {
    public static final Creator<InsightGraphicPayload> CREATOR = new Creator<InsightGraphicPayload>() {
        public InsightGraphicPayload[] newArray(int size) {
            return new InsightGraphicPayload[size];
        }

        public InsightGraphicPayload createFromParcel(Parcel source) {
            InsightGraphicPayload object = new InsightGraphicPayload();
            object.readFromParcel(source);
            return object;
        }
    };
}
