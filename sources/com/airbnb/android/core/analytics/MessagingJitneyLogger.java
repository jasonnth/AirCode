package com.airbnb.android.core.analytics;

import com.airbnb.airrequest.AirResponse;
import com.airbnb.airrequest.BaseResponse;
import com.airbnb.airrequest.Transformer;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.messaging.MessagingRequestFactory.SendSource;
import com.airbnb.android.core.models.InboxType;
import com.airbnb.android.core.models.Post;
import com.airbnb.android.core.models.Thread;
import com.airbnb.jitney.event.logging.I18n.p014v1.I18nTrackTranslationFailureEvent;
import com.airbnb.jitney.event.logging.I18n.p015v2.I18nTrackTranslationEvent;
import com.airbnb.jitney.event.logging.Inbox.p017v1.InboxFetchInboxEvent;
import com.airbnb.jitney.event.logging.Inbox.p017v1.InboxFetchSyncEvent;
import com.airbnb.jitney.event.logging.Inbox.p017v1.InboxFetchThreadEvent;
import com.airbnb.jitney.event.logging.Inbox.p017v1.InboxMarkMessageArchivedEvent;
import com.airbnb.jitney.event.logging.Inbox.p017v1.InboxMarkMessageReadEvent;
import com.airbnb.jitney.event.logging.Inbox.p017v1.InboxMarkMessageUnarchivedEvent;
import com.airbnb.jitney.event.logging.Message.p021v1.ActionType;
import com.airbnb.jitney.event.logging.Message.p021v1.MessageThreadDetailsEvent;
import com.airbnb.jitney.event.logging.Message.p021v1.MessageThreadFetchImageEvent;
import com.airbnb.jitney.event.logging.Message.p021v1.MessageThreadTakePhotoEvent;
import com.airbnb.jitney.event.logging.Message.p021v1.MessageThreadTapAttachmentButtonEvent;
import com.airbnb.jitney.event.logging.Message.p021v1.MessageThreadTapImageEvent;
import com.airbnb.jitney.event.logging.Message.p021v1.SendMessageEvent.Builder;
import com.airbnb.jitney.event.logging.Message.p021v1.SendState;
import com.airbnb.jitney.event.logging.SavedMessage.p022v1.SavedMessageCreateEvent;
import com.airbnb.jitney.event.logging.SavedMessage.p022v1.SavedMessageUseEvent;
import com.airbnb.jitney.event.logging.TranslationButtonTextType.p024v1.C0975TranslationButtonTextType;
import com.airbnb.jitney.event.logging.core.request.p026v1.RequestState;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import p032rx.functions.Action1;

public class MessagingJitneyLogger extends BaseLogger {
    public static final String CAMERA_ICON_LOGGING_TAG = "take_photo";
    public static final String PHOTO_ICON_LOGGING_TAG = "gallery";
    public static final String SAVED_MESSAGES_LOGGING_TAG = "saved_messages";

    @Retention(RetentionPolicy.SOURCE)
    public @interface EventTagName {
    }

    public MessagingJitneyLogger(LoggingContextFactory loggingContextFactory) {
        super(loggingContextFactory);
    }

    public static <T extends BaseResponse> Transformer<T> createInstrumentationTransformer(Action1<RequestState> action) {
        return MessagingJitneyLogger$$Lambda$1.lambdaFactory$(action);
    }

    static /* synthetic */ void lambda$null$1(Action1 action, AirResponse r) {
        action.call(((BaseResponse) r.body()).metadata.isCached() ? RequestState.Cached : RequestState.Success);
    }

    public void sendEvent(InboxType inboxType, long threadId, Post post, long offlineId, SendSource sendSource) {
        publish(new Builder(context(), inboxType.inboxRole, convertSendState(post.getSendState()), post.getContentType().getType(), sendSource.serverKey, Long.toString(offlineId), inboxType.inboxTypeLoggingString(), Long.valueOf(threadId)));
    }

    public void takePhotoClicked(InboxType inboxType, Thread thread) {
        MessageThreadTakePhotoEvent.Builder builder = new MessageThreadTakePhotoEvent.Builder(context(), Long.valueOf(thread.getId()), inboxType.inboxRole, inboxType.inboxTypeLoggingString());
        if (thread.hasListing()) {
            builder.listing_id(Long.valueOf(thread.getListing().getId()));
        }
        publish(builder);
    }

    public void threadDetailsClicked(InboxType inboxType, Thread thread, ActionType actionType) {
        MessageThreadDetailsEvent.Builder builder = new MessageThreadDetailsEvent.Builder(context(), Long.valueOf(thread.getId()), actionType, inboxType.inboxRole, inboxType.inboxTypeLoggingString());
        if (thread.hasListing()) {
            builder.listing_id(Long.valueOf(thread.getListing().getId()));
        }
        publish(builder);
    }

    public void markThreadRead(InboxType inboxType, Thread thread) {
        publish(new InboxMarkMessageReadEvent.Builder(context(), Long.valueOf(thread.getId()), inboxType.inboxRole));
    }

    public void savedMessageCreated() {
        publish(new SavedMessageCreateEvent.Builder(context(), InboxType.Host.inboxRole));
    }

    public void savedMessageUsed() {
        publish(new SavedMessageUseEvent.Builder(context(), InboxType.Host.inboxRole));
    }

    public void logArchive(InboxType inboxType, Thread thread, boolean archive) {
        if (archive) {
            logArchived(inboxType, thread);
        } else {
            logUnarchived(inboxType, thread);
        }
    }

    public void logThreadFetch(InboxType inboxType, long threadId, RequestState requestState) {
        publish(new InboxFetchThreadEvent.Builder(context(), requestState, Long.valueOf(threadId), inboxType.inboxRole, inboxType.inboxTypeLoggingString()));
    }

    public void logInboxFetch(InboxType inboxType, RequestState requestState) {
        publish(new InboxFetchInboxEvent.Builder(context(), requestState, inboxType.inboxRole, inboxType.inboxTypeLoggingString()));
    }

    public void logSyncFetch(InboxType inboxType, RequestState requestState) {
        publish(new InboxFetchSyncEvent.Builder(context(), requestState, inboxType.inboxRole, inboxType.inboxTypeLoggingString()));
    }

    public void logImageTap(long message_thread_id, long post_id) {
        publish(new MessageThreadTapImageEvent.Builder(context(), Long.valueOf(message_thread_id), Long.valueOf(post_id)));
    }

    public void logInputViewButtonClicked(String target, long message_thread_id) {
        publish(new MessageThreadTapAttachmentButtonEvent.Builder(context(), target, Long.valueOf(message_thread_id)));
    }

    public void logImageFetch(long message_thread_id, long post_id, boolean fetch_success, long image_size, long seconds_to_load) {
        publish(new MessageThreadFetchImageEvent.Builder(context(), Long.valueOf(message_thread_id), Long.valueOf(post_id), Boolean.valueOf(fetch_success), Long.valueOf(image_size), Long.valueOf(seconds_to_load)));
    }

    public void logTranslateThreadClicked(InboxType inboxType, Thread thread, C0975TranslationButtonTextType translationButtonText) {
        publish(new I18nTrackTranslationEvent.Builder(context(), Long.valueOf(thread.getId()), Long.valueOf(thread.hasListing() ? thread.getListing().getId() : -1), translationButtonText, inboxType.inboxRole));
    }

    public void logTranslationFailure(InboxType inboxType, Thread thread, String errorMessage) {
        publish(new I18nTrackTranslationFailureEvent.Builder(context(), Long.valueOf(thread.getId()), Long.valueOf(thread.hasListing() ? thread.getListing().getId() : -1), errorMessage, inboxType.inboxRole));
    }

    private void logArchived(InboxType inboxType, Thread thread) {
        publish(new InboxMarkMessageArchivedEvent.Builder(context(), Long.valueOf(thread.getId()), inboxType.inboxRole));
    }

    private void logUnarchived(InboxType inboxType, Thread thread) {
        publish(new InboxMarkMessageUnarchivedEvent.Builder(context(), Long.valueOf(thread.getId()), inboxType.inboxRole));
    }

    private static SendState convertSendState(Post.SendState sendState) {
        switch (sendState) {
            case Sending:
                return SendState.Attempt;
            case Failed:
                return SendState.Failure;
            case None:
                return SendState.Success;
            default:
                BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalStateException("Send state not handled: " + sendState));
                return SendState.Failure;
        }
    }
}
