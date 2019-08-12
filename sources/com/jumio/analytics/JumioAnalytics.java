package com.jumio.analytics;

import android.content.Context;
import com.jumio.analytics.agents.EventAgent;
import com.jumio.analytics.agents.FixedRateQuotaAgent;
import com.jumio.analytics.http.HttpEventDispatcher;
import com.jumio.commons.log.Log;
import com.jumio.core.network.ApiCall.DynamicProvider;
import java.util.UUID;

public class JumioAnalytics {
    public static final String LOGTAG = "Analytics";
    private static EventAgent mAgent;
    private static boolean mEnabled = true;
    private static UUID mSessionId;

    public static void start() {
        Filter disabledEvents = getConfiguration();
        Log.m1909d(LOGTAG, "## Starting JumioAnalytics module ##");
        mAgent = new FixedRateQuotaAgent(null, disabledEvents);
        newSessionId();
    }

    private static Filter getConfiguration() {
        return new Filter().disable(MobileEvents.EVENTTYPE_RAW_TOUCH).disable(MobileEvents.EVENTTYPE_NETWORKCALL);
    }

    public static void add(AnalyticsEvent event) throws IllegalStateException {
        if (mEnabled) {
            if (event.getSessionId() == null) {
                Log.m1929w(LOGTAG, "Discarding event (sessionID == null) : " + event.toString());
            } else if (mAgent == null) {
                String detailMessage = "JumioAnalytics not initialized. Call start()!";
                IllegalStateException illegalStateException = new IllegalStateException(detailMessage);
                Log.m1930w(LOGTAG, detailMessage, (Throwable) illegalStateException);
                throw illegalStateException;
            } else {
                mAgent.enqueue(event);
                if (event.mEventType == 302 && !event.getPayload().getValue().equals(DismissType.INSTANCE_CREATED.toString())) {
                    Log.m1924v(LOGTAG, "-- event was SDKLIFECYCLE -> flush() events");
                    mAgent.flush();
                }
            }
        }
    }

    public static void shutdown(Runnable finishRunnable) {
        Log.m1909d(LOGTAG, "## Shutting down JumioAnalytics module ##");
        if (!mEnabled || (mAgent != null && mAgent.isLocked())) {
            new Thread(finishRunnable).start();
        }
        if (mAgent != null) {
            mAgent.flush();
            mAgent.addFinishTask(finishRunnable);
            mAgent = null;
        }
        mSessionId = null;
        Log.m1909d(LOGTAG, "## Shutting down JumioAnalytics module complete ##");
    }

    private static UUID newSessionId() {
        mSessionId = null;
        mSessionId = UUID.randomUUID();
        Log.m1909d(LOGTAG, "create new session Id: " + mSessionId.toString());
        return mSessionId;
    }

    public static UUID getSessionId() throws IllegalStateException {
        if (mSessionId == null) {
            Log.m1929w(LOGTAG, "getSessionId(): uninitialized sessionID!");
        }
        return mSessionId;
    }

    public static void flush() {
        if (mAgent != null) {
            Log.m1909d(LOGTAG, "forced flush");
            mAgent.flush();
        }
    }

    public static void unlock(Context context, DynamicProvider dynamicProvider, String urlPrefix, String userAgent) {
        Log.m1909d(LOGTAG, "Unlock Analytics");
        if (mAgent == null) {
            Log.m1929w(LOGTAG, "unlock(): JumioAnalytics not initialized. Call start now!");
            start();
        }
        mEnabled = true;
        mAgent.setEventDispatcher(new HttpEventDispatcher(context, dynamicProvider, urlPrefix, userAgent));
        mAgent.setLocked(false);
    }

    public static void disable() {
        Log.m1909d(LOGTAG, "disable Analytics");
        mEnabled = false;
        if (mAgent != null) {
            mAgent.stop();
        }
    }

    public static void allowEvent(int eventType, boolean allow) {
        if (mAgent != null) {
            if (allow) {
                mAgent.getFilter().enable(eventType);
            } else {
                mAgent.getFilter().disable(eventType);
            }
        }
    }
}
