package com.airbnb.android.referrals.analytics;

import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.utils.Strap;

public class ReferralsAnalytics {
    private static final String ENTRY_POINT = "entry_point";
    private static final String NAVIGATION = "navigation";
    private static final String REFERRALS = "referrals";
    private static final String REFERRALS_HOME = "referrals_home";

    public static void trackLandingPageImpression(String entryPoint) {
        track(NAVIGATION, new Strap().mo11639kv(BaseAnalytics.OPERATION, "impression").mo11639kv("page", REFERRALS_HOME).mo11639kv("entry_point", entryPoint));
    }

    public static void trackTermsClick() {
        trackForReferralsHome(new Strap().mo11639kv(BaseAnalytics.OPERATION, "click").mo11639kv(BaseAnalytics.TARGET, "terms_and_conditions"));
    }

    public static void trackShareLinkClick() {
        trackForReferralsHome(new Strap().mo11639kv(BaseAnalytics.OPERATION, "click").mo11639kv(BaseAnalytics.TARGET, "share_link"));
    }

    public static void trackPastInvitesClick() {
        trackForReferralsHome(new Strap().mo11639kv(BaseAnalytics.OPERATION, "click").mo11639kv(BaseAnalytics.TARGET, "past_invites"));
    }

    public static void trackPastInvitesImpression() {
        track(new Strap().mo11639kv(BaseAnalytics.OPERATION, "impression").mo11639kv("page", "referrals_history"));
    }

    private static void trackForReferralsHome(Strap strap) {
        track(strap.mo11639kv("page", REFERRALS_HOME));
    }

    private static void track(Strap strap) {
        track("referrals", strap);
    }

    private static void track(String eventName, Strap strap) {
        AirbnbEventLogger.track(eventName, strap);
    }
}
