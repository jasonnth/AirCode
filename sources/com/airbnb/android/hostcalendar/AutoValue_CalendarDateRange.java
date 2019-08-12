package com.airbnb.android.hostcalendar;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.airdate.AirDate;

final class AutoValue_CalendarDateRange extends C$AutoValue_CalendarDateRange {
    public static final Creator<AutoValue_CalendarDateRange> CREATOR = new Creator<AutoValue_CalendarDateRange>() {
        public AutoValue_CalendarDateRange createFromParcel(Parcel in) {
            return new AutoValue_CalendarDateRange((AirDate) in.readParcelable(AirDate.class.getClassLoader()), (AirDate) in.readParcelable(AirDate.class.getClassLoader()), (AirDate) in.readParcelable(AirDate.class.getClassLoader()));
        }

        public AutoValue_CalendarDateRange[] newArray(int size) {
            return new AutoValue_CalendarDateRange[size];
        }
    };

    AutoValue_CalendarDateRange(AirDate startDate, AirDate endDate, AirDate scrollTargetDate) {
        super(startDate, endDate, scrollTargetDate);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(getStartDate(), flags);
        dest.writeParcelable(getEndDate(), flags);
        dest.writeParcelable(getScrollTargetDate(), flags);
    }

    public int describeContents() {
        return 0;
    }
}
