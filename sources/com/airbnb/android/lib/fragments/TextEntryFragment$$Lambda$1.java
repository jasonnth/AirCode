package com.airbnb.android.lib.fragments;

import com.airbnb.android.utils.KeyboardUtils;

final /* synthetic */ class TextEntryFragment$$Lambda$1 implements Runnable {
    private final TextEntryFragment arg$1;

    private TextEntryFragment$$Lambda$1(TextEntryFragment textEntryFragment) {
        this.arg$1 = textEntryFragment;
    }

    public static Runnable lambdaFactory$(TextEntryFragment textEntryFragment) {
        return new TextEntryFragment$$Lambda$1(textEntryFragment);
    }

    public void run() {
        KeyboardUtils.showSoftKeyboard(this.arg$1.editText);
    }
}
