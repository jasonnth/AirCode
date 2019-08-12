package com.airbnb.android.listing.adapters;

import com.airbnb.android.core.LoggingContextFactory;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class BasePriceAdapter_MembersInjector implements MembersInjector<BasePriceAdapter> {
    static final /* synthetic */ boolean $assertionsDisabled = (!BasePriceAdapter_MembersInjector.class.desiredAssertionStatus());
    private final Provider<LoggingContextFactory> loggingContextFactoryProvider;

    public BasePriceAdapter_MembersInjector(Provider<LoggingContextFactory> loggingContextFactoryProvider2) {
        if ($assertionsDisabled || loggingContextFactoryProvider2 != null) {
            this.loggingContextFactoryProvider = loggingContextFactoryProvider2;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<BasePriceAdapter> create(Provider<LoggingContextFactory> loggingContextFactoryProvider2) {
        return new BasePriceAdapter_MembersInjector(loggingContextFactoryProvider2);
    }

    public void injectMembers(BasePriceAdapter instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.loggingContextFactory = (LoggingContextFactory) this.loggingContextFactoryProvider.get();
    }

    public static void injectLoggingContextFactory(BasePriceAdapter instance, Provider<LoggingContextFactory> loggingContextFactoryProvider2) {
        instance.loggingContextFactory = (LoggingContextFactory) loggingContextFactoryProvider2.get();
    }
}
