package com.facebook;

public enum AccessTokenSource {
    NONE(false),
    FACEBOOK_APPLICATION_WEB(true),
    FACEBOOK_APPLICATION_NATIVE(true),
    FACEBOOK_APPLICATION_SERVICE(true),
    WEB_VIEW(true),
    CHROME_CUSTOM_TAB(true),
    TEST_USER(true),
    CLIENT_TOKEN(true),
    DEVICE_AUTH(true);
    
    private final boolean canExtendToken;

    private AccessTokenSource(boolean canExtendToken2) {
        this.canExtendToken = canExtendToken2;
    }

    /* access modifiers changed from: 0000 */
    public boolean canExtendToken() {
        return this.canExtendToken;
    }
}
