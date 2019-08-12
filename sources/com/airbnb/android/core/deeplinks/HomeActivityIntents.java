package com.airbnb.android.core.deeplinks;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.airbnb.android.core.Activities;
import com.airbnb.android.core.intents.FixItIntents;
import com.airbnb.android.core.utils.Check;

public final class HomeActivityIntents {
    public static final String ACTION_UNHANDLED_DEEPLINK = "action_unhandled_deeplink";
    public static final String ARG_RN_PARAMS_BUNDLE = "rn_params_bundle";
    public static final String ARG_SUPER_HERO_BUNDLE = "super_hero_bundle";
    public static final String ARG_SUPER_HERO_ID = "super_hero_id";
    public static final String ARG_TRIP_HOST_REVIEWABLE_INSTANCE_ID = "selectedTripInstanceId";
    public static final String ARG_TRIP_HOST_SCHEDULED_TRIP_ID = "tripInstanceIdFromEntryPoint";
    public static final String ARG_WISH_LIST_ID = "wish_list_id";
    public static final String SHOW_CALENDAR = "show_calendar";
    public static final String SHOW_DEFAULT_TAB = "show_default_tab";
    public static final String SHOW_GUEST_HOME = "show_guest_home";
    public static final String SHOW_HOSTHOME = "show_host_home";
    public static final String SHOW_IDENTITY = "show_identity";
    public static final String SHOW_LISTINGS = "show_listings";
    public static final String SHOW_LISTING_FIX_IT_REPORT = "show_listing_fix_it_report";
    public static final String SHOW_PERFORMANCE = "show_performance";
    public static final String SHOW_SEARCH_LANDING = "show_search_landing";
    public static final String SHOW_STORY_FEED = "show_story_feed";
    public static final String SHOW_SUPER_HERO = "show_super_hero";
    public static final String SHOW_TRAVEL_INBOX = "show_travel_inbox";
    public static final String SHOW_TRIPS = "show_trips";
    public static final String SHOW_TRIP_HOST_CALENDAR = "show_trip_host_calendar";
    public static final String SHOW_TRIP_HOST_EXPERIENCES = "show_trip_host_experiences";
    public static final String SHOW_TRIP_HOST_INBOX = "show_trip_host_inbox";
    public static final String SHOW_TRIP_HOST_SCHEDULED_TRIP = "show_trip_host_scheduled_trip";
    public static final String SHOW_TRIP_HOST_STATS = "show_trip_host_stats";
    public static final String SHOW_TRIP_HOST_TRIP_REVIEW = "show_trip_host_trip_review";
    public static final String SHOW_TRIP_TEMPLATE = "show_trip_template";
    public static final String SHOW_WISH_LIST = "show_wish_list";
    public static final String SHOW_WISH_LIST_INDEX = "show_wish_list_index";

    private static Intent intentForAction(Context context, String action) {
        return new Intent(context, Activities.home()).addFlags(67108864).setAction(action);
    }

    public static Intent intentForUnhandledDeeplink(Context context, String uri) {
        return intentForAction(context, ACTION_UNHANDLED_DEEPLINK).putExtra("is_deep_link_flag", true).putExtra("deep_link_uri", uri).putExtra("com.airbnb.deeplinkdispatch.EXTRA_SUCCESSFUL", false);
    }

    public static Intent intentForDefaultTab(Context context) {
        return intentForAction(context, SHOW_DEFAULT_TAB);
    }

    public static Intent intentForGuestHome(Context context) {
        return intentForAction(context, SHOW_GUEST_HOME);
    }

    public static Intent intentForSearchLanding(Context context) {
        return intentForAction(context, SHOW_SEARCH_LANDING);
    }

    public static Intent intentForTrips(Context context) {
        return intentForAction(context, SHOW_TRIPS);
    }

    public static Intent intentForTrips(Context context, Bundle bundle) {
        return intentForTrips(context).putExtra(ARG_RN_PARAMS_BUNDLE, bundle);
    }

    public static Intent intentForWishListIndex(Context context) {
        return intentForAction(context, SHOW_WISH_LIST_INDEX);
    }

    public static Intent intentForStoryFeed(Context context) {
        return intentForAction(context, SHOW_STORY_FEED);
    }

    public static Intent intentForWishList(Context context, long wishListId) {
        Check.argument(wishListId > 0);
        return intentForAction(context, SHOW_WISH_LIST).putExtra(ARG_WISH_LIST_ID, wishListId);
    }

    public static Intent intentForHostHome(Context context) {
        return intentForAction(context, SHOW_HOSTHOME);
    }

    public static Intent intentForTripTemplate(Context context) {
        return intentForAction(context, SHOW_TRIP_TEMPLATE);
    }

    public static Intent intentForTripHostScheduledTrip(Context context, long id) {
        return intentForAction(context, SHOW_TRIP_HOST_SCHEDULED_TRIP).putExtra(ARG_TRIP_HOST_SCHEDULED_TRIP_ID, id);
    }

    public static Intent intentForTripHostReview(Context context, long id) {
        return intentForAction(context, SHOW_TRIP_HOST_TRIP_REVIEW).putExtra(ARG_TRIP_HOST_REVIEWABLE_INSTANCE_ID, id);
    }

    public static Intent intentForTripHostInbox(Context context) {
        return intentForAction(context, SHOW_TRIP_HOST_INBOX);
    }

    public static Intent intentForTripHostCalendar(Context context) {
        return intentForAction(context, SHOW_TRIP_HOST_CALENDAR);
    }

    public static Intent intentForTripHostExperiences(Context context) {
        return intentForAction(context, SHOW_TRIP_HOST_EXPERIENCES);
    }

    public static Intent intentForTripHostStats(Context context) {
        return intentForAction(context, SHOW_TRIP_HOST_STATS);
    }

    public static Intent intentForListings(Context context) {
        return intentForAction(context, SHOW_LISTINGS);
    }

    public static Intent intentForListingFixItReport(Context context, long reportId, String listingName) {
        return intentForAction(context, SHOW_LISTING_FIX_IT_REPORT).putExtra(FixItIntents.INTENT_EXTRA_REPORT_ID, reportId).putExtra("listing_name", listingName);
    }

    public static Intent intentForSuperHero(Context context, long superHeroId) {
        return intentForAction(context, SHOW_SUPER_HERO).putExtra(ARG_SUPER_HERO_ID, superHeroId);
    }

    public static Intent intentForSuperHero(Context context, Bundle superHeroBundle) {
        return intentForAction(context, SHOW_SUPER_HERO).putExtra(ARG_SUPER_HERO_BUNDLE, superHeroBundle);
    }

    public static Intent intentForTravelInbox(Context context) {
        return intentForAction(context, SHOW_TRAVEL_INBOX);
    }

    public static Intent intentForCalendar(Context context) {
        return intentForAction(context, SHOW_CALENDAR);
    }

    public static Intent intentForPerformance(Context context) {
        return intentForAction(context, SHOW_PERFORMANCE);
    }

    public static Intent intentForIdentity(Context context) {
        return intentForAction(context, SHOW_IDENTITY);
    }
}
