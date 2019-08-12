package com.airbnb.android.profile_completion;

import com.airbnb.android.core.utils.SharedPrefsHelper;
import com.airbnb.erf.Experiments;

public class ProfileCompletionHelper {
    public static boolean shouldShowBadge(ProfileCompletionManager profileCompletionManager, SharedPrefsHelper preferences) {
        return !preferences.isBadgeSeenAndClearedForProfileCompletion() && profileCompletionManager.hasIncompleteBadgingSteps() && shouldShowProfileCompletionBar(profileCompletionManager, preferences);
    }

    public static boolean shouldShowProfileCompletionBar(ProfileCompletionManager profileCompletionManager, SharedPrefsHelper preferences) {
        if (profileCompletionManager.getMostRecentlyFetchedUser() == null || !profileCompletionManager.hasIncompleteSteps()) {
            return false;
        }
        if (preferences.hasBeenAssignedToNewGuestExperimentForProfileCompletion()) {
            return preferences.isTreatmentNewGuestExperimentAssignmentForProfileCompletion();
        }
        if (profileCompletionManager.getMostRecentlyFetchedUser().getReservationsAsGuestCount() == 0) {
            boolean assignment = Experiments.isInProfileCompletionNewGuestTreatment();
            preferences.setNewGuestExperimentAssignmentForProfileCompletion(assignment);
            return assignment;
        } else if (profileCompletionManager.getMostRecentlyFetchedUser().getReservationsAsGuestCount() > 0) {
            return Experiments.isInProfileCompletionPastBookerTreatment();
        } else {
            return false;
        }
    }
}
