package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenSuperhostMetric implements Parcelable {
    @JsonProperty("threshold")
    protected int mThreshold;
    @JsonProperty("value")
    protected int mValue;

    protected GenSuperhostMetric(int value, int threshold) {
        this();
        this.mValue = value;
        this.mThreshold = threshold;
    }

    protected GenSuperhostMetric() {
    }

    public int getValue() {
        return this.mValue;
    }

    public int getThreshold() {
        return this.mThreshold;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(this.mValue);
        parcel.writeInt(this.mThreshold);
    }

    public void readFromParcel(Parcel source) {
        this.mValue = source.readInt();
        this.mThreshold = source.readInt();
    }
}
