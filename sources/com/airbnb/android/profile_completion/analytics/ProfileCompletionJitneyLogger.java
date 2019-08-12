package com.airbnb.android.profile_completion.analytics;

import com.airbnb.airrequest.NetworkException;
import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.analytics.BaseLogger;
import com.airbnb.android.profile_completion.CompletionStep;
import com.airbnb.android.profile_completion.ProfileCompletionManager;
import com.airbnb.android.profile_completion.ProfileCompletionManager.ProfileCompletionListener;
import com.airbnb.jitney.event.logging.ProfileCompletion.p212v1.ProfileCompletionImpressionEvent;
import com.airbnb.jitney.event.logging.ProfileCompletion.p212v1.ProfileCompletionStatusCheckEvent;
import com.airbnb.jitney.event.logging.ProfileCompletion.p212v1.ProfileCompletionStepResultEvent;
import com.airbnb.jitney.event.logging.ProfileCompletion.p212v1.ProfileCompletionStepStartEvent.Builder;
import com.airbnb.jitney.event.logging.ProfileCompletionImpressionTarget.p213v1.C2594ProfileCompletionImpressionTarget;
import com.airbnb.jitney.event.logging.ProfileCompletionStep.p214v1.C2595ProfileCompletionStep;
import com.google.common.collect.ImmutableMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProfileCompletionJitneyLogger extends BaseLogger implements ProfileCompletionListener {
    private static final Map<CompletionStep, C2595ProfileCompletionStep> STEP_TO_JITNEY_STEP = ImmutableMap.m1296of(CompletionStep.SignUp, C2595ProfileCompletionStep.SignUp, CompletionStep.Verificaton, C2595ProfileCompletionStep.Verification, CompletionStep.AddPaymentMethod, C2595ProfileCompletionStep.AddPaymentMethod, CompletionStep.CompleteAboutMe, C2595ProfileCompletionStep.CompleteAboutMe);
    private ProfileCompletionManager profileCompletionManager;
    private CompletionStep stepToLogResultForOnNextUpdate;

    public ProfileCompletionJitneyLogger(LoggingContextFactory loggingContextFactory) {
        super(loggingContextFactory);
    }

    public void logStepStart(CompletionStep step, ProfileCompletionManager profileCompletionManager2) {
        publish(new Builder(context(), jitneyCompletionStepFrom(step), jitneyCompletionStepListFrom(profileCompletionManager2.getCompletedSteps()), jitneyCompletionStepListFrom(profileCompletionManager2.getIncompleteSteps())));
    }

    public void fetchStatusAndLogStepResult(CompletionStep step, ProfileCompletionManager profileCompletionManager2) {
        this.profileCompletionManager = profileCompletionManager2;
        this.stepToLogResultForOnNextUpdate = step;
        profileCompletionManager2.addUpdateListener(this);
        profileCompletionManager2.fetchStatus();
    }

    private void logStepResult(CompletionStep step, ProfileCompletionManager profileCompletionManager2) {
        publish(new ProfileCompletionStepResultEvent.Builder(context(), jitneyCompletionStepFrom(step), Boolean.valueOf(profileCompletionManager2.getCompletedSteps().contains(step)), jitneyCompletionStepListFrom(profileCompletionManager2.getCompletedSteps()), jitneyCompletionStepListFrom(profileCompletionManager2.getIncompleteSteps())));
    }

    public void logProfileCompletionBarImpression(ProfileCompletionManager profileCompletionManager2) {
        publish(new ProfileCompletionImpressionEvent.Builder(context(), C2594ProfileCompletionImpressionTarget.ProfileCompletionBar, jitneyCompletionStepListFrom(profileCompletionManager2.getCompletedSteps()), jitneyCompletionStepListFrom(profileCompletionManager2.getIncompleteSteps())));
    }

    public void logProfileCompletionPageImpression(ProfileCompletionManager profileCompletionManager2) {
        publish(new ProfileCompletionImpressionEvent.Builder(context(), C2594ProfileCompletionImpressionTarget.ProfileCompletionPage, jitneyCompletionStepListFrom(profileCompletionManager2.getCompletedSteps()), jitneyCompletionStepListFrom(profileCompletionManager2.getIncompleteSteps())));
    }

    public void logStatusCheck(ProfileCompletionManager profileCompletionManager2) {
        publish(new ProfileCompletionStatusCheckEvent.Builder(context(), jitneyCompletionStepListFrom(profileCompletionManager2.getCompletedSteps()), jitneyCompletionStepListFrom(profileCompletionManager2.getIncompleteSteps())));
    }

    private List<C2595ProfileCompletionStep> jitneyCompletionStepListFrom(List<CompletionStep> steps) {
        List<C2595ProfileCompletionStep> jitneySteps = new ArrayList<>(steps.size());
        for (CompletionStep step : steps) {
            jitneySteps.add(jitneyCompletionStepFrom(step));
        }
        return jitneySteps;
    }

    private static C2595ProfileCompletionStep jitneyCompletionStepFrom(CompletionStep step) {
        return (C2595ProfileCompletionStep) STEP_TO_JITNEY_STEP.get(step);
    }

    public void onFetchStatusSuccess(boolean completedStepsChanged) {
        if (this.stepToLogResultForOnNextUpdate != null) {
            logStepResult(this.stepToLogResultForOnNextUpdate, this.profileCompletionManager);
            this.stepToLogResultForOnNextUpdate = null;
        }
        this.profileCompletionManager.removeUpdateListener(this);
    }

    public void onFetchStatusError(NetworkException e) {
    }
}
