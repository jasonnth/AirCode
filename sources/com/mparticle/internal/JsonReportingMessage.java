package com.mparticle.internal;

import org.json.JSONObject;

public interface JsonReportingMessage {
    int getModuleId();

    String getSessionId();

    long getTimestamp();

    void setDevMode(boolean z);

    void setSessionId(String str);

    JSONObject toJson();
}
