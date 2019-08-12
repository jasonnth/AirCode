package com.facebook.appevents;

import android.os.Bundle;
import com.airbnb.android.lib.services.ViewedListingsPersistenceService;
import com.facebook.LoggingBehavior;
import com.facebook.appevents.AppEventsLogger.FlushBehavior;
import com.facebook.internal.Logger;
import java.io.Serializable;
import java.util.Locale;

class FacebookTimeSpentData implements Serializable {
    private static final long APP_ACTIVATE_SUPPRESSION_PERIOD_IN_MILLISECONDS = 300000;
    private static final long FIRST_TIME_LOAD_RESUME_TIME = -1;
    private static final long[] INACTIVE_SECONDS_QUANTA = {APP_ACTIVATE_SUPPRESSION_PERIOD_IN_MILLISECONDS, 900000, 1800000, 3600000, 21600000, 43200000, 86400000, ViewedListingsPersistenceService.VIEWED_LISTINGS_PURGE_INTERVAL_IN_MS, 259200000, 604800000, 1209600000, 1814400000, 2419200000L, 5184000000L, 7776000000L, 10368000000L, 12960000000L, 15552000000L, 31536000000L};
    private static final long INTERRUPTION_THRESHOLD_MILLISECONDS = 1000;
    private static final long NUM_MILLISECONDS_IDLE_TO_BE_NEW_SESSION = 60000;
    private static final String TAG = FacebookTimeSpentData.class.getCanonicalName();
    private static final long serialVersionUID = 1;
    private String firstOpenSourceApplication;
    private int interruptionCount;
    private boolean isAppActive;
    private boolean isWarmLaunch;
    private long lastActivateEventLoggedTime;
    private long lastResumeTime;
    private long lastSuspendTime;
    private long millisecondsSpentInSession;

    private static class SerializationProxyV1 implements Serializable {
        private static final long serialVersionUID = 6;
        private final int interruptionCount;
        private final long lastResumeTime;
        private final long lastSuspendTime;
        private final long millisecondsSpentInSession;

        SerializationProxyV1(long lastResumeTime2, long lastSuspendTime2, long millisecondsSpentInSession2, int interruptionCount2) {
            this.lastResumeTime = lastResumeTime2;
            this.lastSuspendTime = lastSuspendTime2;
            this.millisecondsSpentInSession = millisecondsSpentInSession2;
            this.interruptionCount = interruptionCount2;
        }

        private Object readResolve() {
            return new FacebookTimeSpentData(this.lastResumeTime, this.lastSuspendTime, this.millisecondsSpentInSession, this.interruptionCount);
        }
    }

    private static class SerializationProxyV2 implements Serializable {
        private static final long serialVersionUID = 6;
        private final String firstOpenSourceApplication;
        private final int interruptionCount;
        private final long lastResumeTime;
        private final long lastSuspendTime;
        private final long millisecondsSpentInSession;

        SerializationProxyV2(long lastResumeTime2, long lastSuspendTime2, long millisecondsSpentInSession2, int interruptionCount2, String firstOpenSourceApplication2) {
            this.lastResumeTime = lastResumeTime2;
            this.lastSuspendTime = lastSuspendTime2;
            this.millisecondsSpentInSession = millisecondsSpentInSession2;
            this.interruptionCount = interruptionCount2;
            this.firstOpenSourceApplication = firstOpenSourceApplication2;
        }

        private Object readResolve() {
            return new FacebookTimeSpentData(this.lastResumeTime, this.lastSuspendTime, this.millisecondsSpentInSession, this.interruptionCount, this.firstOpenSourceApplication);
        }
    }

    private FacebookTimeSpentData(long lastResumeTime2, long lastSuspendTime2, long millisecondsSpentInSession2, int interruptionCount2) {
        resetSession();
        this.lastResumeTime = lastResumeTime2;
        this.lastSuspendTime = lastSuspendTime2;
        this.millisecondsSpentInSession = millisecondsSpentInSession2;
        this.interruptionCount = interruptionCount2;
    }

    FacebookTimeSpentData() {
        resetSession();
    }

    private FacebookTimeSpentData(long lastResumeTime2, long lastSuspendTime2, long millisecondsSpentInSession2, int interruptionCount2, String firstOpenSourceApplication2) {
        resetSession();
        this.lastResumeTime = lastResumeTime2;
        this.lastSuspendTime = lastSuspendTime2;
        this.millisecondsSpentInSession = millisecondsSpentInSession2;
        this.interruptionCount = interruptionCount2;
        this.firstOpenSourceApplication = firstOpenSourceApplication2;
    }

    private Object writeReplace() {
        return new SerializationProxyV2(this.lastResumeTime, this.lastSuspendTime, this.millisecondsSpentInSession, this.interruptionCount, this.firstOpenSourceApplication);
    }

    /* access modifiers changed from: 0000 */
    public void onSuspend(AppEventsLogger logger, long eventTime) {
        if (!this.isAppActive) {
            Logger.log(LoggingBehavior.APP_EVENTS, TAG, "Suspend for inactive app");
            return;
        }
        long now = eventTime;
        long delta = now - this.lastResumeTime;
        if (delta < 0) {
            Logger.log(LoggingBehavior.APP_EVENTS, TAG, "Clock skew detected");
            delta = 0;
        }
        this.millisecondsSpentInSession += delta;
        this.lastSuspendTime = now;
        this.isAppActive = false;
    }

    /* access modifiers changed from: 0000 */
    public void onResume(AppEventsLogger logger, long eventTime, String sourceApplicationInfo) {
        long now = eventTime;
        if (isColdLaunch() || now - this.lastActivateEventLoggedTime > APP_ACTIVATE_SUPPRESSION_PERIOD_IN_MILLISECONDS) {
            Bundle eventParams = new Bundle();
            eventParams.putString(AppEventsConstants.EVENT_PARAM_SOURCE_APPLICATION, sourceApplicationInfo);
            logger.logEvent(AppEventsConstants.EVENT_NAME_ACTIVATED_APP, eventParams);
            this.lastActivateEventLoggedTime = now;
            if (AppEventsLogger.getFlushBehavior() != FlushBehavior.EXPLICIT_ONLY) {
                logger.flush();
            }
        }
        if (this.isAppActive) {
            Logger.log(LoggingBehavior.APP_EVENTS, TAG, "Resume for active app");
            return;
        }
        long interruptionDurationMillis = wasSuspendedEver() ? now - this.lastSuspendTime : 0;
        if (interruptionDurationMillis < 0) {
            Logger.log(LoggingBehavior.APP_EVENTS, TAG, "Clock skew detected");
            interruptionDurationMillis = 0;
        }
        if (interruptionDurationMillis > 60000) {
            logAppDeactivatedEvent(logger, interruptionDurationMillis);
        } else if (interruptionDurationMillis > 1000) {
            this.interruptionCount++;
        }
        if (this.interruptionCount == 0) {
            this.firstOpenSourceApplication = sourceApplicationInfo;
        }
        this.lastResumeTime = now;
        this.isAppActive = true;
    }

    private void logAppDeactivatedEvent(AppEventsLogger logger, long interruptionDurationMillis) {
        Bundle eventParams = new Bundle();
        eventParams.putInt(AppEventsConstants.EVENT_NAME_SESSION_INTERRUPTIONS, this.interruptionCount);
        eventParams.putString(AppEventsConstants.EVENT_NAME_TIME_BETWEEN_SESSIONS, String.format(Locale.ROOT, "session_quanta_%d", new Object[]{Integer.valueOf(getQuantaIndex(interruptionDurationMillis))}));
        eventParams.putString(AppEventsConstants.EVENT_PARAM_SOURCE_APPLICATION, this.firstOpenSourceApplication);
        logger.logEvent(AppEventsConstants.EVENT_NAME_DEACTIVATED_APP, (double) (this.millisecondsSpentInSession / 1000), eventParams);
        resetSession();
    }

    private static int getQuantaIndex(long timeBetweenSessions) {
        int quantaIndex = 0;
        while (quantaIndex < INACTIVE_SECONDS_QUANTA.length && INACTIVE_SECONDS_QUANTA[quantaIndex] < timeBetweenSessions) {
            quantaIndex++;
        }
        return quantaIndex;
    }

    private void resetSession() {
        this.isAppActive = false;
        this.lastResumeTime = -1;
        this.lastSuspendTime = -1;
        this.interruptionCount = 0;
        this.millisecondsSpentInSession = 0;
    }

    private boolean wasSuspendedEver() {
        return this.lastSuspendTime != -1;
    }

    private boolean isColdLaunch() {
        boolean result = !this.isWarmLaunch;
        this.isWarmLaunch = true;
        return result;
    }
}
