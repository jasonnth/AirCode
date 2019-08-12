package com.airbnb.android.lib.identity.psb;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class KonaManageGuestProfilesFragment$$Lambda$4 implements OnClickListener {
    private final KonaManageGuestProfilesFragment arg$1;

    private KonaManageGuestProfilesFragment$$Lambda$4(KonaManageGuestProfilesFragment konaManageGuestProfilesFragment) {
        this.arg$1 = konaManageGuestProfilesFragment;
    }

    public static OnClickListener lambdaFactory$(KonaManageGuestProfilesFragment konaManageGuestProfilesFragment) {
        return new KonaManageGuestProfilesFragment$$Lambda$4(konaManageGuestProfilesFragment);
    }

    public void onClick(View view) {
        this.arg$1.onAddBookerIdentificationClick();
    }
}
