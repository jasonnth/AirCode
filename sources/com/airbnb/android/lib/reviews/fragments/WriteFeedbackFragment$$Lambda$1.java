package com.airbnb.android.lib.reviews.fragments;

import com.airbnb.android.utils.KeyboardUtils;

final /* synthetic */ class WriteFeedbackFragment$$Lambda$1 implements Runnable {
    private final WriteFeedbackFragment arg$1;

    private WriteFeedbackFragment$$Lambda$1(WriteFeedbackFragment writeFeedbackFragment) {
        this.arg$1 = writeFeedbackFragment;
    }

    public static Runnable lambdaFactory$(WriteFeedbackFragment writeFeedbackFragment) {
        return new WriteFeedbackFragment$$Lambda$1(writeFeedbackFragment);
    }

    public void run() {
        KeyboardUtils.showSoftKeyboard(this.arg$1.getActivity(), this.arg$1.editText);
    }
}
