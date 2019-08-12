package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenHostStandardMetrics;
import com.airbnb.n2.R;
import com.google.common.collect.FluentIterable;

public class HostStandardMetrics extends GenHostStandardMetrics {
    public static final Creator<HostStandardMetrics> CREATOR = new Creator<HostStandardMetrics>() {
        public HostStandardMetrics[] newArray(int size) {
            return new HostStandardMetrics[size];
        }

        public HostStandardMetrics createFromParcel(Parcel source) {
            HostStandardMetrics object = new HostStandardMetrics();
            object.readFromParcel(source);
            return object;
        }
    };

    public enum MetricState {
        Success("success", R.color.n2_babu),
        Warning("warning", R.color.n2_beach),
        Danger("danger", R.color.n2_arches),
        Default("default", R.color.n2_transparent);
        
        private final int colorRes;
        private final String key;

        private MetricState(String key2, int colorRes2) {
            this.key = key2;
            this.colorRes = colorRes2;
        }

        public static MetricState forKey(String key2) {
            return (MetricState) FluentIterable.m1283of(values()).firstMatch(HostStandardMetrics$MetricState$$Lambda$1.lambdaFactory$(key2)).mo41059or(Default);
        }

        public int getColorRes() {
            return this.colorRes;
        }
    }

    public MetricState getOverallRatingMetricState() {
        return MetricState.forKey(getAverageOverallRatingStandardStateKey());
    }

    public MetricState getCommitmentRateMetricState() {
        return MetricState.forKey(getCommitmentRateStandardStateKey());
    }

    public MetricState getResponseRateMetricState() {
        return MetricState.forKey(getResponseRateStandardStateKey());
    }

    public float getCommitmentRateThreshold() {
        return getWarningThresholds().getCommitmentRate();
    }

    public float getResponseRateThreshold() {
        return getWarningThresholds().getResponseRate();
    }

    public float getAverageOverallRatingThreshold() {
        return getWarningThresholds().getAverageOverallRating();
    }
}
