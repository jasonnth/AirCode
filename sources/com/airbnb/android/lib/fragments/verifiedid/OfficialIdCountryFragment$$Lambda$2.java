package com.airbnb.android.lib.fragments.verifiedid;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class OfficialIdCountryFragment$$Lambda$2 implements OnClickListener {
    private final OfficialIdCountryFragment arg$1;

    private OfficialIdCountryFragment$$Lambda$2(OfficialIdCountryFragment officialIdCountryFragment) {
        this.arg$1 = officialIdCountryFragment;
    }

    public static OnClickListener lambdaFactory$(OfficialIdCountryFragment officialIdCountryFragment) {
        return new OfficialIdCountryFragment$$Lambda$2(officialIdCountryFragment);
    }

    public void onClick(View view) {
        OfficialIdCountryFragment.lambda$enableInteraction$1(this.arg$1, view);
    }
}
