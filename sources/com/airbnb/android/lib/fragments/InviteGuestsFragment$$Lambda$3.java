package com.airbnb.android.lib.fragments;

import p032rx.functions.Action1;

final /* synthetic */ class InviteGuestsFragment$$Lambda$3 implements Action1 {
    private final InviteGuestsFragment arg$1;

    private InviteGuestsFragment$$Lambda$3(InviteGuestsFragment inviteGuestsFragment) {
        this.arg$1 = inviteGuestsFragment;
    }

    public static Action1 lambdaFactory$(InviteGuestsFragment inviteGuestsFragment) {
        return new InviteGuestsFragment$$Lambda$3(inviteGuestsFragment);
    }

    public void call(Object obj) {
        InviteGuestsFragment.lambda$new$2(this.arg$1, obj);
    }
}
