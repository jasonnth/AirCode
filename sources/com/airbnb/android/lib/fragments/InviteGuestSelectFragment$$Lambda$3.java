package com.airbnb.android.lib.fragments;

import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

final /* synthetic */ class InviteGuestSelectFragment$$Lambda$3 implements OnEditorActionListener {
    private final InviteGuestSelectFragment arg$1;

    private InviteGuestSelectFragment$$Lambda$3(InviteGuestSelectFragment inviteGuestSelectFragment) {
        this.arg$1 = inviteGuestSelectFragment;
    }

    public static OnEditorActionListener lambdaFactory$(InviteGuestSelectFragment inviteGuestSelectFragment) {
        return new InviteGuestSelectFragment$$Lambda$3(inviteGuestSelectFragment);
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        return InviteGuestSelectFragment.lambda$onCreateView$0(this.arg$1, textView, i, keyEvent);
    }
}
