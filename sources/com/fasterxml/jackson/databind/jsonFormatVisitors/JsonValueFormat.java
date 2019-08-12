package com.fasterxml.jackson.databind.jsonFormatVisitors;

import com.facebook.internal.AnalyticsEvents;
import com.facebook.react.uimanager.ViewProps;
import com.fasterxml.jackson.annotation.JsonValue;

public enum JsonValueFormat {
    COLOR(ViewProps.COLOR),
    DATE("date"),
    DATE_TIME("date-time"),
    EMAIL("email"),
    HOST_NAME("host-name"),
    IP_ADDRESS("ip-address"),
    IPV6("ipv6"),
    PHONE("phone"),
    REGEX("regex"),
    STYLE(AnalyticsEvents.PARAMETER_LIKE_VIEW_STYLE),
    TIME("time"),
    URI("uri"),
    UTC_MILLISEC("utc-millisec");
    
    private final String _desc;

    private JsonValueFormat(String desc) {
        this._desc = desc;
    }

    @JsonValue
    public String toString() {
        return this._desc;
    }
}
