package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.models.generated.GenListingCalendar;
import java.util.Comparator;
import java.util.List;

public class ListingCalendar extends GenListingCalendar {
    public static final Creator<ListingCalendar> CREATOR = new Creator<ListingCalendar>() {
        public ListingCalendar[] newArray(int size) {
            return new ListingCalendar[size];
        }

        public ListingCalendar createFromParcel(Parcel source) {
            ListingCalendar object = new ListingCalendar();
            object.readFromParcel(source);
            return object;
        }
    };
    public static Comparator<ListingCalendar> SORT_BY_ACTIVE_THEN_NAME = new Comparator<ListingCalendar>() {
        public int compare(ListingCalendar l1, ListingCalendar l2) {
            if (l1.isListingActive() == l2.isListingActive()) {
                return l1.getListingName().compareTo(l2.getListingName());
            }
            return l1.isListingActive() ? -1 : 1;
        }
    };

    public ListingCalendar() {
    }

    public ListingCalendar(AirDate startDate, AirDate endDate, List<CalendarDay> calendarDays, long listingId) {
        setCalendarDays(calendarDays);
        setEndDate(endDate);
        setStartDate(startDate);
        setListingId(listingId);
    }

    public void clearCalendarDays() {
        this.mCalendarDays.clear();
    }
}
