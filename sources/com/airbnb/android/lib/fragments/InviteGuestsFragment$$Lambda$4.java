package com.airbnb.android.lib.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class InviteGuestsFragment$$Lambda$4 implements Action1 {
    private final InviteGuestsFragment arg$1;

    private InviteGuestsFragment$$Lambda$4(InviteGuestsFragment inviteGuestsFragment) {
        this.arg$1 = inviteGuestsFragment;
    }

    public static Action1 lambdaFactory$(InviteGuestsFragment inviteGuestsFragment) {
        return new InviteGuestsFragment$$Lambda$4(inviteGuestsFragment);
    }

    public void call(Object obj) {
        InviteGuestsFragment.lambda$new$3(this.arg$1, (AirRequestNetworkException) obj);
    }
}
