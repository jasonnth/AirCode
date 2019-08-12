package com.airbnb.android.core.dls;

import com.airbnb.android.core.LoggingContextFactory;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;
import javax.inject.Provider;

public final class DLSJitneyLogger_Factory implements Factory<DLSJitneyLogger> {
    static final /* synthetic */ boolean $assertionsDisabled = (!DLSJitneyLogger_Factory.class.desiredAssertionStatus());
    private final MembersInjector<DLSJitneyLogger> dLSJitneyLoggerMembersInjector;
    private final Provider<LoggingContextFactory> loggingContextFactoryProvider;

    public DLSJitneyLogger_Factory(MembersInjector<DLSJitneyLogger> dLSJitneyLoggerMembersInjector2, Provider<LoggingContextFactory> loggingContextFactoryProvider2) {
        if ($assertionsDisabled || dLSJitneyLoggerMembersInjector2 != null) {
            this.dLSJitneyLoggerMembersInjector = dLSJitneyLoggerMembersInjector2;
            if ($assertionsDisabled || loggingContextFactoryProvider2 != null) {
                this.loggingContextFactoryProvider = loggingContextFactoryProvider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public DLSJitneyLogger get() {
        return (DLSJitneyLogger) MembersInjectors.injectMembers(this.dLSJitneyLoggerMembersInjector, new DLSJitneyLogger((LoggingContextFactory) this.loggingContextFactoryProvider.get()));
    }

    public static Factory<DLSJitneyLogger> create(MembersInjector<DLSJitneyLogger> dLSJitneyLoggerMembersInjector2, Provider<LoggingContextFactory> loggingContextFactoryProvider2) {
        return new DLSJitneyLogger_Factory(dLSJitneyLoggerMembersInjector2, loggingContextFactoryProvider2);
    }
}
