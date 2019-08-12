package com.airbnb.android.listyourspacedls.fragments;

import p032rx.functions.Action1;

final /* synthetic */ class LYSRoomsAndGuestsFragment$$Lambda$3 implements Action1 {
    private final LYSRoomsAndGuestsFragment arg$1;

    private LYSRoomsAndGuestsFragment$$Lambda$3(LYSRoomsAndGuestsFragment lYSRoomsAndGuestsFragment) {
        this.arg$1 = lYSRoomsAndGuestsFragment;
    }

    public static Action1 lambdaFactory$(LYSRoomsAndGuestsFragment lYSRoomsAndGuestsFragment) {
        return new LYSRoomsAndGuestsFragment$$Lambda$3(lYSRoomsAndGuestsFragment);
    }

    public void call(Object obj) {
        this.arg$1.setLoadingFinished(((Boolean) obj).booleanValue(), this.arg$1.roomsAndGuestsAdapter);
    }
}
