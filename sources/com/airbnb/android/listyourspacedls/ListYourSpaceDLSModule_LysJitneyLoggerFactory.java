package com.airbnb.android.listyourspacedls;

import com.airbnb.android.core.LoggingContextFactory;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ListYourSpaceDLSModule_LysJitneyLoggerFactory implements Factory<LYSJitneyLogger> {
    static final /* synthetic */ boolean $assertionsDisabled = (!ListYourSpaceDLSModule_LysJitneyLoggerFactory.class.desiredAssertionStatus());
    private final Provider<LoggingContextFactory> loggingContextFactoryProvider;

    public ListYourSpaceDLSModule_LysJitneyLoggerFactory(Provider<LoggingContextFactory> loggingContextFactoryProvider2) {
        if ($assertionsDisabled || loggingContextFactoryProvider2 != null) {
            this.loggingContextFactoryProvider = loggingContextFactoryProvider2;
            return;
        }
        throw new AssertionError();
    }

    public LYSJitneyLogger get() {
        return (LYSJitneyLogger) Preconditions.checkNotNull(ListYourSpaceDLSModule.lysJitneyLogger((LoggingContextFactory) this.loggingContextFactoryProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<LYSJitneyLogger> create(Provider<LoggingContextFactory> loggingContextFactoryProvider2) {
        return new ListYourSpaceDLSModule_LysJitneyLoggerFactory(loggingContextFactoryProvider2);
    }
}
