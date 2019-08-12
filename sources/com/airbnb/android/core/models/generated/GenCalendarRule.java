package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.AdvanceNoticeSetting;
import com.airbnb.android.core.models.MaxDaysNoticeSetting;
import com.airbnb.android.core.models.SeasonalMinNightsCalendarSetting;
import com.airbnb.android.core.models.TurnoverDaysSetting;
import com.airbnb.android.core.models.WeekendMinNightsCalendarSetting;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;

public abstract class GenCalendarRule implements Parcelable {
    @JsonProperty("booking_lead_time")
    protected AdvanceNoticeSetting mAdvanceNotice;
    @JsonProperty("max_days_notice")
    protected MaxDaysNoticeSetting mMaxDaysNotice;
    @JsonProperty("seasonal_min_nights")
    protected ArrayList<SeasonalMinNightsCalendarSetting> mSeasonalCalendarSettings;
    @JsonProperty("turnover_days")
    protected TurnoverDaysSetting mTurnoverDays;
    @JsonProperty("weekend_min_nights")
    protected WeekendMinNightsCalendarSetting mWeekendMinNightsSetting;

    protected GenCalendarRule(AdvanceNoticeSetting advanceNotice, ArrayList<SeasonalMinNightsCalendarSetting> seasonalCalendarSettings, MaxDaysNoticeSetting maxDaysNotice, TurnoverDaysSetting turnoverDays, WeekendMinNightsCalendarSetting weekendMinNightsSetting) {
        this();
        this.mAdvanceNotice = advanceNotice;
        this.mSeasonalCalendarSettings = seasonalCalendarSettings;
        this.mMaxDaysNotice = maxDaysNotice;
        this.mTurnoverDays = turnoverDays;
        this.mWeekendMinNightsSetting = weekendMinNightsSetting;
    }

    protected GenCalendarRule() {
    }

    public AdvanceNoticeSetting getAdvanceNotice() {
        return this.mAdvanceNotice;
    }

    @JsonProperty("booking_lead_time")
    public void setAdvanceNotice(AdvanceNoticeSetting value) {
        this.mAdvanceNotice = value;
    }

    public ArrayList<SeasonalMinNightsCalendarSetting> getSeasonalCalendarSettings() {
        return this.mSeasonalCalendarSettings;
    }

    @JsonProperty("seasonal_min_nights")
    public void setSeasonalCalendarSettings(ArrayList<SeasonalMinNightsCalendarSetting> value) {
        this.mSeasonalCalendarSettings = value;
    }

    public MaxDaysNoticeSetting getMaxDaysNotice() {
        return this.mMaxDaysNotice;
    }

    @JsonProperty("max_days_notice")
    public void setMaxDaysNotice(MaxDaysNoticeSetting value) {
        this.mMaxDaysNotice = value;
    }

    public TurnoverDaysSetting getTurnoverDays() {
        return this.mTurnoverDays;
    }

    @JsonProperty("turnover_days")
    public void setTurnoverDays(TurnoverDaysSetting value) {
        this.mTurnoverDays = value;
    }

    public WeekendMinNightsCalendarSetting getWeekendMinNightsSetting() {
        return this.mWeekendMinNightsSetting;
    }

    @JsonProperty("weekend_min_nights")
    public void setWeekendMinNightsSetting(WeekendMinNightsCalendarSetting value) {
        this.mWeekendMinNightsSetting = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mAdvanceNotice, 0);
        parcel.writeTypedList(this.mSeasonalCalendarSettings);
        parcel.writeParcelable(this.mMaxDaysNotice, 0);
        parcel.writeParcelable(this.mTurnoverDays, 0);
        parcel.writeParcelable(this.mWeekendMinNightsSetting, 0);
    }

    public void readFromParcel(Parcel source) {
        this.mAdvanceNotice = (AdvanceNoticeSetting) source.readParcelable(AdvanceNoticeSetting.class.getClassLoader());
        this.mSeasonalCalendarSettings = source.createTypedArrayList(SeasonalMinNightsCalendarSetting.CREATOR);
        this.mMaxDaysNotice = (MaxDaysNoticeSetting) source.readParcelable(MaxDaysNoticeSetting.class.getClassLoader());
        this.mTurnoverDays = (TurnoverDaysSetting) source.readParcelable(TurnoverDaysSetting.class.getClassLoader());
        this.mWeekendMinNightsSetting = (WeekendMinNightsCalendarSetting) source.readParcelable(WeekendMinNightsCalendarSetting.class.getClassLoader());
    }
}
