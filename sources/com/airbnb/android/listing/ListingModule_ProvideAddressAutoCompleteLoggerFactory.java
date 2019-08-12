package com.airbnb.android.listing;

import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.listing.logging.LYSAddressAutoCompleteLogger;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ListingModule_ProvideAddressAutoCompleteLoggerFactory implements Factory<LYSAddressAutoCompleteLogger> {
    static final /* synthetic */ boolean $assertionsDisabled = (!ListingModule_ProvideAddressAutoCompleteLoggerFactory.class.desiredAssertionStatus());
    private final Provider<LoggingContextFactory> loggingContextFactoryProvider;

    public ListingModule_ProvideAddressAutoCompleteLoggerFactory(Provider<LoggingContextFactory> loggingContextFactoryProvider2) {
        if ($assertionsDisabled || loggingContextFactoryProvider2 != null) {
            this.loggingContextFactoryProvider = loggingContextFactoryProvider2;
            return;
        }
        throw new AssertionError();
    }

    public LYSAddressAutoCompleteLogger get() {
        return (LYSAddressAutoCompleteLogger) Preconditions.checkNotNull(ListingModule.provideAddressAutoCompleteLogger((LoggingContextFactory) this.loggingContextFactoryProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<LYSAddressAutoCompleteLogger> create(Provider<LoggingContextFactory> loggingContextFactoryProvider2) {
        return new ListingModule_ProvideAddressAutoCompleteLoggerFactory(loggingContextFactoryProvider2);
    }
}
