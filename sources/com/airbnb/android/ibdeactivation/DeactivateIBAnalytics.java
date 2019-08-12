package com.airbnb.android.ibdeactivation;

import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.enums.DeactivateIBReason;
import com.airbnb.android.core.requests.constants.ListingRequestConstants;
import com.airbnb.android.lib.cancellation.CancellationAnalytics;
import com.airbnb.android.utils.Strap;

public class DeactivateIBAnalytics extends BaseAnalytics {
    private static final int EMPATHY_PAGE = 2;
    private static final String EVENT_NAME = "deactivation_flow";
    private static final int LOVE_TO_KNOW_MORE_PAGE = 3;
    private static final int SELECT_REASONS_PAGE = 1;
    private static final String TOOLS_LINK = "tools_link";

    private static int getPageName(DeactivateIBReason reason) {
        return reason == null ? 1 : 2;
    }

    public static void trackReasonPageLoad(DeactivateIBReason reason) {
        Strap strap = new Strap().mo11637kv("page", getPageName(reason)).mo11639kv(BaseAnalytics.OPERATION, "impression");
        if (reason != null) {
            strap.mo11637kv(CancellationAnalytics.VALUE_PAGE_REASON, reason.getLoggingConstant());
        }
        AirbnbEventLogger.track(EVENT_NAME, strap);
    }

    public static void trackSubReasonClick(DeactivateIBReason reason) {
        String nativeLink = "";
        switch (reason) {
            case TripLength:
                nativeLink = "trip_length";
                break;
            case PreparationTime:
                nativeLink = "preparation_time";
                break;
            case DistantRequest:
                nativeLink = "distant_request";
                break;
            case UnawareCalendarUpdated:
                nativeLink = "unaware_calendar_updated";
                break;
            case UnawareHouseRules:
                nativeLink = "unaware_house_rules";
                break;
            case HouseRules:
                nativeLink = ListingRequestConstants.JSON_HOUSE_RULES_KEY;
                break;
            case AirbnbRequirements:
                nativeLink = "review_instant_book_requirements";
                break;
            default:
                BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalStateException("Invalid subreason: " + reason.toString()));
                break;
        }
        AirbnbEventLogger.track(EVENT_NAME, new Strap().mo11639kv(BaseAnalytics.OPERATION, "click").mo11637kv("page", 2).mo11639kv("native_link", nativeLink).mo11639kv(BaseAnalytics.SECTION, TOOLS_LINK));
    }

    public static void trackLoveToKnowMore(String idea, Long listingId, Long hostId) {
        AirbnbEventLogger.track(EVENT_NAME, new Strap().mo11639kv(BaseAnalytics.OPERATION, "impression").mo11637kv("page", 3).mo11638kv("listing_id", listingId.longValue()).mo11638kv("host_id", hostId.longValue()).mo11639kv("improvement_ideas", idea));
    }

    public static void trackKeepIBOnClick(Long listingId, Long hostId) {
        AirbnbEventLogger.track(EVENT_NAME, new Strap().mo11638kv("listing_id", listingId.longValue()).mo11638kv("host_id", hostId.longValue()).mo11639kv(BaseAnalytics.SECTION, "keep_ib_on"));
    }

    public static void trackCloseModal(Long listingId, Long hostId) {
        AirbnbEventLogger.track(EVENT_NAME, new Strap().mo11639kv(BaseAnalytics.OPERATION, "close").mo11638kv("listing_id", listingId.longValue()).mo11638kv("host_id", hostId.longValue()).mo11639kv(BaseAnalytics.SECTION, "closed_modal"));
    }
}
