package com.airbnb.android.lib.fragments;

import com.airbnb.android.core.responses.ReservationUserResponse;
import p032rx.functions.Action1;

final /* synthetic */ class InviteGuestsFragment$$Lambda$1 implements Action1 {
    private final InviteGuestsFragment arg$1;

    private InviteGuestsFragment$$Lambda$1(InviteGuestsFragment inviteGuestsFragment) {
        this.arg$1 = inviteGuestsFragment;
    }

    public static Action1 lambdaFactory$(InviteGuestsFragment inviteGuestsFragment) {
        return new InviteGuestsFragment$$Lambda$1(inviteGuestsFragment);
    }

    public void call(Object obj) {
        this.arg$1.adapter.setGuests(((ReservationUserResponse) obj).reservationUsers);
    }
}
