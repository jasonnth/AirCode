package com.airbnb.android.lib.fragments;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.lib.C0880R;

final /* synthetic */ class PayoutListFragment$$Lambda$2 implements OnClickListener {
    private final PayoutListFragment arg$1;

    private PayoutListFragment$$Lambda$2(PayoutListFragment payoutListFragment) {
        this.arg$1 = payoutListFragment;
    }

    public static OnClickListener lambdaFactory$(PayoutListFragment payoutListFragment) {
        return new PayoutListFragment$$Lambda$2(payoutListFragment);
    }

    public void onClick(View view) {
        ZenDialog.createSingleButtonDialog(C0880R.string.payout_add_disabled, C0880R.string.payout_add_disabled_info, C0880R.string.okay).show(this.arg$1.getFragmentManager(), (String) null);
    }
}
