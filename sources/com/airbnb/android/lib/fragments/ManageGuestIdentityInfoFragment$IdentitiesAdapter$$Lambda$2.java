package com.airbnb.android.lib.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ManageGuestIdentityInfoFragment$IdentitiesAdapter$$Lambda$2 implements OnClickListener {
    private final IdentitiesAdapter arg$1;
    private final int arg$2;

    private ManageGuestIdentityInfoFragment$IdentitiesAdapter$$Lambda$2(IdentitiesAdapter identitiesAdapter, int i) {
        this.arg$1 = identitiesAdapter;
        this.arg$2 = i;
    }

    public static OnClickListener lambdaFactory$(IdentitiesAdapter identitiesAdapter, int i) {
        return new ManageGuestIdentityInfoFragment$IdentitiesAdapter$$Lambda$2(identitiesAdapter, i);
    }

    public void onClick(View view) {
        IdentitiesAdapter.lambda$onBindViewHolder$1(this.arg$1, this.arg$2, view);
    }
}
