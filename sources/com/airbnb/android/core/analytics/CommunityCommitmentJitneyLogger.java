package com.airbnb.android.core.analytics;

import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.jitney.event.logging.CommunityBackButtonType.p070v1.C1960CommunityBackButtonType;
import com.airbnb.jitney.event.logging.CommunityCommitment.p071v1.CommunityCommitmentCancelScreenClickEvent.Builder;
import com.airbnb.jitney.event.logging.CommunityCommitment.p071v1.CommunityCommitmentCancelScreenGoBackEvent;
import com.airbnb.jitney.event.logging.CommunityCommitment.p071v1.CommunityCommitmentCancelScreenScrollEvent;
import com.airbnb.jitney.event.logging.CommunityCommitment.p071v1.CommunityCommitmentFeedbackSubmittedEvent;
import com.airbnb.jitney.event.logging.CommunityCommitment.p071v1.CommunityCommitmentIntroScreenClickEvent;
import com.airbnb.jitney.event.logging.CommunityCommitment.p071v1.CommunityCommitmentLearnMoreScreenClickEvent;
import com.airbnb.jitney.event.logging.CommunityCommitment.p071v1.CommunityCommitmentLearnMoreScreenGoBackEvent;
import com.airbnb.jitney.event.logging.CommunityCommitment.p071v1.CommunityCommitmentLearnMoreScreenScrollEvent;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;

public class CommunityCommitmentJitneyLogger extends BaseLogger {
    public static final String EMAIL_LINK = "allbelong_email";
    public static final String HOST_RESOURCE_HELP_CENTER_LINK = "host_resources_help_center";

    public CommunityCommitmentJitneyLogger(LoggingContextFactory loggingContextFactory) {
        super(loggingContextFactory);
    }

    public void clickItemOnCancelScreenEvent(String target) {
        publish(new Builder(context(), target));
    }

    public void goBackFromCancelScreenEvent(C1960CommunityBackButtonType communityBackButtonType) {
        publish(new CommunityCommitmentCancelScreenGoBackEvent.Builder(context(), communityBackButtonType));
    }

    public void scrollToViewButtonOnCancelScreenEvent() {
        publish(new CommunityCommitmentCancelScreenScrollEvent.Builder(context()));
    }

    public void submitFeedbackEvent(String feedbackText, long userId) {
        publish(new CommunityCommitmentFeedbackSubmittedEvent.Builder(contextWithUserId(userId), feedbackText));
    }

    public void clickItemOnIntroScreenEvent(String target) {
        publish(new CommunityCommitmentIntroScreenClickEvent.Builder(context(), target));
    }

    public void clickItemOnLearnMoreScreenEvent(String target) {
        publish(new CommunityCommitmentLearnMoreScreenClickEvent.Builder(context(), target));
    }

    public void goBackFromLearnMoreScreenEvent(C1960CommunityBackButtonType communityBackButtonType) {
        publish(new CommunityCommitmentLearnMoreScreenGoBackEvent.Builder(context(), communityBackButtonType));
    }

    public void scrollToViewButtonOnLearnMoreScreenEvent() {
        publish(new CommunityCommitmentLearnMoreScreenScrollEvent.Builder(context()));
    }

    private Context contextWithUserId(long userId) {
        return this.loggingContextFactory.newInstanceAsUser(userId);
    }
}
