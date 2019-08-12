package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.ActionCardMonthlyMarketDemand;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenInsightGraphicPayload implements Parcelable {
    @JsonProperty("boost_bar_value")
    protected int mBoostBarValue;
    @JsonProperty("demand_curve")
    protected List<ActionCardMonthlyMarketDemand> mDemandCurve;
    @JsonProperty("segmented_boost_bar_value")
    protected int mSegmentedBoostBarValue;

    protected GenInsightGraphicPayload(List<ActionCardMonthlyMarketDemand> demandCurve, int boostBarValue, int segmentedBoostBarValue) {
        this();
        this.mDemandCurve = demandCurve;
        this.mBoostBarValue = boostBarValue;
        this.mSegmentedBoostBarValue = segmentedBoostBarValue;
    }

    protected GenInsightGraphicPayload() {
    }

    public List<ActionCardMonthlyMarketDemand> getDemandCurve() {
        return this.mDemandCurve;
    }

    public int getBoostBarValue() {
        return this.mBoostBarValue;
    }

    @JsonProperty("boost_bar_value")
    public void setBoostBarValue(int value) {
        this.mBoostBarValue = value;
    }

    public int getSegmentedBoostBarValue() {
        return this.mSegmentedBoostBarValue;
    }

    @JsonProperty("segmented_boost_bar_value")
    public void setSegmentedBoostBarValue(int value) {
        this.mSegmentedBoostBarValue = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeTypedList(this.mDemandCurve);
        parcel.writeInt(this.mBoostBarValue);
        parcel.writeInt(this.mSegmentedBoostBarValue);
    }

    public void readFromParcel(Parcel source) {
        this.mDemandCurve = source.createTypedArrayList(ActionCardMonthlyMarketDemand.CREATOR);
        this.mBoostBarValue = source.readInt();
        this.mSegmentedBoostBarValue = source.readInt();
    }
}
