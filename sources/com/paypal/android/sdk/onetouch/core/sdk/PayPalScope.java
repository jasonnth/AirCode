package com.paypal.android.sdk.onetouch.core.sdk;

import com.google.android.gms.common.Scopes;

public enum PayPalScope {
    FUTURE_PAYMENTS("https://uri.paypal.com/services/payments/futurepayments"),
    PROFILE(Scopes.PROFILE),
    PAYPAL_ATTRIBUTES("https://uri.paypal.com/services/paypalattributes"),
    OPENID("openid"),
    EMAIL("email"),
    ADDRESS("address"),
    PHONE("phone");
    
    private String mScopeUri;

    private PayPalScope(String scopeUri) {
        this.mScopeUri = scopeUri;
    }

    public String getScopeUri() {
        return this.mScopeUri;
    }
}
