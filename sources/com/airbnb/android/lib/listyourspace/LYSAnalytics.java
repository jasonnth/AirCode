package com.airbnb.android.lib.listyourspace;

import android.text.TextUtils;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.analytics.ManageListingAnalytics;
import com.airbnb.android.utils.Strap;
import com.facebook.places.model.PlaceFields;
import java.util.UUID;

public final class LYSAnalytics {
    private static final String DEVICE_SESSION_TAG = "host_onboarding_device_session";
    private static final String HOST_ONBOARDING = "host_onboarding";

    private LYSAnalytics() {
    }

    @Deprecated
    public static void trackGenericAction(String... args) {
        int paramsSize = args.length + 2;
        String[] params = new String[paramsSize];
        params[0] = HOST_ONBOARDING;
        System.arraycopy(args, 0, params, 1, args.length);
        params[paramsSize - 1] = getDeviceSession();
        AirbnbEventLogger.track(params);
    }

    public static void trackGenericAction(Strap args) {
        args.mo11639kv("page", HOST_ONBOARDING).mo11639kv("device_session", getDeviceSession());
        AirbnbEventLogger.track(HOST_ONBOARDING, args);
    }

    @Deprecated
    public static void trackLysImpression(String section) {
        AirbnbEventLogger.track(HOST_ONBOARDING, "impressions", "lys", section, getDeviceSession());
    }

    @Deprecated
    public static void trackLysAction(String... args) {
        int paramsSize = args.length + 3;
        String[] params = new String[paramsSize];
        params[0] = HOST_ONBOARDING;
        params[1] = "lys";
        System.arraycopy(args, 0, params, 2, args.length);
        params[paramsSize - 1] = getDeviceSession();
        AirbnbEventLogger.track(params);
    }

    @Deprecated
    public static void trackPageImpression(Listing listing, String section) {
        AirbnbEventLogger.track(HOST_ONBOARDING, "impressions", getPrePostTagSafe(listing), section, Long.toString(listing.getId()), getDeviceSession());
    }

    @Deprecated
    public static void trackPageAction(Listing listing, String... args) {
        int paramsSize = args.length + 4;
        String[] params = new String[paramsSize];
        params[0] = HOST_ONBOARDING;
        params[1] = getPrePostTagSafe(listing);
        System.arraycopy(args, 0, params, 2, args.length);
        params[paramsSize - 2] = Long.toString(listing.getId());
        params[paramsSize - 1] = getDeviceSession();
        AirbnbEventLogger.track(params);
    }

    public static void trackAction(String page, String action, Strap extras) {
        AirbnbEventLogger.track(HOST_ONBOARDING, getBaseParams(page, action).mix(extras));
    }

    public static void trackAction(Listing listing, String page, String action, Strap extras) {
        AirbnbEventLogger.track(HOST_ONBOARDING, getBaseParams(page, action).mo11639kv("listing_id", Long.toString(Long.valueOf(listing == null ? -1 : listing.getId()).longValue())).mo11639kv("has_been_listed", getPrePostTagSafe(listing)).mix(extras));
    }

    public static void resetDeviceSession() {
        AirbnbApplication.instance().component().analyticsRegistry().getRegistry().mo11639kv(DEVICE_SESSION_TAG, (String) null);
    }

    private static String getDeviceSession() {
        Strap analyticsRegistry = AirbnbApplication.instance().component().analyticsRegistry().getRegistry();
        String deviceSession = analyticsRegistry.getRawString(DEVICE_SESSION_TAG);
        if (!TextUtils.isEmpty(deviceSession)) {
            return deviceSession;
        }
        String deviceSession2 = UUID.randomUUID().toString();
        analyticsRegistry.mo11639kv(DEVICE_SESSION_TAG, deviceSession2);
        return deviceSession2;
    }

    private static Strap getBaseParams(String page, String action) {
        return Strap.make().mo11639kv("page", page).mo11639kv("action", action).mo11639kv("device_session", getDeviceSession()).mo11639kv("log_version", "2");
    }

    private static String getPrePostTagSafe(Listing listing) {
        if (listing == null) {
            return ManageListingAnalytics.PRE_LIST;
        }
        return listing.hasBeenListed() ? ManageListingAnalytics.POST_LIST : ManageListingAnalytics.PRE_LIST;
    }

    public static void trackPhotoAction(Listing listing, String action) {
        trackPhotoAction(listing.getId(), action);
    }

    public static void trackPhotoAction(long listingId, String action) {
        AirbnbEventLogger.track(HOST_ONBOARDING, getBaseParams(PlaceFields.PHOTOS_PROFILE, action).mo11638kv("listing_id", listingId));
    }

    public static Strap extrasForLYSFlowStart() {
        return Strap.make().mo11639kv("datadog_key", "mobile_lys.start_flow").mo11639kv("datadog_tags", "client:android");
    }

    public static Strap extrasForPublishListing(Listing listing) {
        Strap strap = Strap.make().mo11639kv("datadog_key", "mobile_lys.publish_listing_button.click").mo11639kv("datadog_tags", "client:android");
        if (listing != null) {
            strap.mo11638kv("listing_id", listing.getId());
        }
        return strap;
    }

    public static Strap extrasForErrorsCreateRawListing() {
        return Strap.make().mo11639kv("datadog_key", "mobile_lys.error.create").mo11639kv("datadog_tags", "client:android");
    }

    public static Strap extrasForErrorsUpdateListing(Listing listing) {
        Strap strap = Strap.make().mo11639kv("datadog_key", "mobile_lys.error.update").mo11639kv("datadog_tags", "client:android");
        if (listing != null) {
            strap.mo11638kv("listing_id", listing.getId());
        }
        return strap;
    }
}
