package com.airbnb.android.core.events;

public class WechatShareTripFinishedEvent {
    public final int errorCode;
    public final String errorString;
    public final boolean success;

    public static WechatShareTripFinishedEvent newSuccessEvent() {
        return new WechatShareTripFinishedEvent(true, 0, null);
    }

    public static WechatShareTripFinishedEvent newFailureEvent(int errorCode2, String errorString2) {
        return new WechatShareTripFinishedEvent(false, errorCode2, errorString2);
    }

    public WechatShareTripFinishedEvent(boolean success2, int errorCode2, String errorString2) {
        this.success = success2;
        this.errorCode = errorCode2;
        this.errorString = errorString2;
    }
}
