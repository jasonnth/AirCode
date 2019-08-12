package com.airbnb.android.lib.fragments.verifiedid;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class OfficialIdCountryFragment$$Lambda$1 implements OnClickListener {
    private final OfficialIdCountryFragment arg$1;

    private OfficialIdCountryFragment$$Lambda$1(OfficialIdCountryFragment officialIdCountryFragment) {
        this.arg$1 = officialIdCountryFragment;
    }

    public static OnClickListener lambdaFactory$(OfficialIdCountryFragment officialIdCountryFragment) {
        return new OfficialIdCountryFragment$$Lambda$1(officialIdCountryFragment);
    }

    public void onClick(View view) {
        OfficialIdCountryFragment.lambda$onCreateView$0(this.arg$1, view);
    }
}
