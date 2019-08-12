package com.airbnb.android.core.analytics;

import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.adapters.find.C5809SearchInputType;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.GuestDetails;
import com.airbnb.android.utils.Strap;

public class FindTweenAnalytics extends BaseAnalytics {
    public static final String ADULTS = "adults";
    private static final String CHECK_IN = "check_in";
    private static final String CHECK_OUT = "check_out";
    public static final String CHILDREN = "children";
    private static final String DATES = "dates";
    private static final String EVENT_NAME = "p2";
    public static final String GUESTS = "guests";
    public static final String INFANTS = "infants";
    public static final String PETS = "pets";
    public static final String SEARCH_FILTERS = "search_results_filters";
    private static final String SEARCH_INPUT = "search_input";
    private static final String SEARCH_SUGGESTIONS_PAGE = "search_suggestions";
    public static final String SEARCH_TWEEN = "search_tween";
    private static final String SEARCH_TYPE = "search_type";
    public static final String SEARCH_TYPE_ANYWHERE = "anywhere";
    public static final String SEARCH_TYPE_AUTOCOMPLETE_SUGGESTION = "autocomplete_suggestion";
    public static final String SEARCH_TYPE_CURRENT_CITY = "current_city";
    public static final String SEARCH_TYPE_CURRENT_LOCATION = "current_location";
    public static final String SEARCH_TYPE_MANUAL = "manual";
    public static final String SEARCH_TYPE_POPULAR_DESTINATION = "popular_destination";
    public static final String SEARCH_TYPE_SAVED_SEARCH = "saved_search";

    public static void trackOnSearch(C5809SearchInputType searchType, String searchInput) {
        track(Strap.make().mo11639kv("page", SEARCH_SUGGESTIONS_PAGE).mo11639kv(BaseAnalytics.SECTION, SEARCH_INPUT).mo11639kv(BaseAnalytics.OPERATION, "type_in").mo11639kv(SEARCH_TYPE, searchType.loggingString).mo11639kv(SEARCH_INPUT, searchInput));
    }

    public static void trackSaveGuests(NavigationTag tag, GuestDetails guestsData) {
        track(Strap.make().mo11639kv("page", NavigationTag.FindGuestSheet.trackingName).mo11639kv(BaseAnalytics.SECTION, GUESTS).mo11639kv(BaseAnalytics.OPERATION, BaseAnalytics.SELECT).mo11639kv(BaseAnalytics.FROM, tag.trackingName).mo11637kv(GUESTS, guestsData.adultsCount()).mo11637kv(ADULTS, guestsData.adultsCount()).mo11637kv(CHILDREN, guestsData.childrenCount()).mo11637kv(INFANTS, guestsData.infantsCount()).mo11640kv(PETS, guestsData.isBringingPets()));
    }

    public static void trackSaveDates(NavigationTag tag, AirDate checkin, AirDate checkout) {
        track(Strap.make().mo11639kv("page", NavigationTag.FindDatepicker.trackingName).mo11639kv(BaseAnalytics.SECTION, "dates").mo11639kv(BaseAnalytics.OPERATION, BaseAnalytics.SELECT).mo11639kv(BaseAnalytics.FROM, tag.trackingName).mo11639kv("check_in", formatAirDateForLogging(checkin)).mo11639kv("check_out", formatAirDateForLogging(checkout)));
    }

    private static void track(Strap params) {
        AirbnbEventLogger.track("p2", params);
    }
}
