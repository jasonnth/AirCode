package com.paypal.android.sdk.onetouch.core.enums;

import com.facebook.appevents.AppEventsConstants;

public enum Protocol {
    v0("0.0"),
    v1("1.0"),
    v2("2.0"),
    v3("3.0");
    
    private final String mVersion;

    private Protocol(String version) {
        this.mVersion = version;
    }

    public String getVersion() {
        return this.mVersion;
    }

    public static Protocol getProtocol(String protocol) {
        char c = 65535;
        switch (protocol.hashCode()) {
            case 48:
                if (protocol.equals(AppEventsConstants.EVENT_PARAM_VALUE_NO)) {
                    c = 0;
                    break;
                }
                break;
            case 49:
                if (protocol.equals("1")) {
                    c = 1;
                    break;
                }
                break;
            case 50:
                if (protocol.equals("2")) {
                    c = 2;
                    break;
                }
                break;
            case 51:
                if (protocol.equals("3")) {
                    c = 3;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return v0;
            case 1:
                return v1;
            case 2:
                return v2;
            case 3:
                return v3;
            default:
                throw new IllegalArgumentException("invalid protocol");
        }
    }
}
