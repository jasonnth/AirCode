package com.airbnb.android.lib.fragments.verifiedid;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class OfficialIdErrorFragment$$Lambda$1 implements OnClickListener {
    private final OfficialIdErrorFragment arg$1;

    private OfficialIdErrorFragment$$Lambda$1(OfficialIdErrorFragment officialIdErrorFragment) {
        this.arg$1 = officialIdErrorFragment;
    }

    public static OnClickListener lambdaFactory$(OfficialIdErrorFragment officialIdErrorFragment) {
        return new OfficialIdErrorFragment$$Lambda$1(officialIdErrorFragment);
    }

    public void onClick(View view) {
        OfficialIdErrorFragment.lambda$onCreateView$0(this.arg$1, view);
    }
}
