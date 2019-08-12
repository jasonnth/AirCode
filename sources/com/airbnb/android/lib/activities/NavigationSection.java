package com.airbnb.android.lib.activities;

import android.text.TextUtils;
import com.airbnb.android.contentframework.ContentFrameworkUtil;
import com.airbnb.android.core.analytics.NavigationModeType;
import com.airbnb.android.lib.C0880R;
import com.google.common.collect.FluentIterable;

public enum NavigationSection {
    GuestHome(C0880R.C0882id.nav_home, NavigationModeType.GuestOnly, ContentFrameworkUtil.GUEST_HOME),
    Trips(C0880R.C0882id.nav_trips, NavigationModeType.GuestOnly, "trips"),
    GuestInbox(C0880R.C0882id.nav_inbox_guest, NavigationModeType.GuestOnly, "guest_inbox"),
    Wishlists(C0880R.C0882id.nav_wishlists, NavigationModeType.GuestOnly, "wishlists"),
    Stories(C0880R.C0882id.nav_story, NavigationModeType.GuestOnly, "stories"),
    Listings(C0880R.C0882id.nav_listings, NavigationModeType.HostOnly, "listings"),
    Calendar(C0880R.C0882id.nav_calendar, NavigationModeType.HostOnly, "calendar"),
    Performance(C0880R.C0882id.nav_performance, NavigationModeType.HostOnly, "performance"),
    Account(C0880R.C0882id.nav_account, NavigationModeType.Shared, "account"),
    TripHostCalendar(C0880R.C0882id.nav_trip_host_calendar, NavigationModeType.TripHostOnly, "trip_host_calendar"),
    TripHostInbox(C0880R.C0882id.nav_trip_host_inbox, NavigationModeType.TripHostOnly, "trip_host_inbox"),
    TripHostExperiences(C0880R.C0882id.nav_trip_host_experiences, NavigationModeType.TripHostOnly, "trip_host_experiences"),
    TripHostStats(C0880R.C0882id.nav_trip_host_stats, NavigationModeType.TripHostOnly, "trip_host_stats"),
    HostInbox(C0880R.C0882id.nav_inbox_host, NavigationModeType.HostOnly, "host_inbox");
    
    public final String appTab;
    public final int itemId;
    public final NavigationModeType modeType;

    private NavigationSection(int itemId2, NavigationModeType modeType2, String appTab2) {
        this.itemId = itemId2;
        this.modeType = modeType2;
        this.appTab = appTab2;
    }

    static NavigationSection findByItemId(int itemId2) {
        return (NavigationSection) FluentIterable.m1283of(values()).filter(NavigationSection$$Lambda$1.lambdaFactory$(itemId2)).first().orNull();
    }

    static NavigationSection findByAppTab(String appTab2) {
        if (TextUtils.isEmpty(appTab2)) {
            return null;
        }
        return (NavigationSection) FluentIterable.m1283of(values()).filter(NavigationSection$$Lambda$2.lambdaFactory$(appTab2)).first().orNull();
    }
}
