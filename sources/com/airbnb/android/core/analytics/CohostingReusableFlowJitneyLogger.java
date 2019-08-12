package com.airbnb.android.core.analytics;

import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.jitney.event.logging.Cohosting.p060v2.CohostingClickReusableRemoveFlowEvent;
import com.airbnb.jitney.event.logging.Cohosting.p060v2.CohostingImpressionReusableRemoveFlowEvent.Builder;
import com.airbnb.jitney.event.logging.ReusableRemoveClickType.p229v1.C2636ReusableRemoveClickType;
import com.airbnb.jitney.event.logging.ReusableRemoveModalType.p230v1.C2637ReusableRemoveModalType;

public class CohostingReusableFlowJitneyLogger extends BaseLogger {
    public CohostingReusableFlowJitneyLogger(LoggingContextFactory loggingContextFactory) {
        super(loggingContextFactory);
    }

    public void logReusableReasonCaptureImpression(long removedCohostId, long sourceId, String sourceType, String action) {
        publish(new Builder(context(), C2637ReusableRemoveModalType.ReasonCaptureModal, Long.valueOf(sourceId), sourceType, action).removed_cohost_id(Long.valueOf(removedCohostId)));
    }

    public void logReusablePrivateFeedbackImpression(long removedCohostId, long sourceId, String sourceType, String action) {
        publish(new Builder(context(), C2637ReusableRemoveModalType.CommentModal, Long.valueOf(sourceId), sourceType, action).removed_cohost_id(Long.valueOf(removedCohostId)));
    }

    public void logReusableMessageImpression(long removedCohostId, long sourceId, String sourceType, String action) {
        publish(new Builder(context(), C2637ReusableRemoveModalType.MessageModal, Long.valueOf(sourceId), sourceType, action).removed_cohost_id(Long.valueOf(removedCohostId)));
    }

    public void logNextClick(long removedCohostId, Long sourceId, String sourceType, String action, Long reason) {
        publish(new CohostingClickReusableRemoveFlowEvent.Builder(context(), C2636ReusableRemoveClickType.Next, sourceId, sourceType, action, reason).removed_cohost_id(Long.valueOf(removedCohostId)));
    }

    public void logSendMessageClick(String message, long removedCohostId, Long sourceId, String sourceType, String action, Long reason) {
        if (message == null) {
            logSendWithoutMessageClick(removedCohostId, sourceId, sourceType, action, reason);
        } else {
            logSendWithMessageClick(removedCohostId, sourceId, sourceType, action, reason);
        }
    }

    public void logSendWithoutMessageClick(long removedCohostId, Long sourceId, String sourceType, String action, Long reason) {
        publish(new CohostingClickReusableRemoveFlowEvent.Builder(context(), C2636ReusableRemoveClickType.SendWithoutMessage, sourceId, sourceType, action, reason).removed_cohost_id(Long.valueOf(removedCohostId)));
    }

    public void logSendWithMessageClick(long removedCohostId, Long sourceId, String sourceType, String action, Long reason) {
        publish(new CohostingClickReusableRemoveFlowEvent.Builder(context(), C2636ReusableRemoveClickType.SendWithMessage, sourceId, sourceType, action, reason).removed_cohost_id(Long.valueOf(removedCohostId)));
    }
}
