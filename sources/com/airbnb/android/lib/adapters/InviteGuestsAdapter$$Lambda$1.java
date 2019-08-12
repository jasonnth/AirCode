package com.airbnb.android.lib.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.lib.adapters.InviteGuestsAdapter.InviteGuestsAdapterCallbacks;

final /* synthetic */ class InviteGuestsAdapter$$Lambda$1 implements OnClickListener {
    private final InviteGuestsAdapterCallbacks arg$1;

    private InviteGuestsAdapter$$Lambda$1(InviteGuestsAdapterCallbacks inviteGuestsAdapterCallbacks) {
        this.arg$1 = inviteGuestsAdapterCallbacks;
    }

    public static OnClickListener lambdaFactory$(InviteGuestsAdapterCallbacks inviteGuestsAdapterCallbacks) {
        return new InviteGuestsAdapter$$Lambda$1(inviteGuestsAdapterCallbacks);
    }

    public void onClick(View view) {
        this.arg$1.goToInvite();
    }
}
