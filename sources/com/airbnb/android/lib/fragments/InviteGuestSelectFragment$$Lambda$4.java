package com.airbnb.android.lib.fragments;

import com.airbnb.android.utils.KeyboardUtils;

final /* synthetic */ class InviteGuestSelectFragment$$Lambda$4 implements Runnable {
    private final InviteGuestSelectFragment arg$1;

    private InviteGuestSelectFragment$$Lambda$4(InviteGuestSelectFragment inviteGuestSelectFragment) {
        this.arg$1 = inviteGuestSelectFragment;
    }

    public static Runnable lambdaFactory$(InviteGuestSelectFragment inviteGuestSelectFragment) {
        return new InviteGuestSelectFragment$$Lambda$4(inviteGuestSelectFragment);
    }

    public void run() {
        KeyboardUtils.showSoftKeyboard(this.arg$1.getActivity(), this.arg$1.editText);
    }
}
