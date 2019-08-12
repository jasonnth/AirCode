package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenSuperhostMetric;

public class SuperhostMetric extends GenSuperhostMetric {
    public static final Creator<SuperhostMetric> CREATOR = new Creator<SuperhostMetric>() {
        public SuperhostMetric[] newArray(int size) {
            return new SuperhostMetric[size];
        }

        public SuperhostMetric createFromParcel(Parcel source) {
            SuperhostMetric object = new SuperhostMetric();
            object.readFromParcel(source);
            return object;
        }
    };

    public void setValue(int value) {
        this.mValue = value;
    }

    public void setThreshold(int threshold) {
        this.mThreshold = threshold;
    }

    public static SuperhostMetric create(int value, int threshold) {
        SuperhostMetric metric = new SuperhostMetric();
        metric.setValue(value);
        metric.setThreshold(threshold);
        return metric;
    }
}
