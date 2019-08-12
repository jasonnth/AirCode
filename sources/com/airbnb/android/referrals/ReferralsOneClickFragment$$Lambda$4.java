package com.airbnb.android.referrals;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.GrayUser;

final /* synthetic */ class ReferralsOneClickFragment$$Lambda$4 implements OnClickListener {
    private final ReferralsOneClickFragment arg$1;
    private final int arg$2;
    private final GrayUser arg$3;

    private ReferralsOneClickFragment$$Lambda$4(ReferralsOneClickFragment referralsOneClickFragment, int i, GrayUser grayUser) {
        this.arg$1 = referralsOneClickFragment;
        this.arg$2 = i;
        this.arg$3 = grayUser;
    }

    public static OnClickListener lambdaFactory$(ReferralsOneClickFragment referralsOneClickFragment, int i, GrayUser grayUser) {
        return new ReferralsOneClickFragment$$Lambda$4(referralsOneClickFragment, i, grayUser);
    }

    public void onClick(View view) {
        this.arg$1.onUndoButtonClicked(this.arg$2, this.arg$3);
    }
}
