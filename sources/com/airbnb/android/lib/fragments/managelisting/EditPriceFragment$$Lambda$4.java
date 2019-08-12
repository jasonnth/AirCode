package com.airbnb.android.lib.fragments.managelisting;

import com.airbnb.android.utils.KeyboardUtils;

final /* synthetic */ class EditPriceFragment$$Lambda$4 implements Runnable {
    private final EditPriceFragment arg$1;

    private EditPriceFragment$$Lambda$4(EditPriceFragment editPriceFragment) {
        this.arg$1 = editPriceFragment;
    }

    public static Runnable lambdaFactory$(EditPriceFragment editPriceFragment) {
        return new EditPriceFragment$$Lambda$4(editPriceFragment);
    }

    public void run() {
        KeyboardUtils.showSoftKeyboard(this.arg$1.getActivity(), this.arg$1.mPriceText);
    }
}
