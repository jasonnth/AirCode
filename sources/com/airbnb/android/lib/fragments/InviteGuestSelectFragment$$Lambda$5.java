package com.airbnb.android.lib.fragments;

final /* synthetic */ class InviteGuestSelectFragment$$Lambda$5 implements Runnable {
    private final InviteGuestSelectFragment arg$1;

    private InviteGuestSelectFragment$$Lambda$5(InviteGuestSelectFragment inviteGuestSelectFragment) {
        this.arg$1 = inviteGuestSelectFragment;
    }

    public static Runnable lambdaFactory$(InviteGuestSelectFragment inviteGuestSelectFragment) {
        return new InviteGuestSelectFragment$$Lambda$5(inviteGuestSelectFragment);
    }

    public void run() {
        InviteGuestSelectFragmentPermissionsDispatcher.setupEmailSuggestWithCheck(this.arg$1);
    }
}
