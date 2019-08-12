package com.airbnb.android.core.requests;

import com.airbnb.android.core.events.ProfilePhotoUpdatedEvent;

final /* synthetic */ class SetProfilePhotoRequest$$Lambda$1 implements Runnable {
    private final SetProfilePhotoRequest arg$1;

    private SetProfilePhotoRequest$$Lambda$1(SetProfilePhotoRequest setProfilePhotoRequest) {
        this.arg$1 = setProfilePhotoRequest;
    }

    public static Runnable lambdaFactory$(SetProfilePhotoRequest setProfilePhotoRequest) {
        return new SetProfilePhotoRequest$$Lambda$1(setProfilePhotoRequest);
    }

    public void run() {
        this.arg$1.bus.post(new ProfilePhotoUpdatedEvent());
    }
}
