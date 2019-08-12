package com.airbnb.android.p011p3;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.ReferralStatusForMobile;

/* renamed from: com.airbnb.android.p3.P3Fragment$$Lambda$5 */
final /* synthetic */ class P3Fragment$$Lambda$5 implements OnClickListener {
    private final P3Fragment arg$1;
    private final ReferralStatusForMobile arg$2;

    private P3Fragment$$Lambda$5(P3Fragment p3Fragment, ReferralStatusForMobile referralStatusForMobile) {
        this.arg$1 = p3Fragment;
        this.arg$2 = referralStatusForMobile;
    }

    public static OnClickListener lambdaFactory$(P3Fragment p3Fragment, ReferralStatusForMobile referralStatusForMobile) {
        return new P3Fragment$$Lambda$5(p3Fragment, referralStatusForMobile);
    }

    public void onClick(View view) {
        this.arg$1.controller.onReferralCreditClicked(this.arg$2);
    }
}
