package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenCohostingStandard;
import com.airbnb.n2.R;
import com.google.common.collect.FluentIterable;

public class CohostingStandard extends GenCohostingStandard {
    public static final Creator<CohostingStandard> CREATOR = new Creator<CohostingStandard>() {
        public CohostingStandard[] newArray(int size) {
            return new CohostingStandard[size];
        }

        public CohostingStandard createFromParcel(Parcel source) {
            CohostingStandard object = new CohostingStandard();
            object.readFromParcel(source);
            return object;
        }
    };

    public enum MetricState {
        Success(State.MEETS_REQUIREMENT, R.color.n2_babu),
        Warning(State.NEEDS_IMPROVEMENT, R.color.n2_beach),
        Default(State.NO_STATS, R.color.n2_transparent);
        
        private final int colorRes;
        private final State key;

        private MetricState(State key2, int colorRes2) {
            this.key = key2;
            this.colorRes = colorRes2;
        }

        public static MetricState forKey(State key2) {
            return (MetricState) FluentIterable.m1283of(values()).firstMatch(CohostingStandard$MetricState$$Lambda$1.lambdaFactory$(key2)).mo41059or(Default);
        }

        public int getColorRes() {
            return this.colorRes;
        }
    }

    public enum State {
        NO_STATS,
        NEEDS_IMPROVEMENT,
        MEETS_REQUIREMENT
    }

    public boolean noStats() {
        return getInquiryNumTotal() == 0;
    }

    public boolean needsImprovement() {
        return getResponseRateCurrent() < getResponseRateThreshold();
    }

    public boolean meetStandardOfResponseRate() {
        return !needsImprovement();
    }

    public float getCurrentRatioOfResponseRate() {
        return ((float) getResponseRateCurrent()) / ((float) getResponseRateMax());
    }

    public float getThresholdRatioOfResponseRate() {
        return ((float) getResponseRateThreshold()) / ((float) getResponseRateMax());
    }

    public int getColorResByResponseRate() {
        return getResponseRateState().getColorRes();
    }

    public MetricState getResponseRateState() {
        if (getInquiryNumTotal() == 0) {
            return MetricState.forKey(State.NO_STATS);
        }
        if (needsImprovement()) {
            return MetricState.forKey(State.NEEDS_IMPROVEMENT);
        }
        return MetricState.forKey(State.MEETS_REQUIREMENT);
    }
}
