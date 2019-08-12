package com.airbnb.android.core.utils;

import android.os.Parcelable;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.views.calendar.CalendarView.Style;

public abstract class DatesFragmentOptions implements Parcelable {

    public static abstract class Builder {
        public abstract DatesFragmentOptions build();

        public abstract Builder endDate(AirDate airDate);

        public abstract Builder endDateTitleOverride(int i);

        public abstract Builder formatWithYear(boolean z);

        public abstract Builder listing(Listing listing);

        public abstract Builder navigationExtras(ParcelStrap parcelStrap);

        public abstract Builder navigationTag(NavigationTag navigationTag);

        public abstract Builder preventEmptyDates(boolean z);

        public abstract Builder saveButtonTextOverride(int i);

        public abstract Builder singleDaySelectionMode(boolean z);

        public abstract Builder sourceTag(NavigationTag navigationTag);

        public abstract Builder startDate(AirDate airDate);

        public abstract Builder startDateTitleOverride(int i);

        public abstract Builder style(Style style);
    }

    public abstract AirDate endDate();

    public abstract int endDateTitleOverride();

    public abstract boolean formatWithYear();

    public abstract Listing listing();

    public abstract ParcelStrap navigationExtras();

    public abstract NavigationTag navigationTag();

    public abstract boolean preventEmptyDates();

    public abstract int saveButtonTextOverride();

    public abstract boolean singleDaySelectionMode();

    public abstract NavigationTag sourceTag();

    public abstract AirDate startDate();

    public abstract int startDateTitleOverride();

    public abstract Style style();

    public static Builder builder() {
        return new Builder().startDate(null).endDate(null).startDateTitleOverride(0).endDateTitleOverride(0).saveButtonTextOverride(0).listing(null).navigationExtras(null).preventEmptyDates(false).formatWithYear(false).singleDaySelectionMode(false);
    }
}
