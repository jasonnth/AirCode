package com.airbnb.android.lib.identity.psb;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class GuestIdentificationAdapter$$Lambda$3 implements OnClickListener {
    private final GuestIdentificationAdapter arg$1;

    private GuestIdentificationAdapter$$Lambda$3(GuestIdentificationAdapter guestIdentificationAdapter) {
        this.arg$1 = guestIdentificationAdapter;
    }

    public static OnClickListener lambdaFactory$(GuestIdentificationAdapter guestIdentificationAdapter) {
        return new GuestIdentificationAdapter$$Lambda$3(guestIdentificationAdapter);
    }

    public void onClick(View view) {
        this.arg$1.identityClickListener.onAddIdentityClick();
    }
}
