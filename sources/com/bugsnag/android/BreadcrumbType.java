package com.bugsnag.android;

import com.airbnb.android.core.analytics.FindTweenAnalytics;
import com.facebook.share.internal.ShareConstants;

public enum BreadcrumbType {
    ERROR("error"),
    LOG("log"),
    MANUAL(FindTweenAnalytics.SEARCH_TYPE_MANUAL),
    NAVIGATION("navigation"),
    PROCESS("process"),
    REQUEST(ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID),
    STATE("state"),
    USER("user");
    
    private final String type;

    private BreadcrumbType(String type2) {
        this.type = type2;
    }

    public String toString() {
        return this.type;
    }
}
