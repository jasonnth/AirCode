package com.airbnb.android.lib.adapters;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class InviteGuestSelectAdapter$$Lambda$1 implements OnClickListener {
    private final InviteGuestSelectAdapter arg$1;
    private final String arg$2;

    private InviteGuestSelectAdapter$$Lambda$1(InviteGuestSelectAdapter inviteGuestSelectAdapter, String str) {
        this.arg$1 = inviteGuestSelectAdapter;
        this.arg$2 = str;
    }

    public static OnClickListener lambdaFactory$(InviteGuestSelectAdapter inviteGuestSelectAdapter, String str) {
        return new InviteGuestSelectAdapter$$Lambda$1(inviteGuestSelectAdapter, str);
    }

    public void onClick(View view) {
        this.arg$1.listener.onEmailSelected(this.arg$2);
    }
}
