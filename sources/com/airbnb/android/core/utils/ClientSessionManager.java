package com.airbnb.android.core.utils;

import android.content.SharedPreferences;
import com.airbnb.android.core.AirbnbPreferences;

public class ClientSessionManager {
    static final String CLIENT_SESSION_ID = "client_session_id";
    private final SharedPreferences globalPreferences;

    public ClientSessionManager(AirbnbPreferences preferences) {
        this.globalPreferences = preferences.getGlobalSharedPreferences();
    }

    public String getClientSessionId() {
        return this.globalPreferences.getString(CLIENT_SESSION_ID, null);
    }
}
