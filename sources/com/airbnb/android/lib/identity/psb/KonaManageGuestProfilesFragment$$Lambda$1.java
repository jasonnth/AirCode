package com.airbnb.android.lib.identity.psb;

import com.airbnb.android.core.intents.WebViewIntentBuilder;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.interfaces.LinkOnClickListener;

final /* synthetic */ class KonaManageGuestProfilesFragment$$Lambda$1 implements LinkOnClickListener {
    private final KonaManageGuestProfilesFragment arg$1;

    private KonaManageGuestProfilesFragment$$Lambda$1(KonaManageGuestProfilesFragment konaManageGuestProfilesFragment) {
        this.arg$1 = konaManageGuestProfilesFragment;
    }

    public static LinkOnClickListener lambdaFactory$(KonaManageGuestProfilesFragment konaManageGuestProfilesFragment) {
        return new KonaManageGuestProfilesFragment$$Lambda$1(konaManageGuestProfilesFragment);
    }

    public void onClickLink(int i) {
        WebViewIntentBuilder.startMobileWebActivity(this.arg$1.getContext(), this.arg$1.getString(C0880R.string.psb_china_terms));
    }
}
