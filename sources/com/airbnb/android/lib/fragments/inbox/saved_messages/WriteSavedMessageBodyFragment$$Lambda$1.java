package com.airbnb.android.lib.fragments.inbox.saved_messages;

import com.airbnb.android.utils.KeyboardUtils;

final /* synthetic */ class WriteSavedMessageBodyFragment$$Lambda$1 implements Runnable {
    private final WriteSavedMessageBodyFragment arg$1;

    private WriteSavedMessageBodyFragment$$Lambda$1(WriteSavedMessageBodyFragment writeSavedMessageBodyFragment) {
        this.arg$1 = writeSavedMessageBodyFragment;
    }

    public static Runnable lambdaFactory$(WriteSavedMessageBodyFragment writeSavedMessageBodyFragment) {
        return new WriteSavedMessageBodyFragment$$Lambda$1(writeSavedMessageBodyFragment);
    }

    public void run() {
        KeyboardUtils.showSoftKeyboard(this.arg$1.getActivity(), this.arg$1.editText);
    }
}
