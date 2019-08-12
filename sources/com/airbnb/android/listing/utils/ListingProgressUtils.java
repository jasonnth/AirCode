package com.airbnb.android.listing.utils;

import com.airbnb.android.core.models.ListingPickerInfo;
import com.airbnb.android.core.utils.listing.ListingDisplayUtils;
import com.airbnb.android.listing.LYSStep;
import com.airbnb.android.listing.constants.LYSStepIdRead;
import com.airbnb.android.listing.constants.LYSStepOrderUtil;
import java.util.List;

public class ListingProgressUtils {
    public static int getListingPercentageComplete(ListingPickerInfo listing) {
        int percentageComplete = (int) listing.getPercentCompleted();
        String lastFinishedStepId = listing.getListYourSpaceLastFinishedStepId();
        if (lastFinishedStepId == null) {
            return percentageComplete;
        }
        LYSStep lastFinishedStep = LYSStepIdRead.stepIdToLYSStepSuperset(lastFinishedStepId);
        List<LYSStep> totalSteps = LYSStepOrderUtil.getOrderedStepsWithoutCompletion();
        return (int) ListingDisplayUtils.getPercentCompleted(totalSteps.indexOf(lastFinishedStep) + 1, totalSteps.size());
    }
}
