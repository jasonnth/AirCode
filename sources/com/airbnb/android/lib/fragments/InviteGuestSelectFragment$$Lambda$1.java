package com.airbnb.android.lib.fragments;

import com.airbnb.android.core.responses.AirBatchResponse;
import p032rx.functions.Action1;

final /* synthetic */ class InviteGuestSelectFragment$$Lambda$1 implements Action1 {
    private final InviteGuestSelectFragment arg$1;

    private InviteGuestSelectFragment$$Lambda$1(InviteGuestSelectFragment inviteGuestSelectFragment) {
        this.arg$1 = inviteGuestSelectFragment;
    }

    public static Action1 lambdaFactory$(InviteGuestSelectFragment inviteGuestSelectFragment) {
        return new InviteGuestSelectFragment$$Lambda$1(inviteGuestSelectFragment);
    }

    public void call(Object obj) {
        InviteGuestSelectFragment.lambda$new$4(this.arg$1, (AirBatchResponse) obj);
    }
}
