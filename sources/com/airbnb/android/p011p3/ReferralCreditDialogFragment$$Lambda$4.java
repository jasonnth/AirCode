package com.airbnb.android.p011p3;

import android.view.View;
import android.view.View.OnClickListener;

/* renamed from: com.airbnb.android.p3.ReferralCreditDialogFragment$$Lambda$4 */
final /* synthetic */ class ReferralCreditDialogFragment$$Lambda$4 implements OnClickListener {
    private final ReferralCreditDialogFragment arg$1;

    private ReferralCreditDialogFragment$$Lambda$4(ReferralCreditDialogFragment referralCreditDialogFragment) {
        this.arg$1 = referralCreditDialogFragment;
    }

    public static OnClickListener lambdaFactory$(ReferralCreditDialogFragment referralCreditDialogFragment) {
        return new ReferralCreditDialogFragment$$Lambda$4(referralCreditDialogFragment);
    }

    public void onClick(View view) {
        this.arg$1.launchTravelCreditTermsofService();
    }
}
