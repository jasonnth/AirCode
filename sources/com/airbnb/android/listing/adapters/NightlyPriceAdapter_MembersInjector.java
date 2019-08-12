package com.airbnb.android.listing.adapters;

import com.airbnb.android.core.LoggingContextFactory;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class NightlyPriceAdapter_MembersInjector implements MembersInjector<NightlyPriceAdapter> {
    static final /* synthetic */ boolean $assertionsDisabled = (!NightlyPriceAdapter_MembersInjector.class.desiredAssertionStatus());
    private final Provider<LoggingContextFactory> loggingContextFactoryProvider;

    public NightlyPriceAdapter_MembersInjector(Provider<LoggingContextFactory> loggingContextFactoryProvider2) {
        if ($assertionsDisabled || loggingContextFactoryProvider2 != null) {
            this.loggingContextFactoryProvider = loggingContextFactoryProvider2;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<NightlyPriceAdapter> create(Provider<LoggingContextFactory> loggingContextFactoryProvider2) {
        return new NightlyPriceAdapter_MembersInjector(loggingContextFactoryProvider2);
    }

    public void injectMembers(NightlyPriceAdapter instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.loggingContextFactory = (LoggingContextFactory) this.loggingContextFactoryProvider.get();
    }

    public static void injectLoggingContextFactory(NightlyPriceAdapter instance, Provider<LoggingContextFactory> loggingContextFactoryProvider2) {
        instance.loggingContextFactory = (LoggingContextFactory) loggingContextFactoryProvider2.get();
    }
}
