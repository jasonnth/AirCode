package com.airbnb.android.core.events;

public class WechatLoginAuthCodeEvent {
    private final String wechatAuthCode;

    public WechatLoginAuthCodeEvent(String authcode) {
        this.wechatAuthCode = authcode;
    }

    public String getWechatAuthCode() {
        return this.wechatAuthCode;
    }
}
