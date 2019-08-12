package com.airbnb.android.profile_completion;

import com.airbnb.android.core.CoreGraph;
import com.airbnb.android.profile_completion.analytics.ProfileCompletionJitneyLogger;

public interface ProfileCompletionGraph extends CoreGraph {
    void inject(ProfileCompletionActivity profileCompletionActivity);

    ProfileCompletionJitneyLogger profileCompletionJitneyLogger();

    ProfileCompletionManager profileCompletionManager();
}
