package com.airbnb.android.core.analytics;

import android.content.Context;
import android.text.TextUtils;
import com.airbnb.airrequest.AirResponse;
import com.airbnb.airrequest.BaseResponse;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.airrequest.Transformer;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger.Builder;
import com.airbnb.android.core.intents.ThreadBlockActivityIntents;
import com.airbnb.android.core.models.InboxType;
import com.airbnb.android.core.models.Thread;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.utils.ReservationStatusDisplay;
import com.airbnb.android.utils.Strap;
import java.util.ArrayList;
import java.util.List;

public class MessagingAnalytics extends BaseAnalytics {
    private static final String ARRAY_DELIMITER = ",";
    private static final String DATADOG_KEY = "datadog_key";
    private static final String ERROR_MESSAGE = "error_message";
    private static final String EVENT_NAME = "messaging_and_inbox";
    private static final int LOG_VERSION = 1;
    private static final String VERSION_KEY = "v";

    public enum Action {
        FetchInbox("fetch_inbox"),
        FetchThreadV2("fetch_thread_2"),
        FetchThreadLegacy("fetch_thread_legacy"),
        FetchSync("fetch_sync"),
        Send("send");
        
        public final String analyticsName;

        private Action(String analyticsName2) {
            this.analyticsName = analyticsName2;
        }
    }

    public enum Status {
        Attempt("attempt"),
        Cached("cached"),
        Success("success"),
        Error("error");
        
        public final String analyticsName;

        private Status(String analyticsName2) {
            this.analyticsName = analyticsName2;
        }
    }

    public static <T extends BaseResponse> Transformer<T> instrument(Action action) {
        return MessagingAnalytics$$Lambda$1.lambdaFactory$(action);
    }

    static /* synthetic */ void lambda$null$1(Action action, AirResponse r) {
        logOperationStatus(action, ((BaseResponse) r.body()).metadata.isCached() ? Status.Cached : Status.Success);
    }

    /* access modifiers changed from: private */
    public static void logOperationStatus(Action action, Status status) {
        createEvent(action, status).track();
    }

    /* access modifiers changed from: private */
    public static void logOperationError(Action action, Throwable t) {
        String message = null;
        if (t instanceof NetworkException) {
            message = NetworkUtil.errorMessage((NetworkException) t);
        }
        if (message == null) {
            message = t.getClass().getSimpleName();
        }
        createEvent(action, Status.Error).mo8238kv("error_message", message).track();
    }

    private static Builder createEvent(Action action, Status status) {
        String operationName = action.analyticsName + '.' + status.analyticsName;
        return AirbnbEventLogger.event().name(EVENT_NAME).mo8238kv(BaseAnalytics.OPERATION, operationName).mo8238kv(DATADOG_KEY, "messaging_and_inbox." + operationName).mo8236kv(VERSION_KEY, 1);
    }

    public static void trackThreadView(Thread thread, String statusString, InboxType inboxType) {
        Strap strap = Strap.make().mo11639kv("page", ThreadBlockActivityIntents.ARG_THREAD).mo11639kv(BaseAnalytics.SECTION, ThreadBlockActivityIntents.ARG_THREAD).mo11639kv("action", "view").mo11639kv("status", statusString).mo11639kv("role", inboxType.inboxRole).mo11640kv("archived", inboxType.archived).mo11638kv("thread_id", thread.getId()).mo11637kv("guest_count", thread.getNumberOfGuests());
        if (thread.hasListing()) {
            strap.mo11638kv("listing_id", thread.getListing().getId());
        }
        if (thread.getStartDate() != null) {
            strap.mo11639kv("checkin_date", thread.getStartDate().getIsoDateString());
        }
        if (thread.getEndDate() != null) {
            strap.mo11639kv("checkout_date", thread.getEndDate().getIsoDateString());
        }
        AirbnbEventLogger.track(EVENT_NAME, strap);
    }

    public static void trackInboxViewMessages(List<Thread> threads, Context context) {
        ArrayList<String> unreadMessageIds = new ArrayList<>();
        ArrayList<String> readMessageIds = new ArrayList<>();
        ArrayList<String> unreadMessageStatuses = new ArrayList<>();
        ArrayList<String> readMessageStatuses = new ArrayList<>();
        for (Thread thread : threads) {
            String statusString = ReservationStatusDisplay.forStatus(thread.getReservationStatus()).getString(context);
            if (thread.isUnread()) {
                unreadMessageIds.add(Long.toString(thread.getId()));
                unreadMessageStatuses.add(statusString);
            } else {
                readMessageIds.add(Long.toString(thread.getId()));
                readMessageStatuses.add(statusString);
            }
        }
        AirbnbEventLogger.track(EVENT_NAME, commonInboxStrap().mo11639kv("read_messages", TextUtils.join(ARRAY_DELIMITER, readMessageIds)).mo11639kv("unread_messages", TextUtils.join(ARRAY_DELIMITER, unreadMessageIds)).mo11639kv("read_messages_status", TextUtils.join(ARRAY_DELIMITER, readMessageStatuses)).mo11639kv("unread_messages_status", TextUtils.join(ARRAY_DELIMITER, unreadMessageStatuses)));
    }

    private static Strap commonInboxStrap() {
        return Strap.make().mo11639kv("page", "guest_inbox");
    }
}
