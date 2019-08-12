package com.airbnb.android.lib.fragments.verifiedid;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class OfficialIdTypeFragment$$Lambda$2 implements OnClickListener {
    private final OfficialIdTypeFragment arg$1;

    private OfficialIdTypeFragment$$Lambda$2(OfficialIdTypeFragment officialIdTypeFragment) {
        this.arg$1 = officialIdTypeFragment;
    }

    public static OnClickListener lambdaFactory$(OfficialIdTypeFragment officialIdTypeFragment) {
        return new OfficialIdTypeFragment$$Lambda$2(officialIdTypeFragment);
    }

    public void onClick(View view) {
        OfficialIdTypeFragment.lambda$onCreateView$1(this.arg$1, view);
    }
}
