package com.airbnb.android.core.analytics;

import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.models.Listing;
import com.airbnb.jitney.event.logging.Cohosting.p059v1.CohostingAcceptButtonClickInviteFlowEvent;
import com.airbnb.jitney.event.logging.Cohosting.p059v1.CohostingImpressionInviteFlowEvent;
import com.airbnb.jitney.event.logging.Cohosting.p059v1.CohostingInviteDetailPageImpressionInviteFlowEvent.Builder;
import com.airbnb.jitney.event.logging.CohostingInviteFlowPage.p064v1.C1954CohostingInviteFlowPage;

public class CohostingInvitationJitneyLogger extends BaseLogger {
    public CohostingInvitationJitneyLogger(LoggingContextFactory loggingContextFactory) {
        super(loggingContextFactory);
    }

    public void logCohostingInvitationInviteDetailImpression(Long invitation_id, Long inviter_user_id, String original_invitee_email, Long invitee_user_id, String invitee_identifier_type, String invitee_identifier, String expires_at, Listing listing) {
        Builder builder = new Builder(context(), invitation_id, inviter_user_id, original_invitee_email, invitee_user_id, invitee_identifier_type, invitee_identifier, expires_at);
        if (listing != null) {
            builder.listing_id(Long.valueOf(listing.getId()));
        }
        publish(builder);
    }

    public void logCohostingInvitationConfirmationImpression(long userId) {
        logImpression(C1954CohostingInviteFlowPage.ConfirmationPage, userId);
    }

    public void logCohostingInvitationExpirationImpression(long userId) {
        logImpression(C1954CohostingInviteFlowPage.ExpirationPage, userId);
    }

    public void logCohostingInvitationEmailMismatchImpression(long userId) {
        logImpression(C1954CohostingInviteFlowPage.EmailMismatchErrorPage, userId);
    }

    public void logCohostingInvitationInviteSelfImpression(long userId) {
        logImpression(C1954CohostingInviteFlowPage.InviteSelfErrorPage, userId);
    }

    public void logCohostingInvitationInvalidInviteImpression(long userId) {
        logImpression(C1954CohostingInviteFlowPage.InvalidInviteErrorPage, userId);
    }

    public void logCohostingInvitationAcceptClick(Long invitation_id, Long inviter_user_id, String original_invitee_email, Long invitee_user_id, String invitee_identifier_type, String invitee_identifier, String expires_at, Listing listing) {
        CohostingAcceptButtonClickInviteFlowEvent.Builder builder = new CohostingAcceptButtonClickInviteFlowEvent.Builder(context(), invitation_id, inviter_user_id, original_invitee_email, invitee_user_id, invitee_identifier_type, invitee_identifier, expires_at);
        if (listing != null) {
            builder.listing_id(Long.valueOf(listing.getId()));
        }
        publish(builder);
    }

    private void logImpression(C1954CohostingInviteFlowPage page, long userId) {
        publish(new CohostingImpressionInviteFlowEvent.Builder(context(), page, Long.valueOf(userId)));
    }
}
