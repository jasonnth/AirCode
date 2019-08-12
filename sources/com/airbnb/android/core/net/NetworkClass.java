package com.airbnb.android.core.net;

import com.facebook.internal.AnalyticsEvents;

public enum NetworkClass {
    TYPE_ROAMING("roaming"),
    TYPE_2G("2G"),
    TYPE_3G("3G"),
    TYPE_4G("4G"),
    TYPE_WIFI("Wifi"),
    Unknown(AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN);
    
    public final String description;

    private NetworkClass(String description2) {
        this.description = description2;
    }
}
