package com.airbnb.android.lib.businesstravel;

import com.airbnb.android.utils.KeyboardUtils;

final /* synthetic */ class TripNoteFragment$$Lambda$1 implements Runnable {
    private final TripNoteFragment arg$1;

    private TripNoteFragment$$Lambda$1(TripNoteFragment tripNoteFragment) {
        this.arg$1 = tripNoteFragment;
    }

    public static Runnable lambdaFactory$(TripNoteFragment tripNoteFragment) {
        return new TripNoteFragment$$Lambda$1(tripNoteFragment);
    }

    public void run() {
        KeyboardUtils.showSoftKeyboard(this.arg$1.editText);
    }
}
