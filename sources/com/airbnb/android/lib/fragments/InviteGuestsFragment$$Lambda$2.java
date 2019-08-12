package com.airbnb.android.lib.fragments;

import com.airbnb.android.core.utils.NetworkUtil;
import p032rx.functions.Action1;

final /* synthetic */ class InviteGuestsFragment$$Lambda$2 implements Action1 {
    private final InviteGuestsFragment arg$1;

    private InviteGuestsFragment$$Lambda$2(InviteGuestsFragment inviteGuestsFragment) {
        this.arg$1 = inviteGuestsFragment;
    }

    public static Action1 lambdaFactory$(InviteGuestsFragment inviteGuestsFragment) {
        return new InviteGuestsFragment$$Lambda$2(inviteGuestsFragment);
    }

    public void call(Object obj) {
        NetworkUtil.toastGenericNetworkError(this.arg$1.getActivity());
    }
}
