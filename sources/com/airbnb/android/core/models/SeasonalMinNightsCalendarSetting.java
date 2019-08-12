package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenSeasonalMinNightsCalendarSetting;
import com.google.common.base.Objects;

public class SeasonalMinNightsCalendarSetting extends GenSeasonalMinNightsCalendarSetting {
    public static final Creator<SeasonalMinNightsCalendarSetting> CREATOR = new Creator<SeasonalMinNightsCalendarSetting>() {
        public SeasonalMinNightsCalendarSetting[] newArray(int size) {
            return new SeasonalMinNightsCalendarSetting[size];
        }

        public SeasonalMinNightsCalendarSetting createFromParcel(Parcel source) {
            SeasonalMinNightsCalendarSetting object = new SeasonalMinNightsCalendarSetting();
            object.readFromParcel(source);
            return object;
        }
    };

    public SeasonalMinNightsCalendarSetting() {
        this.mMinNights = 1;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        SeasonalMinNightsCalendarSetting other = (SeasonalMinNightsCalendarSetting) obj;
        if (!Objects.equal(this.mStartDate, other.mStartDate) || !Objects.equal(this.mEndDate, other.mEndDate) || !Objects.equal(this.mStartDayOfWeek, other.mStartDayOfWeek) || !Objects.equal(Integer.valueOf(this.mMinNights), Integer.valueOf(other.mMinNights))) {
            return false;
        }
        return true;
    }
}
