package com.airbnb.android.lib.fragments.verifiedid;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class OfficialIdTypeFragment$$Lambda$1 implements OnClickListener {
    private final OfficialIdTypeFragment arg$1;

    private OfficialIdTypeFragment$$Lambda$1(OfficialIdTypeFragment officialIdTypeFragment) {
        this.arg$1 = officialIdTypeFragment;
    }

    public static OnClickListener lambdaFactory$(OfficialIdTypeFragment officialIdTypeFragment) {
        return new OfficialIdTypeFragment$$Lambda$1(officialIdTypeFragment);
    }

    public void onClick(View view) {
        OfficialIdTypeFragment.lambda$onCreateView$0(this.arg$1, view);
    }
}
