package com.airbnb.android.profile_completion;

import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.profile_completion.analytics.ProfileCompletionJitneyLogger;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ProfileCompletionModule_ProfileCompletionManagerFactory implements Factory<ProfileCompletionManager> {
    static final /* synthetic */ boolean $assertionsDisabled = (!ProfileCompletionModule_ProfileCompletionManagerFactory.class.desiredAssertionStatus());
    private final Provider<AirbnbAccountManager> accountManagerProvider;
    private final ProfileCompletionModule module;
    private final Provider<ProfileCompletionJitneyLogger> profileCompletionJitneyLoggerProvider;

    public ProfileCompletionModule_ProfileCompletionManagerFactory(ProfileCompletionModule module2, Provider<AirbnbAccountManager> accountManagerProvider2, Provider<ProfileCompletionJitneyLogger> profileCompletionJitneyLoggerProvider2) {
        if ($assertionsDisabled || module2 != null) {
            this.module = module2;
            if ($assertionsDisabled || accountManagerProvider2 != null) {
                this.accountManagerProvider = accountManagerProvider2;
                if ($assertionsDisabled || profileCompletionJitneyLoggerProvider2 != null) {
                    this.profileCompletionJitneyLoggerProvider = profileCompletionJitneyLoggerProvider2;
                    return;
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public ProfileCompletionManager get() {
        return (ProfileCompletionManager) Preconditions.checkNotNull(this.module.profileCompletionManager((AirbnbAccountManager) this.accountManagerProvider.get(), (ProfileCompletionJitneyLogger) this.profileCompletionJitneyLoggerProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<ProfileCompletionManager> create(ProfileCompletionModule module2, Provider<AirbnbAccountManager> accountManagerProvider2, Provider<ProfileCompletionJitneyLogger> profileCompletionJitneyLoggerProvider2) {
        return new ProfileCompletionModule_ProfileCompletionManagerFactory(module2, accountManagerProvider2, profileCompletionJitneyLoggerProvider2);
    }
}
