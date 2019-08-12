package com.airbnb.android.lib.identity.psb;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class GuestIdentificationAdapter$$Lambda$2 implements OnClickListener {
    private final GuestIdentificationAdapter arg$1;

    private GuestIdentificationAdapter$$Lambda$2(GuestIdentificationAdapter guestIdentificationAdapter) {
        this.arg$1 = guestIdentificationAdapter;
    }

    public static OnClickListener lambdaFactory$(GuestIdentificationAdapter guestIdentificationAdapter) {
        return new GuestIdentificationAdapter$$Lambda$2(guestIdentificationAdapter);
    }

    public void onClick(View view) {
        this.arg$1.identityClickListener.onAddBookerIdentificationClick();
    }
}
