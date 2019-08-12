package com.airbnb.android.core.analytics;

import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.responses.OfficialIdStatusResponse;
import com.airbnb.android.core.utils.SearchUtil;
import com.airbnb.android.utils.Strap;
import com.facebook.share.internal.ShareConstants;

public class BookItAnalytics {
    private static final String BOOK_IT = "BOOK_IT";
    private static final String BOOK_IT_EVENT = "book_it";
    private static final String CLICK = "CLICK";
    private static final String COUPON = "COUPON";
    private static final String DATE_SELECT = "DATE_SELECT";
    private static final String DELETE = "DELETE";
    private static final String FAILED = "FAIL";
    private static final String GENERAL = "GENERAL";
    private static final String GUEST_SELECT = "GUEST_SELECT";
    private static final String INQUIRY = "INQUIRY";
    public static final String PARAM_CHECKIN_DATE = "CHECKIN_DATE";
    public static final String PARAM_CHECKOUT_DATE = "CHECKOUT_DATE";
    public static final String PARAM_GUEST_COUNT = "GUEST_COUNT";
    private static final String PARAM_LISTING_ID = "listing_id";
    private static final String PARAM_MESSAGE = "MESSAGE";
    private static final String PARAM_MESSAGE_LENGTH = "MESSAGE_LENGTH";
    private static final String PRICE_BREAKDOWN = "PRICE_BREAKDOWN";
    private static final String PRICE_UPDATE = "PRICE_UPDATE";
    private static final String SEND_BUTTON = "SEND_BUTTON";
    private static final String SENT = "SENT";
    private static final String VIEW = "VIEW";

    public enum Flow {
        BOOK_IT,
        INQUIRY,
        SEARCH_FILTER,
        P3
    }

    public static Strap makeInquiryAnalyticsParams(long listingId, AirDate checkIn, AirDate checkOut, Integer guestCount, String message) {
        String num;
        Strap kv = Strap.make().mo11638kv("listing_id", listingId).mo11639kv(PARAM_CHECKIN_DATE, checkIn.getIsoDateString()).mo11639kv(PARAM_CHECKOUT_DATE, checkOut.getIsoDateString()).mo11639kv(PARAM_MESSAGE, message).mo11639kv(PARAM_MESSAGE_LENGTH, Integer.toString(message.length()));
        String str = PARAM_GUEST_COUNT;
        if (guestCount == null) {
            num = null;
        } else {
            num = guestCount.toString();
        }
        return kv.mo11639kv(str, num);
    }

    public static void trackBookItGeneralPriceUpdate(Strap additionalParams) {
        AirbnbEventLogger.track(BOOK_IT_EVENT, makeBookItParams(BOOK_IT, GENERAL, PRICE_UPDATE, additionalParams));
    }

    public static void trackBookItDateSelectClick(Strap additionalParams) {
        AirbnbEventLogger.track(BOOK_IT_EVENT, makeBookItParams(BOOK_IT, DATE_SELECT, CLICK, additionalParams));
    }

    public static void trackBookItPriceBreakdownClick(Strap additionalParams) {
        AirbnbEventLogger.track(BOOK_IT_EVENT, makeBookItParams(BOOK_IT, PRICE_BREAKDOWN, CLICK, additionalParams));
    }

    public static void trackBookItGuestSelectClick(Strap additionalParams) {
        AirbnbEventLogger.track(BOOK_IT_EVENT, makeBookItParams(BOOK_IT, GUEST_SELECT, CLICK, additionalParams));
    }

    public static void trackBookItCouponClick(Strap additionalParams) {
        AirbnbEventLogger.track(BOOK_IT_EVENT, makeBookItParams(BOOK_IT, COUPON, CLICK, additionalParams));
    }

    public static void trackBookItCouponDelete(Strap additionalParams) {
        AirbnbEventLogger.track(BOOK_IT_EVENT, makeBookItParams(BOOK_IT, COUPON, DELETE, additionalParams));
    }

    public static void trackBookItError(Strap additionalParams) {
        AirbnbEventLogger.track(BOOK_IT_EVENT, makeBookItParams(BOOK_IT, OfficialIdStatusResponse.ERROR, VIEW, additionalParams));
    }

    private static void trackInquiryAction(String section, String action, Strap additionalParams) {
        AirbnbEventLogger.track(BOOK_IT_EVENT, makeBookItParams(INQUIRY, section, action, additionalParams));
    }

    public static void trackInquiryDateSelectClick(Strap additionalParams) {
        trackInquiryAction(DATE_SELECT, CLICK, additionalParams);
    }

    public static void trackInquiryGuestSelectClick(Strap additionalParams) {
        trackInquiryAction(GUEST_SELECT, CLICK, additionalParams);
    }

    public static void trackInquirySendButtonClick(Strap additionalParams) {
        trackInquiryAction(SEND_BUTTON, CLICK, additionalParams);
    }

    public static void trackInquirySent(long threadId, long postId, Strap additionalParams) {
        trackInquiryAction(SEND_BUTTON, SENT, Strap.make().mo11638kv("THREAD_ID", threadId).mo11638kv("THREAD_POST_ID", postId).mix(additionalParams));
    }

    public static void trackInquiryFailed(Strap additionalParams) {
        trackInquiryAction(SEND_BUTTON, FAILED, additionalParams);
    }

    private static Strap makeBookItParams(String page, String section, String action, Strap additionalParams) {
        Strap params = Strap.make().mo11639kv("PAGE", page).mo11639kv("SECTION", section).mo11639kv(ShareConstants.ACTION, action);
        SearchUtil.addUniqueSearchIdIfPresent(params);
        if (additionalParams != null) {
            for (String key : additionalParams.keySet()) {
                if (additionalParams.get(key) != null) {
                    params.mo11639kv(key, (String) additionalParams.get(key));
                }
            }
        }
        return params;
    }
}
