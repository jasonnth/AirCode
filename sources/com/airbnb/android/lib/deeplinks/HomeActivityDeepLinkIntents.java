package com.airbnb.android.lib.deeplinks;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.airbnb.android.core.deeplinks.HomeActivityIntents;

public class HomeActivityDeepLinkIntents {
    public static Intent newIntent(Context context) {
        return HomeActivityIntents.intentForDefaultTab(context);
    }

    public static Intent intentForGuestHome(Context context) {
        return HomeActivityIntents.intentForGuestHome(context);
    }

    public static Intent intentForTrips(Context context) {
        return HomeActivityIntents.intentForTrips(context);
    }

    public static Intent intentForWishListIndex(Context context) {
        return HomeActivityIntents.intentForWishListIndex(context);
    }

    public static Intent intentForStoryFeed(Context context) {
        return HomeActivityIntents.intentForStoryFeed(context);
    }

    public static Intent intentForHostHome(Context context) {
        return HomeActivityIntents.intentForHostHome(context);
    }

    public static Intent intentForTripTemplate(Context context) {
        return HomeActivityIntents.intentForTripTemplate(context);
    }

    public static Intent intentForTripHostInbox(Context context) {
        return HomeActivityIntents.intentForTripHostInbox(context);
    }

    public static Intent intentForExperienceHostCalendar(Context context) {
        return HomeActivityIntents.intentForTripHostCalendar(context);
    }

    public static Intent intentForTripHostExperiences(Context context) {
        return HomeActivityIntents.intentForTripHostExperiences(context);
    }

    public static Intent intentForTripHostStats(Context context) {
        return HomeActivityIntents.intentForTripHostStats(context);
    }

    public static Intent intentForCalendar(Context context) {
        return HomeActivityIntents.intentForCalendar(context);
    }

    public static Intent intentForPerformance(Context context) {
        return HomeActivityIntents.intentForPerformance(context);
    }

    public static Intent intentForTripHostScheduledTrip(Context context, Bundle parameters) {
        return HomeActivityIntents.intentForTripHostScheduledTrip(context, Long.valueOf(parameters.getString("id")).longValue());
    }

    public static Intent intentForCityHostsReview(Context context, Bundle parameters) {
        return HomeActivityIntents.intentForTripHostReview(context, Long.valueOf(parameters.getString("id")).longValue());
    }

    public static Intent intentForIdentity(Context context) {
        return HomeActivityIntents.intentForIdentity(context);
    }
}
