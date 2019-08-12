package com.airbnb.android.lib.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class InviteGuestSelectFragment$$Lambda$2 implements Action1 {
    private final InviteGuestSelectFragment arg$1;

    private InviteGuestSelectFragment$$Lambda$2(InviteGuestSelectFragment inviteGuestSelectFragment) {
        this.arg$1 = inviteGuestSelectFragment;
    }

    public static Action1 lambdaFactory$(InviteGuestSelectFragment inviteGuestSelectFragment) {
        return new InviteGuestSelectFragment$$Lambda$2(inviteGuestSelectFragment);
    }

    public void call(Object obj) {
        InviteGuestSelectFragment.lambda$new$5(this.arg$1, (AirRequestNetworkException) obj);
    }
}
