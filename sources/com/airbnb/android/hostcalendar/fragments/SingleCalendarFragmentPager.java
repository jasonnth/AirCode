package com.airbnb.android.hostcalendar.fragments;

import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentActivity;
import android.support.p000v4.app.FragmentManager;
import android.view.ViewGroup;
import com.airbnb.android.core.UnhandledStateException;
import com.airbnb.android.core.adapters.BaseTabFragmentPager;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.CalendarRule;
import com.airbnb.android.hostcalendar.C6418R;
import com.airbnb.android.hostcalendar.CalendarDateRange;
import java.util.Arrays;

public class SingleCalendarFragmentPager extends BaseTabFragmentPager {
    public static final TabType[] SINGLE_CALENDAR_VIEW_TYPES = {TabType.Month, TabType.Details};
    private final CalendarRule calendarRule;
    private int currentPosition = 0;
    private final CalendarDateRange initialDateRange;
    private final long listingId;

    public enum TabType {
        Month,
        Details
    }

    SingleCalendarFragmentPager(FragmentActivity activity, FragmentManager manager, long listingId2, CalendarDateRange initialDateRange2, CalendarRule calendarRule2) {
        super(activity, manager);
        this.listingId = listingId2;
        this.initialDateRange = initialDateRange2;
        this.calendarRule = calendarRule2;
    }

    public int getPageIconId(int position) {
        return 0;
    }

    public int getPageTitleId(int position) {
        TabType tab = SINGLE_CALENDAR_VIEW_TYPES[position];
        switch (tab) {
            case Month:
                return C6418R.string.host_calendar_month_tab_title;
            case Details:
                return C6418R.string.host_calendar_details_tab_title;
            default:
                throw new UnhandledStateException(tab);
        }
    }

    public NavigationTag getNavigationTrackingTag(int position) {
        TabType tab = SINGLE_CALENDAR_VIEW_TYPES[position];
        switch (tab) {
            case Month:
                return NavigationTag.CalendarSingleListingMonth;
            case Details:
                return NavigationTag.CalendarSingleListingAgenda;
            default:
                throw new UnhandledStateException(tab);
        }
    }

    public boolean isSwipePagingEnabled(int position) {
        return true;
    }

    public Fragment getItem(int position) {
        TabType tab = SINGLE_CALENDAR_VIEW_TYPES[position];
        switch (tab) {
            case Month:
                return SingleCalendarMonthFragment.calendarForDates(this.listingId, this.initialDateRange);
            case Details:
                return SingleCalendarDetailFragment.calendarForDates(this.listingId, this.initialDateRange, this.calendarRule);
            default:
                throw new UnhandledStateException(tab);
        }
    }

    public int getCount() {
        return SINGLE_CALENDAR_VIEW_TYPES.length;
    }

    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
        this.currentPosition = position;
    }

    public TabType getCurrentTabType() {
        return SINGLE_CALENDAR_VIEW_TYPES[this.currentPosition];
    }

    public int getPagePosition(TabType tabType) {
        return Arrays.asList(SINGLE_CALENDAR_VIEW_TYPES).indexOf(tabType);
    }
}
