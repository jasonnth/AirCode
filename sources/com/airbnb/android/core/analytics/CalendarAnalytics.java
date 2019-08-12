package com.airbnb.android.core.analytics;

import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.models.CalendarDay;
import com.airbnb.android.lib.fragments.SeasonalCalendarRuleRangeSelectorDialog;
import com.airbnb.android.utils.Strap;
import java.util.Iterator;
import java.util.List;

public class CalendarAnalytics extends BaseAnalytics {
    private static final String DBP_INTERSTITIAL = "demand_based_pricing_interstitial";
    private static final String EVENT_NAME = "calendar";
    public static final String LISTING_ID = "listing_id";
    private static final String MANAGE_LISTING_CALENDAR = "manage_listing_calendar";

    public static void trackSelectionUpdated(List<CalendarDay> selectedDays) {
        Strap strap = Strap.make();
        addStartAndEndDates(strap, selectedDays);
        track("selected_dates_updated", strap);
    }

    public static void trackChangePriceAndAvailability(long listingId, Integer price, boolean availableSelected, List<CalendarDay> updatedDays) {
        Strap strap = Strap.make().mo11638kv("listing_id", listingId);
        addStartAndEndDates(strap, updatedDays);
        boolean priceChanged = price != null && availableSelected;
        boolean availabilityChanged = false;
        Iterator it = updatedDays.iterator();
        while (true) {
            if (it.hasNext()) {
                if (((CalendarDay) it.next()).isAvailable() != availableSelected) {
                    availabilityChanged = true;
                    break;
                }
            } else {
                break;
            }
        }
        String action = "none";
        if (priceChanged) {
            action = "change_price";
            strap.mo11637kv("new_price", price.intValue());
        }
        if (availabilityChanged) {
            action = "change_availability";
            strap.mo11640kv("new_availability", availableSelected);
        }
        if (priceChanged && availabilityChanged) {
            action = "change_price_and_availability";
        }
        track(action, strap);
    }

    public static void trackChangeSuccess(long listingId) {
        track("change_price_and_availability_success", Strap.make().mo11638kv("listing_id", listingId));
    }

    public static void trackChangeFail(long listingId, String message) {
        track("change_price_and_availability_fail", Strap.make().mo11638kv("listing_id", listingId).mo11639kv("message", message));
    }

    private static void addStartAndEndDates(Strap strap, List<CalendarDay> days) {
        if (days != null && days.size() > 0) {
            strap.put(SeasonalCalendarRuleRangeSelectorDialog.ARG_RESULT_START_DATE, getDateStr((CalendarDay) days.get(0)));
            strap.put(SeasonalCalendarRuleRangeSelectorDialog.ARG_RESULT_END_DATE, getDateStr((CalendarDay) days.get(days.size() - 1)));
        }
    }

    private static String getDateStr(CalendarDay calendarDay) {
        return calendarDay.getDate().getIsoDateString();
    }

    private static void track(String action, Strap strap) {
        AirbnbEventLogger.track(EVENT_NAME, strap.mo11639kv("action", action));
    }

    public static void trackSmartPricingFtueImpression(long listingId) {
        AirbnbEventLogger.track(EVENT_NAME, Strap.make().mo11639kv("page", MANAGE_LISTING_CALENDAR).mo11639kv(BaseAnalytics.SECTION, DBP_INTERSTITIAL).mo11639kv(BaseAnalytics.OPERATION, "impression").mo11638kv("listing_id", listingId));
    }

    public static void trackSmartPricingFtueClick(long listingId) {
        AirbnbEventLogger.track(EVENT_NAME, Strap.make().mo11639kv("page", MANAGE_LISTING_CALENDAR).mo11639kv(BaseAnalytics.SECTION, DBP_INTERSTITIAL).mo11639kv(BaseAnalytics.OPERATION, "click").mo11639kv(BaseAnalytics.TARGET, "try_it_out").mo11638kv("listing_id", listingId));
    }

    public static void trackAcceptPriceSuggestion(AirDate date, int price) {
        AirbnbEventLogger.track(EVENT_NAME, Strap.make().mo11639kv("page", EVENT_NAME).mo11639kv(BaseAnalytics.SECTION, "CalendarEditActions").mo11639kv(BaseAnalytics.OPERATION, "acceptPriceSuggestion").mo11639kv("date", date.getIsoDateString()).mo11637kv("dailyPrice", price).mo11637kv("suggestedPrice", price));
    }
}
