package com.paypal.android.sdk.onetouch.core.sdk;

import android.content.Intent;
import com.paypal.android.sdk.onetouch.core.enums.RequestTarget;

public class PendingRequest {
    private final String mClientMetadataId;
    private final Intent mIntent;
    private final RequestTarget mRequestTarget;
    private final boolean mSuccess;

    public PendingRequest(boolean success, RequestTarget requestTarget, String clientMetadataId, Intent intent) {
        this.mSuccess = success;
        this.mRequestTarget = requestTarget;
        this.mClientMetadataId = clientMetadataId;
        this.mIntent = intent;
    }

    public boolean isSuccess() {
        return this.mSuccess;
    }

    public RequestTarget getRequestTarget() {
        return this.mRequestTarget;
    }

    public Intent getIntent() {
        return this.mIntent;
    }
}
