package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.AvailabilityConditionRange;
import com.airbnb.android.core.models.SimpleCalendarDay;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenCalendarMonth implements Parcelable {
    @JsonProperty("abbr_name")
    protected String mAbbrName;
    @JsonProperty("condition_ranges")
    protected List<AvailabilityConditionRange> mConditionRanges;
    @JsonProperty("days")
    protected List<SimpleCalendarDay> mDays;
    @JsonProperty("month")
    protected int mMonth;
    @JsonProperty("name")
    protected String mName;
    @JsonProperty("year")
    protected int mYear;

    protected GenCalendarMonth(List<AvailabilityConditionRange> conditionRanges, List<SimpleCalendarDay> days, String abbrName, String name, int month, int year) {
        this();
        this.mConditionRanges = conditionRanges;
        this.mDays = days;
        this.mAbbrName = abbrName;
        this.mName = name;
        this.mMonth = month;
        this.mYear = year;
    }

    protected GenCalendarMonth() {
    }

    public List<AvailabilityConditionRange> getConditionRanges() {
        return this.mConditionRanges;
    }

    @JsonProperty("condition_ranges")
    public void setConditionRanges(List<AvailabilityConditionRange> value) {
        this.mConditionRanges = value;
    }

    public List<SimpleCalendarDay> getDays() {
        return this.mDays;
    }

    @JsonProperty("days")
    public void setDays(List<SimpleCalendarDay> value) {
        this.mDays = value;
    }

    public String getAbbrName() {
        return this.mAbbrName;
    }

    @JsonProperty("abbr_name")
    public void setAbbrName(String value) {
        this.mAbbrName = value;
    }

    public String getName() {
        return this.mName;
    }

    @JsonProperty("name")
    public void setName(String value) {
        this.mName = value;
    }

    public int getMonth() {
        return this.mMonth;
    }

    @JsonProperty("month")
    public void setMonth(int value) {
        this.mMonth = value;
    }

    public int getYear() {
        return this.mYear;
    }

    @JsonProperty("year")
    public void setYear(int value) {
        this.mYear = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeTypedList(this.mConditionRanges);
        parcel.writeTypedList(this.mDays);
        parcel.writeString(this.mAbbrName);
        parcel.writeString(this.mName);
        parcel.writeInt(this.mMonth);
        parcel.writeInt(this.mYear);
    }

    public void readFromParcel(Parcel source) {
        this.mConditionRanges = source.createTypedArrayList(AvailabilityConditionRange.CREATOR);
        this.mDays = source.createTypedArrayList(SimpleCalendarDay.CREATOR);
        this.mAbbrName = source.readString();
        this.mName = source.readString();
        this.mMonth = source.readInt();
        this.mYear = source.readInt();
    }
}
