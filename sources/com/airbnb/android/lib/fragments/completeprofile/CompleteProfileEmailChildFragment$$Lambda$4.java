package com.airbnb.android.lib.fragments.completeprofile;

import com.airbnb.android.utils.KeyboardUtils;

final /* synthetic */ class CompleteProfileEmailChildFragment$$Lambda$4 implements Runnable {
    private final CompleteProfileEmailChildFragment arg$1;

    private CompleteProfileEmailChildFragment$$Lambda$4(CompleteProfileEmailChildFragment completeProfileEmailChildFragment) {
        this.arg$1 = completeProfileEmailChildFragment;
    }

    public static Runnable lambdaFactory$(CompleteProfileEmailChildFragment completeProfileEmailChildFragment) {
        return new CompleteProfileEmailChildFragment$$Lambda$4(completeProfileEmailChildFragment);
    }

    public void run() {
        KeyboardUtils.showSoftKeyboard(this.arg$1.getActivity(), this.arg$1.mEmailAddress);
    }
}
