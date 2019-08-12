package com.airbnb.android.core.fragments;

import com.airbnb.android.utils.KeyboardUtils;

final /* synthetic */ class EditTextFragment$$Lambda$2 implements Runnable {
    private final EditTextFragment arg$1;

    private EditTextFragment$$Lambda$2(EditTextFragment editTextFragment) {
        this.arg$1 = editTextFragment;
    }

    public static Runnable lambdaFactory$(EditTextFragment editTextFragment) {
        return new EditTextFragment$$Lambda$2(editTextFragment);
    }

    public void run() {
        KeyboardUtils.showSoftKeyboard(this.arg$1.getActivity(), this.arg$1.editText);
    }
}
