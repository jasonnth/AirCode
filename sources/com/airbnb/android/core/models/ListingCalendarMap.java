package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.utils.ParcelableUtils;
import java.util.HashMap;
import java.util.List;

public class ListingCalendarMap implements Parcelable {
    public static final Creator<ListingCalendarMap> CREATOR = new Creator<ListingCalendarMap>() {
        public ListingCalendarMap createFromParcel(Parcel in) {
            ListingCalendarMap result = new ListingCalendarMap();
            HashMap<String, CalendarDay> calMap = new HashMap<>();
            ParcelableUtils.readParcelableIntoMap(in, calMap, String.class, CalendarDay.class);
            result.setCalendarMap(calMap);
            return result;
        }

        public ListingCalendarMap[] newArray(int size) {
            return new ListingCalendarMap[size];
        }
    };
    public HashMap<String, CalendarDay> mCalendarData = new HashMap<>(MaxDaysNoticeSetting.MAX_DAYS_NOTICE_1_YEAR);

    public ListingCalendarMap() {
    }

    public ListingCalendarMap(List<CalendarDay> calendarDays) {
        for (CalendarDay day : calendarDays) {
            put(day.getDate().getIsoDateString(), day);
        }
    }

    public CalendarDay get(String string) {
        return (CalendarDay) this.mCalendarData.get(string);
    }

    public CalendarDay get(long date) {
        return (CalendarDay) this.mCalendarData.get(new AirDate(date).getIsoDateString());
    }

    public CalendarDay put(String key, CalendarDay value) {
        return (CalendarDay) this.mCalendarData.put(key, value);
    }

    public void updateAll(List<CalendarDay> days) {
        for (CalendarDay updatedDay : days) {
            String key = updatedDay.getDate().getIsoDateString();
            if (this.mCalendarData.containsKey(key)) {
                this.mCalendarData.put(key, updatedDay);
            } else if (BuildHelper.isDevelopmentBuild()) {
                throw new IllegalStateException("updating a calendar day that doesn't exist in this ListingCalendarMap. key = " + key);
            }
        }
    }

    public void setCalendarMap(HashMap<String, CalendarDay> calendarMap) {
        this.mCalendarData = calendarMap;
    }

    public void writeToParcel(Parcel dest, int flags) {
        ParcelableUtils.writeParcelableMap(dest, this.mCalendarData);
    }

    public int describeContents() {
        return 0;
    }

    public String getCurrencyCode(boolean useNativeCurrency) {
        if (this.mCalendarData == null || this.mCalendarData.isEmpty()) {
            return "";
        }
        for (CalendarDay day : this.mCalendarData.values()) {
            CalendarDayPriceInfo priceInfo = day.getPriceInfo();
            if (priceInfo != null) {
                if (useNativeCurrency) {
                    return priceInfo.getNativeCurrency();
                }
                return priceInfo.getLocalCurrency();
            }
        }
        return "";
    }
}
