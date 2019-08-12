package com.airbnb.android.core.intents;

import android.content.Context;
import android.content.Intent;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.Activities;

public class HostCalendarIntents {

    public interface ArgumentConstants {
        public static final String ARG_APPLIED_PRICE_TIPS = "applied_price_tips";
        public static final String ARG_CALENDAR_DAYS = "calendar_days";
        public static final String ARG_CALENDAR_RULE = "calendar_rule";
        public static final String ARG_FROM_INSIGHTS = "from_insights";
        public static final String ARG_LISTING_ID = "listing_id";
        public static final String ARG_LISTING_NAME = "listing_name";
        public static final String ARG_NOTES = "notes";
        public static final String ARG_TAB_TYPE = "tab_type";
        public static final String ARG_TARGET_END_DATE = "target_end_date";
        public static final String ARG_TARGET_START_DATE = "target_start_date";
    }

    public enum CalendarUpdateAction {
        Notes,
        UpdatePrice,
        NestedListing,
        MultiDayPriceTips
    }

    private HostCalendarIntents() {
    }

    public static Intent intentForSingleListingCalendar(Context context, long listingId, String listingName) {
        return new Intent(context, Activities.hostSingleCalendar()).putExtra("listing_id", listingId).putExtra("listing_name", listingName);
    }

    public static Intent intentForSingleListingCalendarWithDates(Context context, long listingId, String listingName, AirDate targetStartDate, AirDate targetEndDate) {
        return new Intent(context, Activities.hostSingleCalendar()).putExtra("listing_id", listingId).putExtra("listing_name", listingName).putExtra("target_start_date", targetStartDate).putExtra("target_end_date", targetEndDate);
    }

    public static Intent intentForSingleListingCalendarDeepLink(Context context, long listingId) {
        return new Intent(context, Activities.hostSingleCalendar()).putExtra("listing_id", listingId);
    }
}
