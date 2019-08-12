package com.airbnb.android.profile_completion;

import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.profile_completion.analytics.ProfileCompletionJitneyLogger;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ProfileCompletionModule_ProfileCompletionJitneyLoggerFactory implements Factory<ProfileCompletionJitneyLogger> {
    static final /* synthetic */ boolean $assertionsDisabled = (!ProfileCompletionModule_ProfileCompletionJitneyLoggerFactory.class.desiredAssertionStatus());
    private final Provider<LoggingContextFactory> loggingContextFactoryProvider;
    private final ProfileCompletionModule module;

    public ProfileCompletionModule_ProfileCompletionJitneyLoggerFactory(ProfileCompletionModule module2, Provider<LoggingContextFactory> loggingContextFactoryProvider2) {
        if ($assertionsDisabled || module2 != null) {
            this.module = module2;
            if ($assertionsDisabled || loggingContextFactoryProvider2 != null) {
                this.loggingContextFactoryProvider = loggingContextFactoryProvider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public ProfileCompletionJitneyLogger get() {
        return (ProfileCompletionJitneyLogger) Preconditions.checkNotNull(this.module.profileCompletionJitneyLogger((LoggingContextFactory) this.loggingContextFactoryProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<ProfileCompletionJitneyLogger> create(ProfileCompletionModule module2, Provider<LoggingContextFactory> loggingContextFactoryProvider2) {
        return new ProfileCompletionModule_ProfileCompletionJitneyLoggerFactory(module2, loggingContextFactoryProvider2);
    }
}
