package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.android.core.responses.SimpleListingResponse;
import p032rx.functions.Action1;

final /* synthetic */ class LYSRoomsAndGuestsFragment$$Lambda$1 implements Action1 {
    private final LYSRoomsAndGuestsFragment arg$1;

    private LYSRoomsAndGuestsFragment$$Lambda$1(LYSRoomsAndGuestsFragment lYSRoomsAndGuestsFragment) {
        this.arg$1 = lYSRoomsAndGuestsFragment;
    }

    public static Action1 lambdaFactory$(LYSRoomsAndGuestsFragment lYSRoomsAndGuestsFragment) {
        return new LYSRoomsAndGuestsFragment$$Lambda$1(lYSRoomsAndGuestsFragment);
    }

    public void call(Object obj) {
        LYSRoomsAndGuestsFragment.lambda$new$0(this.arg$1, (SimpleListingResponse) obj);
    }
}
