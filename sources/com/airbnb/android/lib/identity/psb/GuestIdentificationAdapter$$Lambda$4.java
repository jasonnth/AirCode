package com.airbnb.android.lib.identity.psb;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.interfaces.GuestIdentity;

final /* synthetic */ class GuestIdentificationAdapter$$Lambda$4 implements OnClickListener {
    private final GuestIdentificationAdapter arg$1;
    private final GuestIdentity arg$2;
    private final boolean arg$3;

    private GuestIdentificationAdapter$$Lambda$4(GuestIdentificationAdapter guestIdentificationAdapter, GuestIdentity guestIdentity, boolean z) {
        this.arg$1 = guestIdentificationAdapter;
        this.arg$2 = guestIdentity;
        this.arg$3 = z;
    }

    public static OnClickListener lambdaFactory$(GuestIdentificationAdapter guestIdentificationAdapter, GuestIdentity guestIdentity, boolean z) {
        return new GuestIdentificationAdapter$$Lambda$4(guestIdentificationAdapter, guestIdentity, z);
    }

    public void onClick(View view) {
        this.arg$1.identityClickListener.onIdentityClick(this.arg$2, this.arg$3);
    }
}
