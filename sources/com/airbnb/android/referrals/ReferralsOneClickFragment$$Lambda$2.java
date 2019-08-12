package com.airbnb.android.referrals;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ReferralsOneClickFragment$$Lambda$2 implements OnClickListener {
    private final ReferralsOneClickFragment arg$1;

    private ReferralsOneClickFragment$$Lambda$2(ReferralsOneClickFragment referralsOneClickFragment) {
        this.arg$1 = referralsOneClickFragment;
    }

    public static OnClickListener lambdaFactory$(ReferralsOneClickFragment referralsOneClickFragment) {
        return new ReferralsOneClickFragment$$Lambda$2(referralsOneClickFragment);
    }

    public void onClick(View view) {
        this.arg$1.onDoneButtonClicked();
    }
}
