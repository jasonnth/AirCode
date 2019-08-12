package com.airbnb.android.lib.fragments;

import android.view.View;
import android.view.View.OnLongClickListener;
import com.airbnb.android.core.interfaces.GuestIdentity;

final /* synthetic */ class ManageGuestIdentityInfoFragment$IdentitiesAdapter$$Lambda$1 implements OnLongClickListener {
    private final IdentitiesAdapter arg$1;
    private final int arg$2;

    private ManageGuestIdentityInfoFragment$IdentitiesAdapter$$Lambda$1(IdentitiesAdapter identitiesAdapter, int i) {
        this.arg$1 = identitiesAdapter;
        this.arg$2 = i;
    }

    public static OnLongClickListener lambdaFactory$(IdentitiesAdapter identitiesAdapter, int i) {
        return new ManageGuestIdentityInfoFragment$IdentitiesAdapter$$Lambda$1(identitiesAdapter, i);
    }

    public boolean onLongClick(View view) {
        return this.arg$1.callbacks.deleteIdentity((GuestIdentity) this.arg$1.items.get(this.arg$2));
    }
}
