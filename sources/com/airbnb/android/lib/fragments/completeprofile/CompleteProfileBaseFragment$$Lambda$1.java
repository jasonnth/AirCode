package com.airbnb.android.lib.fragments.completeprofile;

final /* synthetic */ class CompleteProfileBaseFragment$$Lambda$1 implements Runnable {
    private final CompleteProfileBaseFragment arg$1;

    private CompleteProfileBaseFragment$$Lambda$1(CompleteProfileBaseFragment completeProfileBaseFragment) {
        this.arg$1 = completeProfileBaseFragment;
    }

    public static Runnable lambdaFactory$(CompleteProfileBaseFragment completeProfileBaseFragment) {
        return new CompleteProfileBaseFragment$$Lambda$1(completeProfileBaseFragment);
    }

    public void run() {
        CompleteProfileBaseFragment.lambda$showConfirmation$0(this.arg$1);
    }
}
