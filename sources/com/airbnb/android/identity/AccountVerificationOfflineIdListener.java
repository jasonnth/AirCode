package com.airbnb.android.identity;

public interface AccountVerificationOfflineIdListener {
    void clickedDocs();

    void startIdCaptureFlow(GovernmentIdType governmentIdType);
}
