package com.airbnb.android.lib.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class InviteGuestSelectFragment$$Lambda$6 implements OnClickListener {
    private final InviteGuestSelectFragment arg$1;
    private final String arg$2;

    private InviteGuestSelectFragment$$Lambda$6(InviteGuestSelectFragment inviteGuestSelectFragment, String str) {
        this.arg$1 = inviteGuestSelectFragment;
        this.arg$2 = str;
    }

    public static OnClickListener lambdaFactory$(InviteGuestSelectFragment inviteGuestSelectFragment, String str) {
        return new InviteGuestSelectFragment$$Lambda$6(inviteGuestSelectFragment, str);
    }

    public void onClick(View view) {
        this.arg$1.removeEmail(this.arg$2);
    }
}
