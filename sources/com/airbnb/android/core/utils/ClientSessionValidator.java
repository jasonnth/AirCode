package com.airbnb.android.core.utils;

import android.app.Activity;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.airbnb.android.core.AirbnbPreferences;
import com.airbnb.android.core.AppForegroundDetector.AppForegroundListener;
import com.airbnb.android.core.JitneyPublisher;
import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.utils.Strap;
import com.airbnb.jitney.event.logging.Sessionization.p251v1.SessionizationSessionChangeEvent.Builder;
import java.util.UUID;

public class ClientSessionValidator implements AppForegroundListener {
    static final String CLIENT_SESSION_LAST_ACTION_TIMESTAMP = "client_session_last_action_timestamp";
    static final String CLIENT_SESSION_START_TIMESTAMP = "client_session_start_timestamp";
    private static final long MAX_DURATION_FROM_LAST_ACTION = 1800000;
    private static final long MAX_SESSION_MAX_LENGTH = 86400000;
    private final SharedPreferences globalPreferences;
    private long lastActionTimestamp;
    private final LoggingContextFactory loggingContextFactory;
    private long sessionStartTimestamp;

    public ClientSessionValidator(AirbnbPreferences preferences, LoggingContextFactory loggingContextFactory2) {
        this.globalPreferences = preferences.getGlobalSharedPreferences();
        this.loggingContextFactory = loggingContextFactory2;
        restoreSavedSession();
        validateClientSession();
    }

    /* access modifiers changed from: 0000 */
    public void restoreSavedSession() {
        this.lastActionTimestamp = this.globalPreferences.getLong(CLIENT_SESSION_LAST_ACTION_TIMESTAMP, 0);
        this.sessionStartTimestamp = this.globalPreferences.getLong(CLIENT_SESSION_START_TIMESTAMP, 0);
    }

    public void onAppForegrounded(Activity entryActivity) {
        validateClientSession();
    }

    public void onAppBackgrounded() {
    }

    private void validateClientSession() {
        String clientSessionId = this.globalPreferences.getString("client_session_id", null);
        if (noSessionSaved(clientSessionId) || sessionHasExpired()) {
            renewAndSaveSession(clientSessionId);
        } else {
            notifyAction();
        }
    }

    public void notifyAction() {
        this.lastActionTimestamp = System.currentTimeMillis();
        this.globalPreferences.edit().putLong(CLIENT_SESSION_LAST_ACTION_TIMESTAMP, this.lastActionTimestamp).apply();
    }

    private boolean noSessionSaved(String clientSessionId) {
        return TextUtils.isEmpty(clientSessionId) || this.lastActionTimestamp <= 0 || this.sessionStartTimestamp <= 0;
    }

    private boolean sessionHasExpired() {
        long currTimestamp = System.currentTimeMillis();
        return currTimestamp - this.lastActionTimestamp > MAX_DURATION_FROM_LAST_ACTION || currTimestamp - this.sessionStartTimestamp > 86400000;
    }

    private void renewAndSaveSession(String oldClientSessionId) {
        String newClientSessionId = UUID.randomUUID().toString();
        this.sessionStartTimestamp = System.currentTimeMillis();
        this.lastActionTimestamp = this.sessionStartTimestamp;
        AirbnbEventLogger.track("client_session_renew", Strap.make().mo11639kv("session_id", newClientSessionId).mo11639kv("old_session_id", oldClientSessionId));
        Builder builder = new Builder(this.loggingContextFactory.newInstance(), newClientSessionId);
        if (!TextUtils.isEmpty(oldClientSessionId)) {
            builder.client_session_id_previous(oldClientSessionId);
        }
        JitneyPublisher.publish(builder);
        this.globalPreferences.edit().putString("client_session_id", newClientSessionId).putLong(CLIENT_SESSION_START_TIMESTAMP, this.sessionStartTimestamp).putLong(CLIENT_SESSION_LAST_ACTION_TIMESTAMP, this.lastActionTimestamp).apply();
    }
}
