package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenTurnoverDaysSetting;

public class TurnoverDaysSetting extends GenTurnoverDaysSetting {
    public static final Creator<TurnoverDaysSetting> CREATOR = new Creator<TurnoverDaysSetting>() {
        public TurnoverDaysSetting[] newArray(int size) {
            return new TurnoverDaysSetting[size];
        }

        public TurnoverDaysSetting createFromParcel(Parcel source) {
            TurnoverDaysSetting object = new TurnoverDaysSetting();
            object.readFromParcel(source);
            return object;
        }
    };
    public static final int TURNOVER_DAYS_1_DAY = 1;
    public static final int TURNOVER_DAYS_2_DAYS = 2;
    public static final int TURNOVER_DAYS_NONE = 0;

    public static TurnoverDaysSetting newInstance(int days) {
        TurnoverDaysSetting option = new TurnoverDaysSetting();
        option.setDays(days);
        return option;
    }

    public static TurnoverDaysSetting[] values() {
        return new TurnoverDaysSetting[]{newInstance(0), newInstance(1), newInstance(2)};
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (this.mDays != ((TurnoverDaysSetting) o).mDays) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.mDays;
    }
}
