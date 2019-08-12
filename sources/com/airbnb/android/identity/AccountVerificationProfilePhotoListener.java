package com.airbnb.android.identity;

public interface AccountVerificationProfilePhotoListener {
    void onNext();

    void onProfilePhotoCompressFailed();

    void uploadPhoto(boolean z);
}
