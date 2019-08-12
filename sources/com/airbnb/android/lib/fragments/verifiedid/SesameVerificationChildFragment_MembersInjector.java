package com.airbnb.android.lib.fragments.verifiedid;

import com.airbnb.android.core.analytics.VerifiedIdAnalytics;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class SesameVerificationChildFragment_MembersInjector implements MembersInjector<SesameVerificationChildFragment> {
    static final /* synthetic */ boolean $assertionsDisabled = (!SesameVerificationChildFragment_MembersInjector.class.desiredAssertionStatus());
    private final Provider<VerifiedIdAnalytics> verifiedIdAnalyticsProvider;

    public SesameVerificationChildFragment_MembersInjector(Provider<VerifiedIdAnalytics> verifiedIdAnalyticsProvider2) {
        if ($assertionsDisabled || verifiedIdAnalyticsProvider2 != null) {
            this.verifiedIdAnalyticsProvider = verifiedIdAnalyticsProvider2;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<SesameVerificationChildFragment> create(Provider<VerifiedIdAnalytics> verifiedIdAnalyticsProvider2) {
        return new SesameVerificationChildFragment_MembersInjector(verifiedIdAnalyticsProvider2);
    }

    public void injectMembers(SesameVerificationChildFragment instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.verifiedIdAnalytics = (VerifiedIdAnalytics) this.verifiedIdAnalyticsProvider.get();
    }

    public static void injectVerifiedIdAnalytics(SesameVerificationChildFragment instance, Provider<VerifiedIdAnalytics> verifiedIdAnalyticsProvider2) {
        instance.verifiedIdAnalytics = (VerifiedIdAnalytics) verifiedIdAnalyticsProvider2.get();
    }
}
