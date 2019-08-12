package com.airbnb.android.lib.activities;

final /* synthetic */ class CompleteProfileActivity$$Lambda$2 implements Runnable {
    private final CompleteProfileActivity arg$1;

    private CompleteProfileActivity$$Lambda$2(CompleteProfileActivity completeProfileActivity) {
        this.arg$1 = completeProfileActivity;
    }

    public static Runnable lambdaFactory$(CompleteProfileActivity completeProfileActivity) {
        return new CompleteProfileActivity$$Lambda$2(completeProfileActivity);
    }

    public void run() {
        CompleteProfileActivity.lambda$animateShowProgressBar$0(this.arg$1);
    }
}
