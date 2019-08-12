package com.airbnb.android.profile_completion;

import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.profile_completion.analytics.ProfileCompletionJitneyLogger;

public class ProfileCompletionModule {
    public ProfileCompletionManager profileCompletionManager(AirbnbAccountManager accountManager, ProfileCompletionJitneyLogger profileCompletionJitneyLogger) {
        return new ProfileCompletionManager(accountManager, profileCompletionJitneyLogger);
    }

    public ProfileCompletionJitneyLogger profileCompletionJitneyLogger(LoggingContextFactory loggingContextFactory) {
        return new ProfileCompletionJitneyLogger(loggingContextFactory);
    }
}
