package com.airbnb.android.lib.fragments;

import com.airbnb.android.core.intents.ReactNativeIntents;
import com.airbnb.android.core.responses.ReservationResponse;
import p032rx.functions.Action1;

final /* synthetic */ class ThreadFragment$$Lambda$3 implements Action1 {
    private final ThreadFragment arg$1;

    private ThreadFragment$$Lambda$3(ThreadFragment threadFragment) {
        this.arg$1 = threadFragment;
    }

    public static Action1 lambdaFactory$(ThreadFragment threadFragment) {
        return new ThreadFragment$$Lambda$3(threadFragment);
    }

    public void call(Object obj) {
        this.arg$1.startActivity(ReactNativeIntents.intentForAlterations(this.arg$1.getContext(), ((ReservationResponse) obj).reservation, this.arg$1.inboxType.isHostMode()));
    }
}
