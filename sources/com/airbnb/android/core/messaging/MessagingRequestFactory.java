package com.airbnb.android.core.messaging;

import android.app.NotificationManager;
import android.content.Context;
import android.net.Uri;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.PostApplicationCreatedInitializer;
import com.airbnb.android.core.analytics.MessagingJitneyLogger;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.events.MessageSendEvent;
import com.airbnb.android.core.intents.ThreadFragmentIntents;
import com.airbnb.android.core.models.InboxType;
import com.airbnb.android.core.models.Post;
import com.airbnb.android.core.models.Post.SendState;
import com.airbnb.android.core.models.Thread;
import com.airbnb.android.core.requests.CreateMessageRequest;
import com.airbnb.android.core.requests.CreateMessageWithImageAttachmentRequest;
import com.airbnb.android.core.requests.InboxRequest;
import com.airbnb.android.core.requests.ThreadRequest;
import com.airbnb.android.core.requests.UpdateThreadRequest;
import com.airbnb.android.core.responses.CreateMessageResponse;
import com.airbnb.android.core.services.PushNotificationBuilder;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.utils.PhotoCompressor;
import com.airbnb.android.core.utils.PhotoCompressor.SimpleCompressionCallback;
import com.airbnb.android.core.utils.PushNotificationUtil;
import com.squareup.otto.Bus;
import java.io.File;
import p032rx.Observer;

public class MessagingRequestFactory implements PostApplicationCreatedInitializer {
    private static final String NOTIFICATION_TAG = "message_send_failure";
    /* access modifiers changed from: private */
    public final AirbnbAccountManager accountManager;
    private final Bus bus;
    private final Context context;
    private final InboxUnreadCountManager inboxUnreadCountManager;
    private final MessagingJitneyLogger jitneyLogger;
    private final MessageStore messageStore;
    private final PhotoCompressor photoCompressor;
    private final SyncRequestFactory syncRequestFactory;

    public enum SendSource {
        Thread("message_thread"),
        VoiceReply("wearable");
        
        public final String serverKey;

        private SendSource(String serverKey2) {
            this.serverKey = serverKey2;
        }
    }

    public MessagingRequestFactory(Context context2, Bus bus2, AirbnbAccountManager accountManager2, MessageStore messageStore2, SyncRequestFactory syncRequestFactory2, MessagingJitneyLogger jitneyLogger2, PhotoCompressor photoCompressor2, InboxUnreadCountManager inboxUnreadCountManager2) {
        this.context = context2;
        this.bus = bus2;
        this.accountManager = accountManager2;
        this.messageStore = messageStore2;
        this.syncRequestFactory = syncRequestFactory2;
        this.jitneyLogger = jitneyLogger2;
        this.photoCompressor = photoCompressor2;
        this.inboxUnreadCountManager = inboxUnreadCountManager2;
    }

    public void initialize() {
        if (this.accountManager.hasAccessToken()) {
            this.inboxUnreadCountManager.setUnreadCount(InboxType.Guest, this.messageStore.getUnreadCount(InboxType.Guest));
            sync(InboxType.Guest);
            if (this.accountManager.userHasListings()) {
                this.inboxUnreadCountManager.setUnreadCount(InboxType.Host, this.messageStore.getUnreadCount(InboxType.Host));
                sync(InboxType.Host);
            }
        }
    }

    public void clearAllUserData() {
        this.messageStore.clearAll();
    }

    public Post sendMessage(InboxType inboxType, long threadId, String message, SendSource sendSource) {
        String str = message;
        Post post = Post.createTextPost(str, this.accountManager.getCurrentUserId(), System.currentTimeMillis(), SendState.Sending);
        sendPost(inboxType, threadId, post, sendSource);
        return post;
    }

    public Post sendImageMessage(InboxType inboxType, long threadId, String imagePath, SendSource sendSource) {
        final long temporaryPostId = System.currentTimeMillis();
        Post uncompressedPost = Post.createImagePost(imagePath, this.accountManager.getCurrentUserId(), temporaryPostId, SendState.Sending);
        final InboxType inboxType2 = inboxType;
        final long j = threadId;
        final SendSource sendSource2 = sendSource;
        this.photoCompressor.compressPhoto(Uri.fromFile(new File(imagePath)), new SimpleCompressionCallback(this.context) {
            public void onPhotoCompressed(String imagePath) {
                MessagingRequestFactory.this.sendPost(inboxType2, j, Post.createImagePost(imagePath, MessagingRequestFactory.this.accountManager.getCurrentUserId(), temporaryPostId, SendState.Sending), sendSource2);
            }
        });
        return uncompressedPost;
    }

    public void sendPost(InboxType inboxType, long threadId, Post post, SendSource sendSource) {
        long offlinePostId = post.getId();
        NonResubscribableRequestListener<CreateMessageResponse> listener = new C0699RL().onResponse(MessagingRequestFactory$$Lambda$1.lambdaFactory$(this, inboxType, threadId, offlinePostId, sendSource)).onError(MessagingRequestFactory$$Lambda$2.lambdaFactory$(this, inboxType, threadId, offlinePostId, post, sendSource)).buildWithoutResubscription();
        setOutgoingPostState(inboxType, threadId, offlinePostId, post, SendState.Sending, sendSource);
        createSendRequest(threadId, post).withListener((Observer) listener).execute(NetworkUtil.singleFireExecutor());
    }

    private BaseRequestV2<CreateMessageResponse> createSendRequest(long threadId, Post post) {
        switch (post.getContentType()) {
            case TEXT:
                return CreateMessageRequest.create(threadId, post.getMessage());
            case IMAGE:
                return CreateMessageWithImageAttachmentRequest.create(threadId, post);
            default:
                throw new IllegalStateException("Unhandled enum state: " + post.getContentType());
        }
    }

    public InboxRequest createInboxRequest(InboxType inboxType, Thread threadOffset) {
        if (!inboxType.useMessagingSync()) {
            return InboxRequest.create(inboxType, threadOffset, this.jitneyLogger);
        }
        return new MessageStoreInboxRequest(this.messageStore, inboxType, threadOffset, this.syncRequestFactory, this.jitneyLogger);
    }

    public ThreadRequest createThreadRequest(long threadId, InboxType inboxType) {
        if (!inboxType.useMessagingSync()) {
            return createFullThreadRequest(threadId, inboxType);
        }
        return new MessageStoreThreadRequest(this.messageStore, inboxType, threadId, this.syncRequestFactory, this.jitneyLogger);
    }

    public ThreadRequest createFullThreadRequest(long threadId, InboxType inboxType) {
        return new ThreadRequest(inboxType, threadId, this.jitneyLogger);
    }

    public void markThreadRead(InboxType inboxType, Thread thread) {
        if (inboxType.useMessagingSync()) {
            this.messageStore.markThreadAsRead(inboxType, thread.getId());
        }
        UpdateThreadRequest.forMarkRead(thread).execute(NetworkUtil.singleFireExecutor());
        PushNotificationUtil.dismissMessageThreadNotification(this.context, thread.getId());
        this.jitneyLogger.markThreadRead(inboxType, thread);
    }

    public UpdateThreadRequest archiveThread(InboxType inboxType, Thread thread, boolean archive) {
        this.jitneyLogger.logArchive(inboxType, thread, archive);
        if (!inboxType.useMessagingSync()) {
            return UpdateThreadRequest.forArchive(thread, archive);
        }
        return new MessageStoreArchiveThreadRequest(this, this.messageStore, inboxType, thread, archive);
    }

    public void sync(InboxType inboxType) {
        if (inboxType.useMessagingSync()) {
            this.syncRequestFactory.getSyncRequest(inboxType);
        }
    }

    public void expireCacheForThread(long threadId, InboxType inboxType) {
        InboxType nonArchivedType = inboxType.getNonArchivedInboxType();
        if (inboxType.useMessagingSync()) {
            sync(nonArchivedType);
            return;
        }
        createInboxRequest(inboxType, null).skipCache().execute(NetworkUtil.singleFireExecutor());
        createThreadRequest(threadId, inboxType).skipCache().execute(NetworkUtil.singleFireExecutor());
    }

    /* access modifiers changed from: private */
    public void setOutgoingPostState(InboxType inboxType, long threadId, long offlineId, Post post, SendState state, SendSource sendSource) {
        post.setSendState(state);
        this.bus.post(new MessageSendEvent(threadId, offlineId, post));
        if (inboxType.useMessagingSync()) {
            this.messageStore.storeOutgoingMessage(threadId, offlineId, post);
        }
        InboxType destinationInbox = inboxType.getNonArchivedInboxType();
        if (destinationInbox.useMessagingSync()) {
            sync(destinationInbox);
        }
        switch (state) {
            case Sending:
                clearFailedSendNotifications(this.context, offlineId);
                break;
            case Failed:
                showFailedSendNotification(this.context, inboxType, threadId, offlineId);
                break;
            case None:
                expireCacheForThread(threadId, inboxType);
                break;
        }
        this.jitneyLogger.sendEvent(inboxType, threadId, post, offlineId, sendSource);
    }

    private static void showFailedSendNotification(Context context2, InboxType inboxType, long threadId, long offlinePostId) {
        if (inboxType.useMessagingSync()) {
            new PushNotificationBuilder(context2).setLaunchIntent(ThreadFragmentIntents.newIntent(context2, threadId, inboxType, Long.valueOf(offlinePostId), null)).setTitleAndContent(context2.getString(C0716R.string.message_send_failed_notification_title), context2.getString(C0716R.string.message_send_failed_notification_description)).buildAndNotify(NOTIFICATION_TAG, getNotificationId(offlinePostId));
        }
    }

    private static void clearFailedSendNotifications(Context context2, long offlinePostId) {
        ((NotificationManager) context2.getSystemService("notification")).cancel(NOTIFICATION_TAG, getNotificationId(offlinePostId));
    }

    private static int getNotificationId(long offlinePostId) {
        return (int) (offlinePostId % 2147483647L);
    }
}
