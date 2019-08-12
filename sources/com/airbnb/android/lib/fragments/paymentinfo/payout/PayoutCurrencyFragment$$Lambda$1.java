package com.airbnb.android.lib.fragments.paymentinfo.payout;

import com.airbnb.android.booking.p336n2.SimpleSelectionViewItem;
import com.airbnb.p027n2.collections.BaseSelectionView.SelectionSheetOnItemClickedListener;

final /* synthetic */ class PayoutCurrencyFragment$$Lambda$1 implements SelectionSheetOnItemClickedListener {
    private final PayoutCurrencyFragment arg$1;

    private PayoutCurrencyFragment$$Lambda$1(PayoutCurrencyFragment payoutCurrencyFragment) {
        this.arg$1 = payoutCurrencyFragment;
    }

    public static SelectionSheetOnItemClickedListener lambdaFactory$(PayoutCurrencyFragment payoutCurrencyFragment) {
        return new PayoutCurrencyFragment$$Lambda$1(payoutCurrencyFragment);
    }

    public void onItemClicked(Object obj) {
        PayoutCurrencyFragment.lambda$onCreateView$0(this.arg$1, (SimpleSelectionViewItem) obj);
    }
}
