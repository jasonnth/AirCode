package com.airbnb.android.listing;

import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.listing.logging.WhatsMyPlaceWorthLogger;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ListingModule_ProvideWhatsMyPlaceWorthLoggerFactory implements Factory<WhatsMyPlaceWorthLogger> {
    static final /* synthetic */ boolean $assertionsDisabled = (!ListingModule_ProvideWhatsMyPlaceWorthLoggerFactory.class.desiredAssertionStatus());
    private final Provider<LoggingContextFactory> loggingContextFactoryProvider;

    public ListingModule_ProvideWhatsMyPlaceWorthLoggerFactory(Provider<LoggingContextFactory> loggingContextFactoryProvider2) {
        if ($assertionsDisabled || loggingContextFactoryProvider2 != null) {
            this.loggingContextFactoryProvider = loggingContextFactoryProvider2;
            return;
        }
        throw new AssertionError();
    }

    public WhatsMyPlaceWorthLogger get() {
        return (WhatsMyPlaceWorthLogger) Preconditions.checkNotNull(ListingModule.provideWhatsMyPlaceWorthLogger((LoggingContextFactory) this.loggingContextFactoryProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<WhatsMyPlaceWorthLogger> create(Provider<LoggingContextFactory> loggingContextFactoryProvider2) {
        return new ListingModule_ProvideWhatsMyPlaceWorthLoggerFactory(loggingContextFactoryProvider2);
    }
}
