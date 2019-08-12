package com.airbnb.android.listyourspacedls.utils;

import com.airbnb.android.listing.LYSStep;
import com.google.common.collect.FluentIterable;

public class LYSLastSeenStepUtil {
    public static boolean shouldUpdateLastFinishedStepId(String currentStepId, String lastFinishedStepId) {
        LYSStep currentStep = getStepFromConstant(currentStepId);
        LYSStep lastFinishedStep = getStepFromConstant(lastFinishedStepId);
        if (currentStep == null || lastFinishedStep == null) {
            return false;
        }
        return currentStep.isAfter(lastFinishedStep);
    }

    private static LYSStep getStepFromConstant(String stepId) {
        return (LYSStep) FluentIterable.from((E[]) LYSStep.values()).filter(LYSLastSeenStepUtil$$Lambda$1.lambdaFactory$(stepId)).last().orNull();
    }

    static /* synthetic */ boolean lambda$getStepFromConstant$0(String stepId, LYSStep step) {
        return step.stepId != null && step.stepId.equals(stepId);
    }
}
