package com.airbnb.android.lib.fragments.verifiedid;

import com.airbnb.android.core.analytics.VerifiedIdAnalytics;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class OfflineIdChildFragment_MembersInjector implements MembersInjector<OfflineIdChildFragment> {
    static final /* synthetic */ boolean $assertionsDisabled = (!OfflineIdChildFragment_MembersInjector.class.desiredAssertionStatus());
    private final Provider<VerifiedIdAnalytics> verifiedIdAnalyticsProvider;

    public OfflineIdChildFragment_MembersInjector(Provider<VerifiedIdAnalytics> verifiedIdAnalyticsProvider2) {
        if ($assertionsDisabled || verifiedIdAnalyticsProvider2 != null) {
            this.verifiedIdAnalyticsProvider = verifiedIdAnalyticsProvider2;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<OfflineIdChildFragment> create(Provider<VerifiedIdAnalytics> verifiedIdAnalyticsProvider2) {
        return new OfflineIdChildFragment_MembersInjector(verifiedIdAnalyticsProvider2);
    }

    public void injectMembers(OfflineIdChildFragment instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.verifiedIdAnalytics = (VerifiedIdAnalytics) this.verifiedIdAnalyticsProvider.get();
    }

    public static void injectVerifiedIdAnalytics(OfflineIdChildFragment instance, Provider<VerifiedIdAnalytics> verifiedIdAnalyticsProvider2) {
        instance.verifiedIdAnalytics = (VerifiedIdAnalytics) verifiedIdAnalyticsProvider2.get();
    }
}
