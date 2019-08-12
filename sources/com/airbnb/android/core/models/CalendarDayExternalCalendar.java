package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenCalendarDayExternalCalendar;

public class CalendarDayExternalCalendar extends GenCalendarDayExternalCalendar {
    public static final Creator<CalendarDayExternalCalendar> CREATOR = new Creator<CalendarDayExternalCalendar>() {
        public CalendarDayExternalCalendar[] newArray(int size) {
            return new CalendarDayExternalCalendar[size];
        }

        public CalendarDayExternalCalendar createFromParcel(Parcel source) {
            CalendarDayExternalCalendar object = new CalendarDayExternalCalendar();
            object.readFromParcel(source);
            return object;
        }
    };

    public String getNotes() {
        String notes = super.getNotes();
        if (notes == null) {
            return "";
        }
        return notes;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CalendarDayExternalCalendar)) {
            return false;
        }
        CalendarDayExternalCalendar that = (CalendarDayExternalCalendar) o;
        if (this.mName == null ? that.mName != null : !this.mName.equals(that.mName)) {
            return false;
        }
        if (this.mNotes != null) {
            return this.mNotes.equals(that.mNotes);
        }
        if (that.mNotes != null) {
            return false;
        }
        return true;
    }
}
