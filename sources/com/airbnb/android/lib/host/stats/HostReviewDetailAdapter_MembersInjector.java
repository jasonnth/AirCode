package com.airbnb.android.lib.host.stats;

import com.airbnb.android.core.host.stats.HostStatsJitneyLogger;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class HostReviewDetailAdapter_MembersInjector implements MembersInjector<HostReviewDetailAdapter> {
    static final /* synthetic */ boolean $assertionsDisabled = (!HostReviewDetailAdapter_MembersInjector.class.desiredAssertionStatus());
    private final Provider<HostStatsJitneyLogger> statsJitneyLoggerProvider;

    public HostReviewDetailAdapter_MembersInjector(Provider<HostStatsJitneyLogger> statsJitneyLoggerProvider2) {
        if ($assertionsDisabled || statsJitneyLoggerProvider2 != null) {
            this.statsJitneyLoggerProvider = statsJitneyLoggerProvider2;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<HostReviewDetailAdapter> create(Provider<HostStatsJitneyLogger> statsJitneyLoggerProvider2) {
        return new HostReviewDetailAdapter_MembersInjector(statsJitneyLoggerProvider2);
    }

    public void injectMembers(HostReviewDetailAdapter instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.statsJitneyLogger = (HostStatsJitneyLogger) this.statsJitneyLoggerProvider.get();
    }

    public static void injectStatsJitneyLogger(HostReviewDetailAdapter instance, Provider<HostStatsJitneyLogger> statsJitneyLoggerProvider2) {
        instance.statsJitneyLogger = (HostStatsJitneyLogger) statsJitneyLoggerProvider2.get();
    }
}
