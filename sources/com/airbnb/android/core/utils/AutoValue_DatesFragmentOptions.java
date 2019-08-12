package com.airbnb.android.core.utils;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.views.calendar.CalendarView.Style;

final class AutoValue_DatesFragmentOptions extends C$AutoValue_DatesFragmentOptions {
    public static final Creator<AutoValue_DatesFragmentOptions> CREATOR = new Creator<AutoValue_DatesFragmentOptions>() {
        public AutoValue_DatesFragmentOptions createFromParcel(Parcel in) {
            boolean z;
            AirDate airDate = (AirDate) in.readParcelable(AirDate.class.getClassLoader());
            AirDate airDate2 = (AirDate) in.readParcelable(AirDate.class.getClassLoader());
            int readInt = in.readInt();
            int readInt2 = in.readInt();
            int readInt3 = in.readInt();
            Listing listing = (Listing) in.readParcelable(Listing.class.getClassLoader());
            NavigationTag valueOf = NavigationTag.valueOf(in.readString());
            NavigationTag valueOf2 = NavigationTag.valueOf(in.readString());
            ParcelStrap parcelStrap = (ParcelStrap) in.readParcelable(ParcelStrap.class.getClassLoader());
            Style valueOf3 = Style.valueOf(in.readString());
            boolean z2 = in.readInt() == 1;
            boolean z3 = in.readInt() == 1;
            if (in.readInt() == 1) {
                z = true;
            } else {
                z = false;
            }
            return new AutoValue_DatesFragmentOptions(airDate, airDate2, readInt, readInt2, readInt3, listing, valueOf, valueOf2, parcelStrap, valueOf3, z2, z3, z);
        }

        public AutoValue_DatesFragmentOptions[] newArray(int size) {
            return new AutoValue_DatesFragmentOptions[size];
        }
    };

    AutoValue_DatesFragmentOptions(AirDate startDate, AirDate endDate, int startDateTitleOverride, int endDateTitleOverride, int saveButtonTextOverride, Listing listing, NavigationTag navigationTag, NavigationTag sourceTag, ParcelStrap navigationExtras, Style style, boolean preventEmptyDates, boolean formatWithYear, boolean singleDaySelectionMode) {
        super(startDate, endDate, startDateTitleOverride, endDateTitleOverride, saveButtonTextOverride, listing, navigationTag, sourceTag, navigationExtras, style, preventEmptyDates, formatWithYear, singleDaySelectionMode);
    }

    public void writeToParcel(Parcel dest, int flags) {
        int i;
        int i2;
        int i3 = 1;
        dest.writeParcelable(startDate(), flags);
        dest.writeParcelable(endDate(), flags);
        dest.writeInt(startDateTitleOverride());
        dest.writeInt(endDateTitleOverride());
        dest.writeInt(saveButtonTextOverride());
        dest.writeParcelable(listing(), flags);
        dest.writeString(navigationTag().name());
        dest.writeString(sourceTag().name());
        dest.writeParcelable(navigationExtras(), flags);
        dest.writeString(style().name());
        if (preventEmptyDates()) {
            i = 1;
        } else {
            i = 0;
        }
        dest.writeInt(i);
        if (formatWithYear()) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        dest.writeInt(i2);
        if (!singleDaySelectionMode()) {
            i3 = 0;
        }
        dest.writeInt(i3);
    }

    public int describeContents() {
        return 0;
    }
}
