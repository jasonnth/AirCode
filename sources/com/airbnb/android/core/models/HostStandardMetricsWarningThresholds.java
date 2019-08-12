package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenHostStandardMetricsWarningThresholds;

public class HostStandardMetricsWarningThresholds extends GenHostStandardMetricsWarningThresholds {
    public static final Creator<HostStandardMetricsWarningThresholds> CREATOR = new Creator<HostStandardMetricsWarningThresholds>() {
        public HostStandardMetricsWarningThresholds[] newArray(int size) {
            return new HostStandardMetricsWarningThresholds[size];
        }

        public HostStandardMetricsWarningThresholds createFromParcel(Parcel source) {
            HostStandardMetricsWarningThresholds object = new HostStandardMetricsWarningThresholds();
            object.readFromParcel(source);
            return object;
        }
    };
}
