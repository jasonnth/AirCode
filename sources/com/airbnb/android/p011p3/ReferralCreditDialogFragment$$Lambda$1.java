package com.airbnb.android.p011p3;

import android.view.View;
import android.view.View.OnClickListener;

/* renamed from: com.airbnb.android.p3.ReferralCreditDialogFragment$$Lambda$1 */
final /* synthetic */ class ReferralCreditDialogFragment$$Lambda$1 implements OnClickListener {
    private final ReferralCreditDialogFragment arg$1;

    private ReferralCreditDialogFragment$$Lambda$1(ReferralCreditDialogFragment referralCreditDialogFragment) {
        this.arg$1 = referralCreditDialogFragment;
    }

    public static OnClickListener lambdaFactory$(ReferralCreditDialogFragment referralCreditDialogFragment) {
        return new ReferralCreditDialogFragment$$Lambda$1(referralCreditDialogFragment);
    }

    public void onClick(View view) {
        this.arg$1.getActivity().onBackPressed();
    }
}
