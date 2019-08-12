package com.airbnb.android.places;

import com.airbnb.android.core.LoggingContextFactory;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class ResyController_MembersInjector implements MembersInjector<ResyController> {
    static final /* synthetic */ boolean $assertionsDisabled = (!ResyController_MembersInjector.class.desiredAssertionStatus());
    private final Provider<LoggingContextFactory> loggingContextFactoryProvider;

    public ResyController_MembersInjector(Provider<LoggingContextFactory> loggingContextFactoryProvider2) {
        if ($assertionsDisabled || loggingContextFactoryProvider2 != null) {
            this.loggingContextFactoryProvider = loggingContextFactoryProvider2;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<ResyController> create(Provider<LoggingContextFactory> loggingContextFactoryProvider2) {
        return new ResyController_MembersInjector(loggingContextFactoryProvider2);
    }

    public void injectMembers(ResyController instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.loggingContextFactory = (LoggingContextFactory) this.loggingContextFactoryProvider.get();
    }

    public static void injectLoggingContextFactory(ResyController instance, Provider<LoggingContextFactory> loggingContextFactoryProvider2) {
        instance.loggingContextFactory = (LoggingContextFactory) loggingContextFactoryProvider2.get();
    }
}
