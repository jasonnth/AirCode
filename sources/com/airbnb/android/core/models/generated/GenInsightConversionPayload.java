package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.airdate.AirDate;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenInsightConversionPayload implements Parcelable {
    @JsonProperty("date_range")
    protected List<AirDate> mDateRange;
    @JsonProperty("integer_value")
    protected int mIntegerValue;
    @JsonProperty("month")
    protected AirDate mMonth;

    protected GenInsightConversionPayload(AirDate month, List<AirDate> dateRange, int integerValue) {
        this();
        this.mMonth = month;
        this.mDateRange = dateRange;
        this.mIntegerValue = integerValue;
    }

    protected GenInsightConversionPayload() {
    }

    public AirDate getMonth() {
        return this.mMonth;
    }

    @JsonProperty("month")
    public void setMonth(AirDate value) {
        this.mMonth = value;
    }

    public List<AirDate> getDateRange() {
        return this.mDateRange;
    }

    @JsonProperty("date_range")
    public void setDateRange(List<AirDate> value) {
        this.mDateRange = value;
    }

    public int getIntegerValue() {
        return this.mIntegerValue;
    }

    @JsonProperty("integer_value")
    public void setIntegerValue(int value) {
        this.mIntegerValue = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mMonth, 0);
        parcel.writeTypedList(this.mDateRange);
        parcel.writeInt(this.mIntegerValue);
    }

    public void readFromParcel(Parcel source) {
        this.mMonth = (AirDate) source.readParcelable(AirDate.class.getClassLoader());
        this.mDateRange = source.createTypedArrayList(AirDate.CREATOR);
        this.mIntegerValue = source.readInt();
    }
}
